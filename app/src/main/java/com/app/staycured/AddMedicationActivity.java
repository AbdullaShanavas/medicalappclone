package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class AddMedicationActivity extends AppCompatActivity {
    EditText edtName, edtDosage, edtDrtnFrom, edtDrnTo, edtFreq;
    RadioButton rdoFood;
    RadioGroup radioGroupFood;
    Button btnAdd;
    private static Dialog dialog;
    private String selectedType = "";


    public static final String Name = "Dola650";
    public static final String Dosage = "emailKey";
    public static final String DrtnFrom = "2020-08-03";
    public static final String DrnTo = "2021-02-05";
    public static final String Freq = "8.00AM 12.00PM 4.00 pm";
    public static final String rdFood = "Before Food";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);
        init();
        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        edtFreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new Dialog(AddMedicationActivity.this);
                dialog.setCanceledOnTouchOutside(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.alert_medicine_frequency);


                Button Ok = (Button) dialog.findViewById(R.id.btn_ok);
                ImageView clsImg = dialog.findViewById(R.id.close_img);
                final RadioButton one = dialog.findViewById(R.id.rdo_one);
                final RadioButton two = dialog.findViewById(R.id.rdo_two);
                final RadioButton three = dialog.findViewById(R.id.rdo_three);
                final RadioButton four = dialog.findViewById(R.id.rdo_four);

                final RadioGroup gendertype = dialog.findViewById(R.id.radioGroup1);

                gendertype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (i == R.id.rdo_one) {
                            selectedType = one.getText().toString();
                        } else if (i == R.id.rdo_two) {
                            selectedType = two.getText().toString();
                        } else if (i == R.id.rdo_three) {
                            selectedType = three.getText().toString();
                        } else if (i == R.id.rdo_four) {
                            selectedType = four.getText().toString();
                        }
                    }
                });

                Ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtFreq.setText(selectedType);
                        dialog.dismiss();
                    }
                });
                clsImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                dialog.show();

            }
        });
        edtDrtnFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(AddMedicationActivity.this,
                        R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        edtDrtnFrom.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                },


                        year, month, day);

                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.show();


            }
        });


        edtDrnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(AddMedicationActivity.this,
                        R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        edtDrnTo.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                        strDate = profileDob.getText().toString();
//                        if (view != null) {
//                            lnrlyt.setVisibility(View.VISIBLE);
//                        } else {
//                            lnrlyt.setVisibility(View.GONE);
//                        }
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
//                        String dayName=simpleDateFormat.format(profileDob);

                    }
                },


                        year, month, day);

                picker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                picker.show();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = edtName.getText().toString();
                String strDosage = edtDosage.getText().toString();
                String strDrtnFrom = edtDrtnFrom.getText().toString();
                String strDrnTo = edtDrnTo.getText().toString();
                String strFreq = edtFreq.getText().toString();

                int selectedId = radioGroupFood.getCheckedRadioButtonId();
                rdoFood = (RadioButton) findViewById(selectedId);
                if (selectedId == -1) {
                    Toast.makeText(AddMedicationActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = PreferenceManager
                            .getDefaultSharedPreferences(AddMedicationActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String strRdfood = rdoFood.getText().toString();
                    editor.putString(rdFood, strRdfood);
                    editor.apply();
                    Toast.makeText(AddMedicationActivity.this, rdoFood.getText(), Toast.LENGTH_SHORT).show();
                }

                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(AddMedicationActivity.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Name, strName);
                editor.putString(Dosage, strDosage);
                editor.putString(DrtnFrom, strDrtnFrom);
                editor.putString(DrnTo, strDrnTo);
                editor.putString(Freq, strFreq);

                editor.apply();
                Intent i = new Intent(AddMedicationActivity.this, MedicationActivity.class);
                startActivity(i);
            }
        });
    }

    public void init() {
        edtName = (EditText) findViewById(R.id.edtName);
        edtDosage = (EditText) findViewById(R.id.edtDosage);
        edtDrtnFrom = (EditText) findViewById(R.id.edtDurnFrom);
        edtDrnTo = (EditText) findViewById(R.id.edtDurnTo);
        edtFreq = (EditText) findViewById(R.id.edtFreq);

        radioGroupFood = (RadioGroup) findViewById(R.id.radioGroupFood);


        btnAdd = (Button) findViewById(R.id.btnAdd);
    }
}