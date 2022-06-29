package com.liaozibo.study.mockito.service;

import com.liaozibo.study.mockito.dao.StudentDao;
import com.liaozibo.study.mockito.model.Student;
import com.liaozibo.study.mockito.util.StaticUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentDao studentDao;

    @Spy
    private Random random;

    @Test
    void test1() {
        // 未对 StudentDao 进行打桩，默认返回 null
        Student student = studentService.getById(1L);
        assertNull(student);
    }

    @Test
    public void test2() {
        // 打桩，返回学生对象
        when(studentDao.getById(1L)).thenReturn(new Student(1L, "小明", 18));
        Student student = studentService.getById(1L);
        assertNotNull(student);
        assertEquals(1L, student.getId());
        assertEquals("小明", student.getName());
        assertEquals(18, student.getAge());
        verify(studentDao).getById(1L);
        verify(studentDao, times(1)).getById(1L);
    }

    @Test
    public void test3() {
        // 打桩，抛出异常
        when(studentDao.getById(1L)).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> studentService.getById(1L));
    }

    @Test
    public void test4() {
        // 对静态方法进行打桩
        try (MockedStatic<StaticUtils> staticUtils = mockStatic(StaticUtils.class)) {
            staticUtils.when(StaticUtils::name).thenReturn("nothing");
            assertEquals("nothing", StaticUtils.name());
        }
    }
}