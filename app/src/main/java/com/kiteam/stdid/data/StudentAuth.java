package com.kiteam.stdid.data;

import com.kiteam.stdid.models.StudentData;

/**
 * Created by nz2Dev on 28.04.2018
 */
public class StudentAuth {

    private static StudentAuth instance;

    public static StudentAuth getInstance() {
        if (instance == null) {
            instance = new StudentAuth();
        }
        return instance;
    }

    private StudentData authStudent;

    private StudentAuth() {
    }

    public void auth(StudentData studentData) {
        authStudent = studentData;
    }

    public StudentData get() {
        return authStudent;
    }

}
