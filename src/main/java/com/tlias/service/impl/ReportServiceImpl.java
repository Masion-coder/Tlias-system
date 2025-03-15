package com.tlias.service.impl;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlias.mapper.EmpMapper;
import com.tlias.model.JobOption;
import com.tlias.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {
        // 1.调用mapper接口，获取统计数据
        List<Map<String,Object>> list = empMapper.countEmpJobData();
        // 2.组装结果，并返回

        List<Object> jobList = list.stream().map(data -> data.get("pos")).toList();
        List<Object> dataList = list.stream().map(data -> data.get("num")).toList();
        return new JobOption(jobList, dataList);
    }
}
