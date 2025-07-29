package com.ss16.service.feedback;

import com.ss16.model.dto.FeedbackRequest;
import com.ss16.model.entity.Feedback;
import com.ss16.model.entity.User;
import com.ss16.repository.ComboRepository;
import com.ss16.repository.FeedbackRepository;
import com.ss16.repository.PlayAreaRepository;
import com.ss16.repository.TicketOrderRepository;
import com.ss16.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepo;
    private final UserRepository userRepo;
    private final PlayAreaRepository playAreaRepo;
    private final ComboRepository comboRepo;
    private final TicketOrderRepository ticketOrderRepo;

    @Override
    public Feedback createFeedback(FeedbackRequest dto, String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean usedService = ticketOrderRepo.existsByUserAndPlayAreaIdOrComboId(
                user.getId(), dto.getPlayAreaId(), dto.getComboId()
        );

        if (!usedService)
            throw new RuntimeException("Bạn chưa sử dụng dịch vụ này");

        Feedback feedback = Feedback.builder()
                .user(user)
                .playArea(dto.getPlayAreaId() != null ?
                        playAreaRepo.findById(dto.getPlayAreaId()).orElse(null) : null)
                .combo(dto.getComboId() != null ?
                        comboRepo.findById(dto.getComboId()).orElse(null) : null)
                .rating(dto.getRating())
                .comment(dto.getComment())
                .createdAt(LocalDateTime.now())
                .build();

        return feedbackRepo.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepo.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Feedback replyToFeedback(Long id, String reply, String staffUsername) {
        Feedback feedback = feedbackRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Phản hồi không tồn tại"));

        feedback.setReply(reply);
        feedback.setRepliedAt(LocalDateTime.now());
        return feedbackRepo.save(feedback);
    }
}
