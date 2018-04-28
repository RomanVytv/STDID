package com.kiteam.stdid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.kiteam.stdid.data.StudentRepository;
import com.kiteam.stdid.models.StudentData;

/**
 * Created by Роман on 28.04.2018.
 */

public class RegisterActivity extends AppCompatActivity {

    EditText etStudentId;
    EditText etFullName;
    EditText etPassword;
    Button btnRegister;
    ImageButton btnScan;


    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etStudentId = findViewById(R.id.student_id);
        etFullName = findViewById(R.id.name);
        etPassword = findViewById(R.id.password);
        btnRegister = findViewById(R.id.sign_up_button);
        btnRegister.setOnClickListener(v -> {
            StudentRepository.getInstance().add(
                    new StudentData(
                            etStudentId.getText().toString(),
                            etFullName.getText().toString(),
                            etPassword.getText().toString()));
            finish();
        });

        btnScan = findViewById(R.id.scan_button);
        btnScan.setOnClickListener(v -> navigateToScan());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        etStudentId.setText(data.getStringExtra(ScannerActivity.SCAN_RESULT));
    }

    private void navigateToScan() {
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivityForResult(intent, RESULT_OK);
    }
}
