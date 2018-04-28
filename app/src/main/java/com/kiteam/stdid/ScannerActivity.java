package com.kiteam.stdid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Роман on 28.04.2018.
 */

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    public static final String SCAN_RESULT = "SCAN_RESULT";

    private Toolbar toolbar;
    private ZXingScannerView scannerView;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, ScannerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle state) {
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
        Intent intent = new Intent();
        intent.putExtra(SCAN_RESULT, rawResult.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
