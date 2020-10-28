package com.app.staycured.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.staycured.R;
import com.app.staycured.geoutils.SessionData;

public class AddAdressActivity extends AppCompatActivity {
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress);

        addBtn = (Button) findViewById(R.id.btn_add);

        if(SessionData.getInstance().getClick()==0) {
            addBtn.setText("Add Adress");

        } else{
            addBtn.setText("Update");
        }

    }
}