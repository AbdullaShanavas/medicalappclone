package com.app.staycured.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.staycured.R;

public class HelpSupportActivity extends AppCompatActivity {

    TextView mailStaycure,callStayCure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_support);

        mailStaycure=(TextView) findViewById(R.id.mail);
        callStayCure=(TextView) findViewById(R.id.call);

        mailStaycure.setOnClickListener(new View.OnClickListener() {
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

        callStayCure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+919876541260"));
                startActivity(intent);

            }
        });


    }
}