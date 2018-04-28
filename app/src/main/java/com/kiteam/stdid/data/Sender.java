package com.kiteam.stdid.data;

import com.google.firebase.storage.FirebaseStorage;
import com.google.gson.Gson;
import com.kiteam.stdid.models.Student;

/**
 * Created by nz2Dev on 28.04.2018
 */
public class Sender {

    private FirebaseStorage firebaseStorage;
    private Gson gson;

    public Sender(FirebaseStorage firebaseStorage, Gson gson) {
        this.firebaseStorage = firebaseStorage;
        this.gson = gson;
    }

    public void send(String code, Student student) {
        firebaseStorage.getReference()
                .child(code + ".json")
                .putBytes(gson.toJson(student).getBytes());
    }

}
