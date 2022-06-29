package com.liaozibo.study.mockito.service;

import com.liaozibo.study.mockito.dao.StudentDao;
import com.liaozibo.study.mockito.model.Student;

public class StudentService {
    private StudentDao studentDao;

    public Student getById(Long id) {
        return studentDao.getById(id);
    }
}