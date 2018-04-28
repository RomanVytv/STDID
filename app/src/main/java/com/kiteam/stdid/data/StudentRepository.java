package com.kiteam.stdid.data;

import com.kiteam.stdid.models.StudentData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by nz2Dev on 28.04.2018
 */
public class StudentRepository {

    private static StudentRepository instance;

    public static StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }

    private List<StudentData> dataList = new ArrayList<>();

    private StudentRepository() {
    }

    public void add(StudentData studentData) {
        dataList.add(studentData);
    }

    public StudentData get(String id, String pass) {
        return Observable.fromIterable(dataList)
                .filter(sd -> sd.id.equals(id) && sd.pass.equals(pass))
                .blockingSingle();
    }

}
