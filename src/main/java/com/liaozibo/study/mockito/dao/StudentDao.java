package com.liaozibo.study.mockito.dao;

import com.liaozibo.study.mockito.model.Student;

public interface StudentDao {
    Student getById(Long id);
}
