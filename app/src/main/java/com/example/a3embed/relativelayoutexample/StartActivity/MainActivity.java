package com.example.a3embed.relativelayoutexample.StartActivity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.a3embed.relativelayoutexample.CameraActivity.CameraActivity;
import com.example.a3embed.relativelayoutexample.R;

public class MainActivity extends AppCompatActivity {

    final public int CAMERA_RESULT = 100;
    private TextView primaryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        primaryTextView = findViewById(R.id.mainTextView);
        final Button mainButton = findViewById(R.id.buttonBelow);
        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final EditText editText = findViewById(R.id.editText);
        Button setButton = findViewById(R.id.setButton);
        Button openNewActivity = findViewById(R.id.openNewActivity);

        primaryTextView.setText(R.string.primaryTextView);
        mainButton.setText(R.string.click_me);
        button1.setText(R.string.one);
        button2.setText(R.string.two);
        button3.setText(R.string.three);
        setButton.setText(R.string.set_text);
        editText.setHint(R.string.enter_something);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primaryTextView.setText(mainButton.getText());
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primaryTextView.setText(button1.getText());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primaryTextView.setText(button2.getText());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primaryTextView.setText(button3.getText());
            }
        });

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(editText.getText().toString())) {
                    primaryTextView.setText(editText.getText().toString());
                } else {
                    primaryTextView.setText(R.string.nothing);
                }
            }
        });

        openNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivityForResult(intent, CAMERA_RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_RESULT) {
            if (resultCode == RESULT_OK) {
                int returnedResult = Integer.parseInt(data.getData().toString());
                if (returnedResult == 100) {
                    primaryTextView.setText(R.string.accessDenied);
                } else if (returnedResult == 200) {
                    primaryTextView.setText(R.string.accessGranted);
                } else if (returnedResult == 300) {
                    primaryTextView.setText(R.string.imageAdded);
                } else {
                    primaryTextView.setText(R.string.primaryTextView);
                }
            }
        }
    }

}
