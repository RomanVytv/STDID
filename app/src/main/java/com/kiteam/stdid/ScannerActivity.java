package com.kiteam.stdid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Роман on 28.04.2018.
 */

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private Toolbar toolbar;
    private ZXingScannerView scannerView;

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

        Toast.makeText(this, codetype + " - " + code, Toast.LENGTH_LONG).show();

        // If you would like to resume scanning, call this method below:
        scannerView.resumeCameraPreview(this);
    }
}
