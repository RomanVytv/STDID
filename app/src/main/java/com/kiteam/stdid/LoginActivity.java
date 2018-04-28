package com.kiteam.stdid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kiteam.stdid.data.StudentAuth;
import com.kiteam.stdid.data.StudentRepository;
import com.kiteam.stdid.models.StudentData;

/**
 * A login screen that offers login via email/etPassword.
 */
public class LoginActivity extends AppCompatActivity {

    EditText etStudentId;
    EditText etPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etStudentId = findViewById(R.id.student_id);
        etPassword = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.sign_in_button);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        if (isPasswordValid() && isStudentIdValid()) {
            String id = etStudentId.getText().toString();
            String pass = etPassword.getText().toString();

            StudentData studentData = StudentRepository.getInstance().get(id, pass);
            StudentAuth.getInstance().auth(studentData);

            navigateToScanActivity();
        } else {
            showError();
        }
    }

    private void showError() {
        Snackbar.make(btnSignIn.getRootView(), "Incorrect credentials. Please, try again.", BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    private void navigateToScanActivity() {
        ScannerActivity.startActivity(this);
    }

    private boolean isStudentIdValid() {
        return etStudentId.getText().length() == 8;
    }

    private boolean isPasswordValid() {
        return !TextUtils.isEmpty(etPassword.getText());
    }
}

