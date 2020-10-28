package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SpecialityActivity extends AppCompatActivity {
   LinearLayout lnrCardiology;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciality);
        ImageView imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        init();
        lnrCardiology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SpecialityActivity.this,SpecialistActivity.class);

                startActivity(i);
            }
        });
    }
    public void init(){
        lnrCardiology=(LinearLayout)findViewById(R.id.lnrCardiology);
    }
}