package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.AppointmentActivity;
import com.app.staycured.Database.DbHandler;
import com.app.staycured.Object.CommentsObject;
import com.app.staycured.Object.PastAppointmentObject;
import com.app.staycured.Object.PatientDetailsObject;
import com.app.staycured.geoutils.SessionData;

import java.util.ArrayList;

public class
MyAppointmentDetailsActivity extends AppCompatActivity {
    Button btnStart;
    ImageView imgBack;
   public static RelativeLayout rlAppointment;
  public static  LinearLayout lnrRating;
  public static int click;
  EditText edtComments;
  TextView txtRating;
  ArrayList<CommentsObject>commentsObjectArrayList=new ArrayList<>();
  CommentsObject commentsObject;
  String strComments,strRatings;
  private int i;
  Button RescheduleAppointment,btnCancel;
    TextView txtPatientName,txtPatientSymp,txtDrName;
    PatientDetailsObject patientDetailsObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointment_details);
        init();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MyAppointmentDetailsActivity.this,ConsultationActivity.class);
                startActivity(i);
            }
        });
        if(click==0){
            rlAppointment.setVisibility(View.VISIBLE);
            lnrRating.setVisibility(View.GONE);
            txtDrName.setText(SessionData.getInstance().getUpDrName());
            txtPatientName.setText(SessionData.getInstance().getUpPatientName());
            txtPatientSymp.setText(SessionData.getInstance().getUpPatientSymptoms());


        }else if(click==1){
            rlAppointment.setVisibility(View.GONE);
            lnrRating.setVisibility(View.VISIBLE);
            txtDrName.setText(SessionData.getInstance().getPastDrName());
            txtPatientName.setText(SessionData.getInstance().getPastPatientName());
            txtPatientSymp.setText(SessionData.getInstance().getPastPatientSymptoms());
            edtComments.setText(SessionData.getInstance().getComments());
            txtRating.setText(SessionData.getInstance().getRating());
        }

//
//        DbHandler db=new DbHandler(MyAppointmentDetailsActivity.this);
//        commentsObjectArrayList= db.GetCommentsRatings();
//
//        edtComments.setText(commentsObjectArrayList.get(i).getComments());
//        txtRating.setText(commentsObjectArrayList.get(i).getRating());

//        for(int i=0;i<commentsObjectArrayList.size();i++){
//            commentsObject=commentsObjectArrayList.get(i);
//            strComments=(commentsObject).getComments();
//            strRatings=(commentsObject).getRating();
//            edtComments.setText(commentsObjectArrayList.get(i).getComments());
//            txtRating.setText(commentsObjectArrayList.get(i).getRating());


//            edtComments.setText(((CommentsObject)commentsObject).getComments());
//            txtRating.setText(((CommentsObject)commentsObject).getRating());

//        }

        RescheduleAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MyAppointmentDetailsActivity.this, AppointmentActivity.class);
                startActivity(i);
                SessionData.getInstance().setPos(1);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MyAppointmentDetailsActivity.this,DashboardActivity.class);
                startActivity(i);
            }
        });


    }

    private void init() {
         imgBack = findViewById(R.id.imgBack);
         btnStart =(Button) findViewById(R.id.btnStart);
         rlAppointment=(RelativeLayout)findViewById(R.id.rlApointment);
         lnrRating=(LinearLayout)findViewById(R.id.lnr_rating);

        edtComments=(EditText)findViewById(R.id.edtComments);
        txtRating=(TextView)findViewById(R.id.txt_rating);
        RescheduleAppointment=(Button) findViewById(R.id.btn_rescheduleAppointment);
        btnCancel=(Button) findViewById(R.id.btnCancel);
        txtPatientName=(TextView) findViewById(R.id.txtPatientName);
        txtPatientSymp=(TextView) findViewById(R.id.txtPatientSymp);
        txtDrName=(TextView) findViewById(R.id.txtDrName);


    }
}