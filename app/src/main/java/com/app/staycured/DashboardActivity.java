package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.staycured.Settings.SettingsActivity;

public class DashboardActivity extends AppCompatActivity {
    ImageView ImgMap;
    TextView txtProfile, txtSpeciality, txtAppointments, txtBookmarks, txtQuestion, txtSettings, txtSignout;
    TextView signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
        ImgMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DashboardActivity.this,MapActivity.class);
                startActivity(i);
            }
        });
        txtBookmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this,BookmarkActitvity.class);
                startActivity(intent);
            }
        });
        txtQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this,MyQuestionActivity.class);
                startActivity(intent);
            }
        });
        txtSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        txtAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this,MyAppointmentActivity.class);
                startActivity(intent);
            }
        });
        txtSpeciality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this,SpecialityActivity.class);
                startActivity(intent);
            }
        });
        txtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this,MyProfileActivity.class);
                startActivity(intent);
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DashboardActivity.this,MainActivity.class);
                startActivity(i);
            }
        });



    }

    public void init() {
        ImgMap = (ImageView) findViewById(R.id.ImgMapview);
        txtProfile =(TextView)findViewById(R.id.txtProfile);
        txtSpeciality =(TextView)findViewById(R.id.txtSpeciality);
        txtAppointments =(TextView)findViewById(R.id.txtAppointments);
        txtBookmarks =(TextView)findViewById(R.id.txtBookmarks);
        txtQuestion =(TextView)findViewById(R.id.txtQuestion);
        txtSettings =(TextView)findViewById(R.id.txtSettings);
        txtSignout=(TextView)findViewById(R.id.txtSignout);
        signOut=(TextView)findViewById(R.id.txtSignOut);


    }
}