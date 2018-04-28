package com.kiteam.stdid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.gson.Gson;
import com.kiteam.stdid.data.Sender;
import com.kiteam.stdid.data.StudentAuth;
import com.kiteam.stdid.models.Student;

/**
 * Created by nz2Dev on 28.04.2018
 */
public class AuthActivity extends AppCompatActivity {

    public static void startActivity(LoginActivity loginActivity) {
        loginActivity.startActivity(new Intent(loginActivity, AuthActivity.class));
    }

    private TextView name;
    private Button authBtn;

    private Sender sender;
    private Student authStudent = Student.from(StudentAuth.getInstance().get());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        authBtn = findViewById(R.id.btn_auth);
        name = findViewById(R.id.tv_student_name);
        name.setText(authStudent.name);

        sender = new Sender(FirebaseStorage.getInstance(), new Gson());

        authBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ScannerActivity.class);
            startActivityForResult(intent, RESULT_FIRST_USER);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        sender.send("", authStudent);
    }

}
