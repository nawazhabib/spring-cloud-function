package com.aws.springcloudawslambda.repository;

import com.aws.springcloudawslambda.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepository {

    public List<Student> studentList() {
        return Arrays.asList(
                new Student(1001, "Lutfor", 24),
                new Student(1002, "Mosarof", 25),
                new Student(1003, "Habib", 24),
                new Student(1004, "Keya", 21)
        );
    }
}
