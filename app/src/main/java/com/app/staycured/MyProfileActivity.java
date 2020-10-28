package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Calendar;

public class MyProfileActivity extends AppCompatActivity {
    EditText profileDob;
    Button freqSpecialist,familyMember,medicalRecords,btnMedication;
    Button edit_profile,btnUpdate;
    LinearLayout lnrUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        ImageView imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        profileDob=(EditText) findViewById(R.id.edt_profile_dob);
        freqSpecialist=(Button) findViewById(R.id.btn_freq_specialist);
        familyMember=(Button) findViewById(R.id.btn_family_member);
        medicalRecords=(Button) findViewById(R.id.btn_medical_records);
        btnMedication=(Button) findViewById(R.id.btn_medication);



        edit_profile=(Button) findViewById(R.id.btn_edit);
        btnUpdate=(Button) findViewById(R.id.btn_update);
        lnrUpdate=(LinearLayout) findViewById(R.id.lnr_update);

        lnrUpdate.setVisibility(View.GONE);

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lnrUpdate.setVisibility(View.VISIBLE);


            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lnrUpdate.setVisibility(View.GONE);


            }
        });

        btnMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(MyProfileActivity.this, MedicationActivity.class);
                startActivity(i);

            }
        });


        freqSpecialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MyProfileActivity.this, FreqSpecialistActivity.class);
                startActivity(i);

            }
        });

        familyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent j=new Intent(MyProfileActivity.this,FamilyMembersActivity.class);
                startActivity(j);

            }
        });

        medicalRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent j=new Intent(MyProfileActivity.this, MedicalRecordsActivity.class);
                startActivity(j);

            }
        });



        profileDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(MyProfileActivity.this,
                        R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        profileDob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                },


                        year, month, day);

                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.show();
            }
        });


    }
}