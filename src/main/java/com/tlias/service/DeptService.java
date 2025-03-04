package com.tlias.service;

import java.util.List;

import com.tlias.module.Dept;

public interface DeptService {
    List<Dept> findAll();

    void deleteById(Integer id);
}
