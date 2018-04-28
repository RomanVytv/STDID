package com.kiteam.stdid.models;

/**
 * Created by nz2Dev on 28.04.2018
 */
public class Student {

    public static Student from(StudentData studentData) {
        return new Student(studentData.id, studentData.name);
    }

    public final String id;
    public final String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
