package com.example.a3embed.relativelayoutexample.CoordinatorLayout;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a3embed.relativelayoutexample.R;

public class CoordinatorLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        // Variables and initialization
        Button showSnackBar = findViewById(R.id.showSnackBar);
        final android.support.design.widget.CoordinatorLayout mainLayout = findViewById(R.id.mainLayout);

        // OnClickListener
        showSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mainLayout, "This is a Snack bar",
                        Snackbar.LENGTH_LONG)
                        .setAction("CLOSE", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
            }
        });

    }
}
