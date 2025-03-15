package com.tlias.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOption {
    @SuppressWarnings("rawtypes")
    private List jobList;//职位列表
    @SuppressWarnings("rawtypes")
    private List dataList;//人数列表
}
