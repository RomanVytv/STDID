package com.kiteam.stdid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.kiteam.stdid.data.Sender;
import com.kiteam.stdid.data.StudentAuth;
import com.kiteam.stdid.models.Student;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Роман on 28.04.2018.
 */

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    public static final String SCAN_RESULT = "SCAN_RESULT";

    private Toolbar toolbar;
    private ZXingScannerView scannerView;
    private Sender sender;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, ScannerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scan);

        scannerView = findViewById(R.id.scanner_view);
        toolbar = findViewById(R.id.toolbar);
        sender = new Sender(FirebaseStorage.getInstance(), new Gson());

        setSupportActionBar(toolbar);
    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        scannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        String codetype = rawResult.getBarcodeFormat().name();
        String code = rawResult.getText();

        if (!rawResult.getBarcodeFormat().equals(BarcodeFormat.QR_CODE)) {
            Intent intent = new Intent();
            intent.putExtra(SCAN_RESULT, rawResult.getText());
            setResult(RESULT_OK, intent);
            finish();
        }

        Toast.makeText(this, codetype + " - " + code, Toast.LENGTH_LONG).show();

        Student student = Student.from(StudentAuth.getInstance().get());
        sender.send(code, student);

        // TODO navigate to success screen.
    }
}
