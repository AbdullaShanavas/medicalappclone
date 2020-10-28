package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.AppointmentActivity;
import com.app.staycured.geoutils.SessionData;

public class SpecialistDetails extends AppCompatActivity {
    Button btnSchdApointmt;
    ImageView ImageView;
    TextView txtDrName;
    int i = 0;
    static int[] images = {R.drawable.doc1, R.drawable.doc2, R.drawable.doc3, R.drawable.doc4, R.drawable.doc5, R.drawable.doc6, R.drawable.doc7, R.drawable.doc8, R.drawable.doc9, R.drawable.doc10,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_details);
        txtDrName = findViewById(R.id.txtDrName);
        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView = findViewById(R.id.img_spcialist);
        btnSchdApointmt = findViewById(R.id.btnSchedAppointmt);
        txtDrName.setText(SessionData.getInstance().getDrName());
        i = SessionData.getInstance().getPos();
        ImageView.setImageResource(images[i]);


        btnSchdApointmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SpecialistDetails.this, AppointmentActivity.class);
                startActivity(i);
            }
        });
    }
}