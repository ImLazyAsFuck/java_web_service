package com.ss16.service.feedback;

import com.ss16.model.dto.FeedbackRequest;
import com.ss16.model.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(FeedbackRequest dto, String username);
    List<Feedback> getAllFeedbacks();
    Feedback replyToFeedback(Long id, String reply, String staffUsername);
}
