package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.staycured.Database.DbHandler;
import com.app.staycured.Object.CommentsObject;
import com.app.staycured.Object.PastAppointmentObject;
import com.app.staycured.Object.PatientDetailsObject;
import com.app.staycured.Object.UpcommingAppointmentsObject;
import com.app.staycured.geoutils.SessionData;

import java.util.ArrayList;

public class MyAppointmentActivity extends AppCompatActivity {
    private ListView pastListView;
    private ListView upcomeListView;
    ArrayList<PastAppointmentObject> pastAppointmentArrayList = new ArrayList<>();
    PastAppointmentObject pastAppointmentObject;
    MyAdapterPast adapterPast;
    MyAdapterUpcoming adapterUpcoming;

    ArrayList<PatientDetailsObject>patientDetailsObjects;
    PatientDetailsObject patientDetailsObject;

    ArrayList<CommentsObject>commentsObjectArrayList;
    CommentsObject commentsObject;

    String drname,speciality,date,time;

    TextView txtUpcoming,txtPast;

    private int pos;
    private DbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointment);
        ImageView imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        pastListView = (ListView) findViewById(R.id.lst_post_appmnt);
        upcomeListView = (ListView) findViewById(R.id.lnr_upcomming_apmt);
        txtUpcoming = (TextView) findViewById(R.id.txtUpcoming);
        txtUpcoming.setVisibility(View.GONE);
        txtPast = (TextView) findViewById(R.id.txtPast);
        txtPast.setVisibility(View.GONE);




         db=new DbHandler(getApplicationContext());
        patientDetailsObjects= db.GetAppoinmtmentList();
//        for(int i=0;i<patientDetailsObjects.size();i++){
//            patientDetailsObject=patientDetailsObjects.get(i);
//            SessionData.getInstance().setDrName(patientDetailsObjects.get(i).getDrname());
//            SessionData.getInstance().setPatientName(patientDetailsObjects.get(i).getPatient());
//            SessionData.getInstance().setPatientSymptoms(patientDetailsObjects.get(i).getSymptoms());
//
//        }


        commentsObjectArrayList=db.GetCommentsRatings();
//        for(int i=0;i<commentsObjectArrayList.size();i++){
//            commentsObject=commentsObjectArrayList.get(i);
//            SessionData.getInstance().setPastDrName(commentsObjectArrayList.get(i).getDrname());
//            SessionData.getInstance().setPastPatientName(commentsObjectArrayList.get(i).getPatient());
//            SessionData.getInstance().setPastPatientSymptoms(commentsObjectArrayList.get(i).getSymptoms());
//            SessionData.getInstance().setComments(commentsObjectArrayList.get(i).getComments());
//            SessionData.getInstance().setRating(commentsObjectArrayList.get(i).getRating());
//        }


        adapterUpcoming = new MyAdapterUpcoming(this, patientDetailsObjects);
        upcomeListView.setAdapter(adapterUpcoming);



//        if(CommentsActivity.click==1){
            adapterPast = new MyAdapterPast(this, commentsObjectArrayList);
            pastListView.setAdapter(adapterPast);
//            upcomeListView.setVisibility(View.GONE);
//            CommentsActivity.click=0;
//            pos=1;
//        }

       Boolean patientDetailsAray=patientDetailsObjects.isEmpty();
       Boolean commentsAray=commentsObjectArrayList.isEmpty();
        if(patientDetailsAray==true){
            txtUpcoming.setVisibility(View.VISIBLE);
        }
        if(commentsAray==true){
            txtPast.setVisibility(View.VISIBLE);
        }


//        if(pos==0){
//          pastListView.setVisibility(View.GONE);
//          upcomeListView.setVisibility(View.VISIBLE);
//          txtPast.setVisibility(View.VISIBLE);
//        }else if(pos==1){
//            upcomeListView.setVisibility(View.GONE);
//
//            txtUpcoming.setVisibility(View.VISIBLE);
//            pastListView.setVisibility(View.VISIBLE);
//            pos=0;
//        }


    }





    public class MyAdapterUpcoming extends BaseAdapter {
        private Context context;
        private ArrayList<PatientDetailsObject> arrayList1;
        private TextView drName, drSpeciality, drApmtDate,drApmtTime;
        private Button btn_appointmentDetails;

        public MyAdapterUpcoming(Context context, ArrayList<PatientDetailsObject> arrayList) {
            this.context = context;
            this.arrayList1 = arrayList;
        }


        @Override
        public int getCount() {
            int size = 0;
            if (arrayList1 != null) {
                size = arrayList1.size();
            }

            return size;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            convertView = LayoutInflater.from(context).inflate(R.layout.child_up_appointments, parent, false);
            drName = convertView.findViewById(R.id.post_specialist_name1);
            drSpeciality = convertView.findViewById(R.id.post_speciality1);
            drApmtDate = convertView.findViewById(R.id.post_date1);
            drApmtTime = convertView.findViewById(R.id.post_time1);
            btn_appointmentDetails = convertView.findViewById(R.id.btn_appointmentDetails);
            btn_appointmentDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(MyAppointmentActivity.this,MyAppointmentDetailsActivity.class);
                    MyAppointmentDetailsActivity.click=0;
                        String docName=arrayList1.get(position).getDrname();
                        String patient=arrayList1.get(position).getPatient();
                        String symptom=arrayList1.get(position).getSymptoms();

                        SessionData.getInstance().setUpDrName(docName);
                        SessionData.getInstance().setUpPatientName(patient);
                        SessionData.getInstance().setUpPatientSymptoms(symptom);
                    startActivity(i);
                }
            });


            drName.setText(arrayList1.get(position).getDrname());
            drSpeciality.setText(arrayList1.get(position).getSpeciality());
            drApmtDate.setText(arrayList1.get(position).getDate());
            drApmtTime.setText(arrayList1.get(position).getTime());



//                drName.setText(SessionData.getInstance().getDrName());
//                drSpeciality.setText("Cardiology");
//                drApmtDate.setText(((PatientDetailsObject)patientDetailsObject).getDate());
//                drApmtTime.setText(((PatientDetailsObject)patientDetailsObject).getTime());




            return convertView;



        }
    }
    public class MyAdapterPast extends BaseAdapter {
        private Context context;
        private ArrayList<CommentsObject> arrayList;
        private TextView drName, drSpeciality, drApmtDate,drApmtTime;
        private Button review;


        public MyAdapterPast(Context context, ArrayList<CommentsObject> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }


        @Override
        public int getCount() {
            int size = 0;
            if (arrayList != null) {
                size = arrayList.size();
            }

            return size;
        }

        @Override
        public Object getItem(int position) {
            return (position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            convertView = LayoutInflater.from(context).inflate(R.layout.child_appointment, parent, false);
            drName = convertView.findViewById(R.id.post_specialist_name);
            drSpeciality = convertView.findViewById(R.id.post_speciality);
            drApmtDate = convertView.findViewById(R.id.post_date);
            drApmtTime = convertView.findViewById(R.id.post_time);


            drName.setText(arrayList.get(position).getDrname());
            drSpeciality.setText(arrayList.get(position).getSpeciality());
            drApmtDate.setText(arrayList.get(position).getDate());
            drApmtTime.setText(arrayList.get(position).getTime());

//            for(int i=0;i<arrayList.size();i++){
//                patientDetailsObject=arrayList.get(i);
//
//                drName.setText(SessionData.getInstance().getDrName());
//                drSpeciality.setText("Cardiology");
//                drApmtDate.setText(((PatientDetailsObject)patientDetailsObject).getDate());
//                drApmtTime.setText(((PatientDetailsObject)patientDetailsObject).getTime());
//
//            }

            review = (Button) convertView.findViewById(R.id.btn_review);
            review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(MyAppointmentActivity.this,MyAppointmentDetailsActivity.class);
                    MyAppointmentDetailsActivity.click=1;
                    String docName=arrayList.get(position).getDrname();
                    String patient=arrayList.get(position).getPatient();
                    String symptom=arrayList.get(position).getSymptoms();
                    String comments=arrayList.get(position).getComments();
                    String rating=arrayList.get(position).getRating();
                    SessionData.getInstance().setPastDrName(docName);
                    SessionData.getInstance().setPastPatientName(patient);
                    SessionData.getInstance().setPastPatientSymptoms(symptom);
                    SessionData.getInstance().setComments(comments);
                    SessionData.getInstance().setRating(rating);
                    startActivity(i);
                }
            });
            return convertView;



        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(MyAppointmentActivity.this,DashboardActivity.class);
        startActivity(i);
    }
}
