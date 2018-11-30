package com.example.a3embed.relativelayoutexample.OpneURL;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a3embed.relativelayoutexample.R;

public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        // Local Variables and initialization
        Button openGoogle = findViewById(R.id.openGoogle);
        Button openWhatsapp = findViewById(R.id.openWhatsapp);
        final EditText message = findViewById(R.id.message);

        // OnClickListener
        // Google
        openGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.google.com/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        // WhatsApp
        openWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(message.getText().toString())) {
                    PackageManager pm=getPackageManager();
                    try {
                        PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                        Log.d("Whatsapp: ", info.toString());
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        String text = message.getText().toString();
                        intent.setPackage("com.whatsapp");
                        intent.putExtra(Intent.EXTRA_TEXT, text);
                        startActivity(intent);
                    } catch (PackageManager.NameNotFoundException e) {
                        Toast.makeText(BrowserActivity.this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BrowserActivity.this, "Enter Message", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
