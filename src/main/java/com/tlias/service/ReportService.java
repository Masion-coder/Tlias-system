package com.tlias.service;

import java.util.List;
import java.util.Map;

import com.tlias.model.JobOption;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();
}
