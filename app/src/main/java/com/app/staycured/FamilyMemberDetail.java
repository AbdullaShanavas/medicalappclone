package com.app.staycured;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.staycured.geoutils.SessionData;


public class FamilyMemberDetail extends AppCompatActivity {

    Button addBtn;
    LinearLayout lnrdetails;
    TextView txthdr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_member_detail);
        txthdr = (TextView) findViewById(R.id.txt_hdr);

        addBtn = (Button) findViewById(R.id.btn_add);
        lnrdetails = (LinearLayout) findViewById(R.id.lnr_fm_details);

        if(SessionData.getInstance().getClick()==0) {
            txthdr.setText("Add Family Member");
            lnrdetails.setVisibility(View.GONE);

        } else{

            addBtn.setVisibility(View.GONE);
        }

    }
}