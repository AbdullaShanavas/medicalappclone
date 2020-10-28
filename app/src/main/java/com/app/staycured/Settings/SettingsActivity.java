package com.app.staycured.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.staycured.ChangePasswordActivity;
import com.app.staycured.R;

public class SettingsActivity extends AppCompatActivity {
    TextView changePswd,txtManageAddress,txtPaymentHistory,txtHelpSupport;
    TextView txtAbout,txtReminder,txtFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ImageView imgBack = findViewById(R.id.imgBack);
         txtManageAddress = findViewById(R.id.txt_manage_address);
        txtPaymentHistory = findViewById(R.id.txt_payment_history);
        txtHelpSupport = findViewById(R.id.txt_helpSupport);
        txtAbout = findViewById(R.id.txt_about);
        txtReminder = findViewById(R.id.txt_remainder);
        txtFeedback = findViewById(R.id.txt_feedback);

         changePswd = findViewById(R.id.txt_change_pswd);

         txtFeedback.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                 String aEmailList[] = { "contact@staycured@gmail.com"};
                 emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
                 emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My subject");
                 emailIntent.setType("plain/text");
                 emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "My message body.");

                 startActivity(emailIntent);
             }
         });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        txtReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingsActivity.this, ReminderActivity.class);
                startActivity(intent);
            }
        });
        txtAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingsActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
        changePswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
                startActivity(intent);

            }
        });
        txtManageAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingsActivity.this, ManageAddressActivity.class);
                startActivity(intent);

            }
        });
        txtPaymentHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingsActivity.this, PaymentHistoryActivity.class);
                startActivity(intent);

            }
        });
        txtHelpSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingsActivity.this,HelpSupportActivity.class);
                startActivity(i);
            }
        });


    }
}