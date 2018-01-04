package com.gy.allen.blacklakezbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.gy.allen.blacklakezbardemo.zxing.QRCodeView;

/**
 * Created by allen on 17/12/12.
 */

public class TestScanActivity extends AppCompatActivity implements QRCodeView.Delegate {
    private QRCodeView mQRCodeView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sacn);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        mQRCodeView = findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);
        mQRCodeView.startCamera();
        mQRCodeView.showScanRect();
        mQRCodeView.startSpotDelay(200);
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(80);
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.tv_manul:
                showManuSheet();
                break;
            case R.id.tv_torch:
                if (mQRCodeView.isFlashLightOpen()) {
                    mQRCodeView.closeFlashlight();
                } else {
                    mQRCodeView.openFlashlight();
                }
                break;
        }
    }

    private void showManuSheet() {
        ManuBottomSheetDialog sheetDialog = new ManuBottomSheetDialog(this);

        sheetDialog.setOnShowListener(dialog12 -> {
            mQRCodeView.stopSpot();
        });
        sheetDialog.setOnCancelListener(dialog1 -> {
            mQRCodeView.startSpotDelay(80);
        });
        sheetDialog.setManuClickListener(new ManuBottomSheetDialog.ManuClickListener() {
            @Override
            public void onCancel() {
                sheetDialog.cancel();
            }

            @Override
            public void onConfirm(String code) {
                sheetDialog.cancel();
                Intent intent = new Intent(TestScanActivity.this, ResultActivity.class);
                intent.putExtra("CODE", code);
                startActivity(intent);
                vibrate();
            }
        });
        sheetDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mQRCodeView.startSpotDelay(10);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mQRCodeView.startSpotDelay(10);
        mQRCodeView.closeFlashlight();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("CODE", result);
        startActivity(intent);
        vibrate();

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this, "请开启相机权限", Toast.LENGTH_LONG).show();
    }
}
