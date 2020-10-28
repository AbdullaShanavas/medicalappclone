package com.app.staycured.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.staycured.R;

public class AboutActivity extends AppCompatActivity {
    TextView txtVersion,back,description;
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        back =(TextView)findViewById(R.id.txt_back);
        txtVersion =(TextView)findViewById(R.id.txt_version);
        description =(TextView) findViewById(R.id.edt_desc);
        txtVersion.setText("Version 1.0.1");
    }
}