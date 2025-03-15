package com.tlias.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlias.model.JobOption;
import com.tlias.model.Result;
import com.tlias.service.ReportService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    /*
     * 统计员工职位人数
     */
    @GetMapping("empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /*
     * 统计员工性别人数

     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工职位人数");
        List<Map<String, Object>> list = reportService.getEmpGenderData();
        return Result.success(list);
    }
    
}
