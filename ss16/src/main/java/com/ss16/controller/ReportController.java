package com.ss16.controller;

import com.ss16.model.dto.ReportDTO;
import com.ss16.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/revenue")
    public List<ReportDTO> getRevenue(@RequestParam String type) {
        return Collections.singletonList(reportService.getRevenueReport(type));
    }

    @GetMapping("/attendance")
    public List<ReportDTO> getAttendance(@RequestParam String type) {
        return Collections.singletonList(reportService.getAttendanceReport(type));
    }

    @GetMapping("/combo-usage")
    public List<ReportDTO> getComboUsage(@RequestParam String type) {
        return Collections.singletonList(reportService.getComboUsageReport(type));
    }
}
