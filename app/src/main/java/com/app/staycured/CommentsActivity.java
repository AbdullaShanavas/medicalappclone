package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.app.staycured.Database.DbHandler;
import com.app.staycured.Object.CommentsObject;
import com.app.staycured.Object.PatientDetailsObject;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {
    Button btnSubmit,btnCancel;
    EditText edtComments;
    String comments;
     DbHandler db;
    String rating;
//    public static int click;
    ArrayList<PatientDetailsObject> patientDetailsObjects;
    PatientDetailsObject patientDetailsObject;
    String StrPatientName,StrSymptoms,StrDate,StrTime,StrDescription,
    strDrName,strSpecialist;
   private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);


        db = new DbHandler(CommentsActivity.this);
        patientDetailsObjects= db.GetAppoinmtmentList();

        btnSubmit=(Button)findViewById(R.id.btn_submit);
        btnCancel=(Button)findViewById(R.id.btn_cancel);
        edtComments=(EditText)findViewById(R.id.edtComments);


        final RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.rating_Bar);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(click==0){

                for(int i=0;i<patientDetailsObjects.size();i++) {
                    patientDetailsObject=patientDetailsObjects.get(i);
                    StrPatientName = patientDetailsObjects.get(i).getPatient();
                    StrSymptoms = (patientDetailsObjects).get(i).getSymptoms();
                    StrDate = (patientDetailsObjects).get(i).getDate();
                    StrTime = (patientDetailsObjects).get(i).getTime();
                    StrDescription = (patientDetailsObjects).get(i).getDescription();
                    strDrName = patientDetailsObjects.get(i).getDrname();
                    strSpecialist = patientDetailsObjects.get(i).getSpeciality();



                }
                    comments =edtComments.getText().toString();
                    rating = String.valueOf(simpleRatingBar.getRating());
                    createCommentRating(comments,rating,StrPatientName, StrSymptoms, StrDescription,StrDate, StrTime,strDrName,strSpecialist);
                db.DeleteDetails(patientDetailsObject);



//                    db.DeleteDetails(patientDetailsObject);
                    Intent i=new Intent(CommentsActivity.this,MyAppointmentActivity.class);
                    startActivity(i);
//                    click=1;
//                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i=new Intent(CommentsActivity.this,DashboardActivity.class);
                    startActivity(i);
            }
        });
    }
    private void createCommentRating(String comment, String rating,String patientNme,
                                     String symptoms, String description, String date, String time,String drname,String speciality) {

        CommentsObject commentsObject=new CommentsObject();
        commentsObject.setComments(comment);
        commentsObject.setRating(rating);
        commentsObject.setPatient(patientNme);
        commentsObject.setSymptoms(symptoms);
        commentsObject.setDescription(description);
        commentsObject.setDate(date);
        commentsObject.setDrname(drname);
        commentsObject.setSpeciality(speciality);
        commentsObject.setTime(time);
        db.insertCommentsRating(CommentsActivity.this,commentsObject);
//        db.GetAppoinmtmentList().add(patientDetailsObject);




    }
}