package com.example.a3embed.relativelayoutexample.CoordinatorLayout;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.a3embed.relativelayoutexample.R;

import java.util.Arrays;

public class CoordinatorLayout extends AppCompatActivity {

    private ProgressBar determinateBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        // Variables and initialization
        Button showSnackBar = findViewById(R.id.showSnackBar);
        FloatingActionButton fab = findViewById(R.id.fab);
        final android.support.design.widget.CoordinatorLayout mainLayout = findViewById(R.id.mainLayout);
        determinateBar = findViewById(R.id.determinateBar);

        // OnClickListener
        showSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mainLayout, "This is a Snack bar",
                        Snackbar.LENGTH_LONG)
                        .setAction("CLOSE", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DoSomeTask task = new DoSomeTask();
                                task.execute("Hello");
                            }
                        }).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    class DoSomeTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            for (int i = 0; i < 100; i++) {
                publishProgress (i);
            }
            return params[0];
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            String value = Arrays.toString(values);
            determinateBar.setProgress(Integer.parseInt(value.replace("[", "").replace("]","")));
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
    }

}
