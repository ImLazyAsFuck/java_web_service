package com.ss16.service.report;

import com.ss16.model.dto.ReportDTO;
import com.ss16.repository.TicketOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final TicketOrderRepository ticketOrderRepo;

    @Override
    public ReportDTO getRevenueReport(String type) {
        return ticketOrderRepo.fetchRevenueBy(type);
    }

    @Override
    public ReportDTO getAttendanceReport(String type) {
        return ticketOrderRepo.fetchAttendanceBy(type);
    }

    @Override
    public ReportDTO getComboUsageReport(String type) {
        return ticketOrderRepo.fetchComboUsageBy(type);
    }
}
