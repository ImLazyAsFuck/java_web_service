package com.ss16.service.report;

import com.ss16.model.dto.ReportDTO;

public interface ReportService {
    ReportDTO getRevenueReport(String type);
    ReportDTO getAttendanceReport(String type);
    ReportDTO getComboUsageReport(String type);
}
