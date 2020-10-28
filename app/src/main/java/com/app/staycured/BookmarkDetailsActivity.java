package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.app.AppointmentActivity;

public class BookmarkDetailsActivity extends AppCompatActivity {
Button btnScheduleAppointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_details);
        ImageView imgBack=findViewById(R.id.imgBack);
        btnScheduleAppointment=(Button)findViewById(R.id.btnScheduleAppointment);
        btnScheduleAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(BookmarkDetailsActivity.this, AppointmentActivity.class);
                startActivity(i);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




    }
}