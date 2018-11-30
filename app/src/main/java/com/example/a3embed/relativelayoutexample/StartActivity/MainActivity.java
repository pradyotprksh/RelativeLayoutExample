package com.example.a3embed.relativelayoutexample.StartActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a3embed.relativelayoutexample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView primaryTextView = findViewById(R.id.mainTextView);
        final Button mainButton = findViewById(R.id.buttonBelow);
        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final EditText editText = findViewById(R.id.editText);
        Button setButton = findViewById(R.id.setButton);

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

    }
}
