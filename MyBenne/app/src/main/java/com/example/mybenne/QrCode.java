package com.example.mybenne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QrCode extends AppCompatActivity implements View.OnClickListener {

    Button btnTakePicture, btnScanBarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        initViews();
    }

    private void initViews() {
        btnTakePicture = findViewById(R.id.btnTakePicture);
        btnScanBarcode = findViewById(R.id.btnScanBarcode);
        btnTakePicture.setOnClickListener(this);
        btnScanBarcode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            /*case R.id.btnTakePicture:
                startActivity(new Intent(QrCode.this, PictureBarcodeActivity.class));
                break;*/
            case R.id.btnScanBarcode:
                startActivity(new Intent(QrCode.this, ScannedBarcodeActivity.class));
                break;
        }

    }
}
