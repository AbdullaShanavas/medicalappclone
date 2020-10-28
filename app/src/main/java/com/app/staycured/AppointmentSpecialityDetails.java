package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.AppointmentActivity;
import com.app.staycured.Database.DbHandler;
import com.app.staycured.Object.PatientDetailsObject;
import com.app.staycured.geoutils.SessionData;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;

public class AppointmentSpecialityDetails extends AppCompatActivity implements PaymentResultListener {
    Button btnConfirm,btnEdit,btnCancel;
    ImageView imgBack;
    TextView txtPatientName,txtSymptoms,txtDate,txtTime,txtPayment,txtDescription;
    String StrPatientName,StrSymptoms,StrDate,StrTime,StrPayment,StrDescription;
    private static Dialog dialog;
ArrayList<PatientDetailsObject>patientDetailsObjects;
    PatientDetailsObject patientDetailsObject;
    private int i;
    DbHandler db;
    public static int flag=0;
    private String razorpayKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_speciality_details);
        txtPatientName=(TextView)findViewById(R.id.txtPatientName);
        txtSymptoms=(TextView)findViewById(R.id.txtSymptoms);
        txtDate=(TextView)findViewById(R.id.txtDate);
        txtTime=(TextView)findViewById(R.id.txtTime);
        txtPayment=(TextView)findViewById(R.id.txtPayment);
        txtDescription=(TextView)findViewById(R.id.txtDescription);
        btnEdit=(Button) findViewById(R.id.btnEdit);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        StrPayment="150";

         db=new DbHandler(AppointmentSpecialityDetails.this);
        patientDetailsObjects= db.getAllAppointmentList();

        for(int i=0;i<patientDetailsObjects.size();i++){
            patientDetailsObject=patientDetailsObjects.get(i);
            StrPatientName=((PatientDetailsObject)patientDetailsObject).getPatient();
            StrSymptoms=((PatientDetailsObject)patientDetailsObject).getSymptoms();
            StrDate=((PatientDetailsObject)patientDetailsObject).getDate();
            StrTime=((PatientDetailsObject)patientDetailsObject).getTime();
            StrDescription=((PatientDetailsObject)patientDetailsObject).getDescription();


            txtPatientName.setText(StrPatientName);
            txtSymptoms.setText(StrSymptoms);
            txtDate.setText(StrDate);
            txtTime.setText(StrTime);
            txtDescription.setText(StrDescription);


        }
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag=1;

                    Intent startIntent=new Intent(AppointmentSpecialityDetails.this, AppointmentActivity.class);
                    startActivity(startIntent);

                }
            });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AppointmentSpecialityDetails.this,DashboardActivity.class);
                startActivity(i);
            }
        });

                imgBack = (ImageView) findViewById(R.id.imgBack);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        Checkout.preload(getApplicationContext());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(txtPayment.getText().toString().equals(null) || txtPayment.getText().toString().equals("")){
//                    txtPayment.setError("Please Fillup");
//                }else if(Integer.parseInt(txtPayment.getText().toString())==0){
//                    txtPayment.setError("Amount should be greater than 0"); //Razorpay min amount is 1 Rs.
//                }else{
//                    //you have to convert Rs. to Paisa using multiplication of 100
//                    String convertedAmount=String.valueOf(Integer.parseInt(txtPayment.getText().toString())*100);
                    startPayment();
//                }
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=1;
                Intent startIntent=new Intent(AppointmentSpecialityDetails.this, AppointmentActivity.class);
                startActivity(startIntent);
            }
        });
    }


    private void startPayment() {

        final Activity activity = this;
        Checkout checkout = new Checkout();
        razorpayKey="cdd93492b4ecf08afd3e3d13deca215074c0bd69"; //Generate your razorpay key from Settings-> API Keys-> copy Key Id
//        razorpayKey="34038364c73f2e0b92f0c164e83043"; //Generate your razorpay key from Settings-> API Keys-> copy Key Id
        checkout.setKeyID(razorpayKey);
        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */

        JSONObject options = new JSONObject();

        try {

            options.put("name", "StayCured");
            options.put("description", "Consultation payment");
            options.put("image", "https://firebasestorage.googleapis.com/v0/b/webserver-bcae7.appspot.com/o/staycuredsmall.png?alt=media&token=28ae437e-a598-44d5-91e0-41cda63da838");
            options.put("currency", "INR");
            options.put("amount", "15000");//pass amount in currency subunits

            checkout.open(activity, options);
        } catch (Exception e) {
//            Log.e(TAG, "Error in starting Razorpay Checkout", e);
            Toast.makeText(activity, "Expeption :" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String rasorpayid) {
        dialog = new Dialog(AppointmentSpecialityDetails.this);
        dialog.setCanceledOnTouchOutside(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_paid);
        dialog.setCanceledOnTouchOutside(false);
        Button yes = (Button) dialog.findViewById(R.id.btnOk);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AppointmentSpecialityDetails.this, DashboardActivity.class);
                startActivity(i);
                dialog.dismiss();
            }
        });


        dialog.show();
    }


    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Expeption :" + s, Toast.LENGTH_SHORT).show();
    }

}