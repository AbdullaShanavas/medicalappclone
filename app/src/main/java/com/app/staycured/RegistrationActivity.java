package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.AppointmentActivity;
import com.app.staycured.Database.DbHandler;
import com.app.staycured.Object.PatientDetailsObject;
import com.app.staycured.Object.RegistrationDetailsObject;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {
    private static Dialog dialog;
    private String selectedType = "";
    Button register;
    EditText dateOfBirth, email, password, gender;
    EditText edtFirstName, edtMiddleName, edtLastName, edtAddressline1, edtAddressline2, edtCity, edtState, edtCountry, edtZipcode, edtMobile;
    String strFirstName, strMiddleName, strLastName, strAdressline1, strAddresline2, strCity, strState, strCountry, strZipcode, strMobile, strDob, strEmail, strGender, strPwd;


    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registartion);
        init();
        db = new DbHandler(RegistrationActivity.this);

        gender.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub

                dialog = new Dialog(RegistrationActivity.this);
                dialog.setCanceledOnTouchOutside(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.alert_gender);
                Button yes = (Button) dialog.findViewById(R.id.btn_gender_ok);
                ImageView clsImg = dialog.findViewById(R.id.close_img);
                final RadioButton male = dialog.findViewById(R.id.rdo_male);
                final RadioButton female = dialog.findViewById(R.id.rdo_female);

                final RadioGroup gendertype = dialog.findViewById(R.id.radioGroup1);

                gendertype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (i == R.id.rdo_male) {
                            selectedType = male.getText().toString();
                        } else if (i == R.id.rdo_female) {
                            selectedType = female.getText().toString();
                        }
                    }
                });

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gender.setText(selectedType);
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


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strFirstName = edtFirstName.getText().toString();
                strMiddleName = edtMiddleName.getText().toString();
                strLastName = edtLastName.getText().toString();
                strAdressline1 = edtAddressline1.getText().toString();
                strAddresline2 = edtAddressline2.getText().toString();
                strCity = edtCity.getText().toString();
                strState = edtState.getText().toString();
                strCountry = edtCountry.getText().toString();
                strZipcode = edtZipcode.getText().toString();
                strMobile = edtMobile.getText().toString();
                strGender = gender.getText().toString();
                strDob = dateOfBirth.getText().toString();
                strPwd = password.getText().toString();
//                email.getText().toString().trim().matches(emailPattern);
                strEmail = email.getText().toString();
                if(email.getText().toString().isEmpty()||!email.getText().toString().trim().matches(emailPattern)) {

                    Toast.makeText(getApplicationContext(),"Enter valid email address", Toast.LENGTH_SHORT).show();
                } else if(password.getText().toString()
                        .isEmpty()||password.getText().toString().length()<6){
                    Toast.makeText(getApplicationContext(),"You must have 6 characters in your password", Toast.LENGTH_SHORT).show();
                }else  if (edtFirstName.getText().toString().length() == 0 && edtMiddleName.getText().toString().length() == 0 && edtLastName.getText().toString().length() == 0
                        && edtAddressline1.getText().toString().length() == 0&& edtAddressline2.getText().toString().length() == 0 && edtCity.getText().toString().length() == 0 &&
                        edtState.getText().toString().length() == 0 && edtCountry.getText().toString().length() == 0 && edtZipcode.getText().toString().length() == 0
                        && edtMobile.getText().toString().length() == 0 && gender.getText().toString().length() == 0
                        && dateOfBirth.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details correctly", Toast.LENGTH_SHORT).show();

                } else {
                    createRegistration(strFirstName,strMiddleName,strLastName,strAdressline1,strAddresline2,strCity,strState,strCountry,strZipcode,strMobile,strGender,strEmail,strDob,strPwd);
                    Toast.makeText(RegistrationActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);

                }

            }
        });

        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
// date picker dialog
                DatePickerDialog picker = new DatePickerDialog(RegistrationActivity.this,
                        R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateOfBirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                },


                        year, month, day);

                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.show();
            }
        });

    }

    public void ShowHidePass(View view) {

        if (view.getId() == R.id.img_show_pswd) {

            if (password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.ic_baseline_remove_red_eye_24);

//Show Password
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.hideicon);

//Hide Password
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

    private void init() {
        edtFirstName = (EditText) findViewById(R.id.edt_first_name);
        edtMiddleName = (EditText) findViewById(R.id.edt_Middle_Name);
        edtLastName = (EditText) findViewById(R.id.edt_Last_Name);
        edtAddressline1 = (EditText) findViewById(R.id.edt_Address_Line_1);
        edtAddressline2 = (EditText) findViewById(R.id.edt_Address_Line_2);
        edtCity = (EditText) findViewById(R.id.edt_City);
        edtState = (EditText) findViewById(R.id.edt_State);
        edtCountry = (EditText) findViewById(R.id.edt_Country);
        edtZipcode = (EditText) findViewById(R.id.edt_Zip_Code);
        edtMobile = (EditText) findViewById(R.id.edt_Mobile);
        dateOfBirth = (EditText) findViewById(R.id.edt_date_of_birth);
        gender = (EditText) findViewById(R.id.edt_gender);

        email = (EditText) findViewById(R.id.edt_Email);
        password = (EditText) findViewById(R.id.edt_password);
        register = (Button) findViewById(R.id.btn_register);
    }

    private void createRegistration(String strFirstName,String strMiddleName,String strLastName,String strAdressline1,String strAddresline2,String strCity,
                                    String strState,String strCountry,String strZipcode,String strMobile,String strGender,String strEmail,String strDob,String strPwd ) {

        RegistrationDetailsObject registrationDetailsObject = new RegistrationDetailsObject(strFirstName,strMiddleName,strLastName,
                strAdressline1,strAddresline2,strCity,strState,strCountry,strZipcode,strMobile,strGender,strEmail,strDob,strPwd);
        registrationDetailsObject.setFirstName(strFirstName);
        registrationDetailsObject.setMiddleName(strMiddleName);
        registrationDetailsObject.setLastName(strLastName);
        registrationDetailsObject.setAddressLine1(strAdressline1);
        registrationDetailsObject.setAddressLine2(strAddresline2);
        registrationDetailsObject.setCity(strCity);
        registrationDetailsObject.setState(strState);
        registrationDetailsObject.setCountry(strCountry);
        registrationDetailsObject.setZipCode(strZipcode);
        registrationDetailsObject.setMobile(strMobile);
        registrationDetailsObject.setGender(strGender);
        registrationDetailsObject.setEmail(strEmail);
        registrationDetailsObject.setDob(strDob);
        registrationDetailsObject.setPassword(strPwd);

        db.insertRegistration(RegistrationActivity.this, registrationDetailsObject);
//        db.GetAppoinmtmentList().add(patientDetailsObject);


    }
}