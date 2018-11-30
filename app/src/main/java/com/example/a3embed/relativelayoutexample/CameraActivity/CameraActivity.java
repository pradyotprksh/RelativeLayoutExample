package com.example.a3embed.relativelayoutexample.CameraActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.a3embed.relativelayoutexample.R;

public class CameraActivity extends AppCompatActivity {

    final int CAMERA_PIC_REQUEST = 0;
    final int CAMERA_PIC_REQUEST_CODE = 1;
    int PERMISSION_GRANT_CODE;
    private ImageView addImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Toolbar mToolbar = findViewById(R.id.topToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Setup Image");

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });

        addImage = findViewById(R.id.addImage);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askRequest();
            }
        });

    }

    public void askRequest() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((ContextCompat.
                    checkSelfPermission(CameraActivity.this,
                            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PIC_REQUEST_CODE);
            } else {
                PERMISSION_GRANT_CODE = 200;
                opneCamera();
            }
        } else {
            PERMISSION_GRANT_CODE = 200;
            opneCamera();
        }
    }

    public void opneCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, CAMERA_PIC_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PIC_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                opneCamera();
            } else {
                PERMISSION_GRANT_CODE = 100;
                Toast.makeText(CameraActivity.this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                finishActivity();
            }
        }
    }

    public void finishActivity() {
        Intent data = new Intent();
        data.setData(Uri.parse(String.valueOf(PERMISSION_GRANT_CODE)));
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                addImage.setImageBitmap(photo);
                PERMISSION_GRANT_CODE = 300;
            }
        }
    }

}
