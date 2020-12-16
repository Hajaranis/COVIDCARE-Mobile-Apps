package com.example.cov_eid.ui.notifications;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

class ScanCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView ScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ScannerView = new ZXingScannerView(this);
        super.onCreate(savedInstanceState);
        setContentView(ScannerView);
    }
    @Override
    public void handleResult(Result result) {

        NotificationsFragment.resultTextView.setText(result.getText());
        onBackPressed();

    }

    @Override
    protected void onPause(){
        super.onPause();

        ScannerView.stopCamera();
    }

    @Override
    protected void onResume(){
        super.onResume();

        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }


}
