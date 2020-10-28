package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.staycured.geoutils.SessionData;

public class ConsultationActivity extends AppCompatActivity {
    Button btnCallDr;
    String number = "9489119145";
    private Thread mThread;
    private int callResume = 0;
    Button btnMedicalRecords, btnMedication;
    TextView txtDrName, txtPatientName, txtPatientSymp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
        init();
//        txtDrName.setText(SessionData.getInstance().getDrName());
//        txtPatientName.setText(SessionData.getInstance().getPatientName());
//        txtPatientSymp.setText(SessionData.getInstance().getPatientSymptoms());

        txtDrName.setText(SessionData.getInstance().getUpDrName());
        txtPatientName.setText(SessionData.getInstance().getUpPatientName());
        txtPatientSymp.setText(SessionData.getInstance().getUpPatientSymptoms());
        btnCallDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (callResume == 0) {
                    PackageManager pm = getPackageManager();
                    try {
                        Uri uri = Uri.parse("smsto:" + number);
                        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                        i.setPackage("com.whatsapp");
                        startActivity(Intent.createChooser(i, ""));
                        callResume = 1;
                    } catch (Exception e) {
                        Toast.makeText(ConsultationActivity.this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                                .show();
                    }

                }
            }
        });
        btnMedicalRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConsultationActivity.this, MedicalRecordsActivity.class);
                startActivity(i);
            }
        });
        btnMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConsultationActivity.this, MedicationActivity.class);
                startActivity(i);
            }
        });
    }

    private void init() {
        btnCallDr = (Button) findViewById(R.id.btn_Call_doctor);
        btnMedicalRecords = (Button) findViewById(R.id.btnMedicalRecords);
        btnMedication = (Button) findViewById(R.id.btnMedication);

        txtPatientName = (TextView) findViewById(R.id.txtPatientName);
        txtPatientSymp = (TextView) findViewById(R.id.txtPatientSymp);
        txtDrName = (TextView) findViewById(R.id.txtDrName);

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (callResume == 1) {
            Intent newInt = new Intent(this, CommentsActivity.class);
            startActivity(newInt);
            this.finish();

        }
    }
}