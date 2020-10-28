package com.app;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.staycured.AppointmentSpecialityDetails;
import com.app.staycured.Database.DbHandler;
import com.app.staycured.Object.PatientDetailsObject;
import com.app.staycured.Object.TimingObject;
import com.app.staycured.R;
import com.app.staycured.geoutils.SessionData;
import com.app.staycured.util.ExpandableHeightGridView;

import java.util.ArrayList;
import java.util.Calendar;


public class AppointmentActivity extends AppCompatActivity {
    ExpandableHeightGridView gvMorning, gvAfternoon, gvEvening, gvNight;
    LinearLayout lnrlyt;
    TextView txtAppointment;

    ArrayList<String> morningTimeList = new ArrayList<>();
    ArrayList<String> afternoonTimeList = new ArrayList<>();
    ArrayList<String> eveningTimeList = new ArrayList<>();
    ArrayList<String> nightTimeList = new ArrayList<>();
    //    ArrayList<String> timeList = new ArrayList<>();
    MyAdapter adapterMor;
    MyAdapterAftr adapterAftr;
    MyAdaptEve adapEve;
    MyAdaptNight adaNight;
    Button btnSubmit;
    ImageView imgBack;

    EditText edtPatientName, edtSymptoms, edtDescription;

    String strPatientNme, strSymptoms, strDecreption, strTime, strDate;
    String strDrName, strSpecialist;


    private DbHandler db;
    TimingObject timingObject;
    String selectedItem;
    ArrayList<PatientDetailsObject>patientDetailsObjects;
    PatientDetailsObject patientDetailsObject;

    private int button=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        imgBack = findViewById(R.id.imgBack);
        final EditText profileDob = findViewById(R.id.edt_date);
        lnrlyt = (LinearLayout) findViewById(R.id.lnrlyt);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        edtPatientName = (EditText) findViewById(R.id.edt_patient_name);
        edtSymptoms = (EditText) findViewById(R.id.edt_symptoms);
        edtDescription = (EditText) findViewById(R.id.edtDescription);

        txtAppointment = (TextView) findViewById(R.id.txtAppointment);

        db = new DbHandler(AppointmentActivity.this);
        showListData();
        if(AppointmentSpecialityDetails.flag==1){
            lnrlyt.setVisibility(View.VISIBLE);
                button=1;
//            Intent intent = getIntent();
                for(int i=0;i<patientDetailsObjects.size();i++){
                    patientDetailsObject=patientDetailsObjects.get(i);
                    strPatientNme=((PatientDetailsObject)patientDetailsObject).getPatient();
                    strSymptoms=((PatientDetailsObject)patientDetailsObject).getSymptoms();
                    strDate=((PatientDetailsObject)patientDetailsObject).getDate();
                    strTime=((PatientDetailsObject)patientDetailsObject).getTime();
                    strDecreption=((PatientDetailsObject)patientDetailsObject).getDescription();


                    edtPatientName.setText(strPatientNme);
                    edtSymptoms.setText(strSymptoms);
                    edtDescription.setText(strDecreption);
                    profileDob.setText(strDate);


                    strDrName=SessionData.getInstance().getDrName();
                    strSpecialist="Cardiology";

                }



                btnSubmit.setText("update");


            AppointmentSpecialityDetails.flag=0;
        }


        if (SessionData.getInstance().getPos() == 1) {
            txtAppointment.setText("ReSchedule Appointment");
        }

//        lnrlyt.setVisibility(View.GONE);

        gvMorning = (ExpandableHeightGridView) findViewById(R.id.gridViewMorning);
        gvAfternoon = (ExpandableHeightGridView) findViewById(R.id.gridViewAfternoon);
        gvEvening = (ExpandableHeightGridView) findViewById(R.id.gridViewEvening);
        gvNight = (ExpandableHeightGridView) findViewById(R.id.gridViewNight);


        morningTimeList.add("9:00AM");
        morningTimeList.add("9.30AM");
        morningTimeList.add("9.45AM");
        morningTimeList.add("10.00AM");
        morningTimeList.add("10.15AM");
        morningTimeList.add("10.15AM");
        morningTimeList.add("10.15AM");
        morningTimeList.add("10.15AM");
        morningTimeList.add("10.15AM");

        afternoonTimeList.add("12:00PM");
        afternoonTimeList.add("12.15PM");
        afternoonTimeList.add("12.30PM");
        afternoonTimeList.add("12.45PM");

        eveningTimeList.add("4:00PM");
        eveningTimeList.add("4.30PM");
        eveningTimeList.add("4.45PM");
        eveningTimeList.add("5.00PM");
        eveningTimeList.add("5.30PM");

        nightTimeList.add("7:00PM");
        nightTimeList.add("7.15PM");
        nightTimeList.add("7.30PM");
        nightTimeList.add("7.45PM");
        nightTimeList.add("8.00PM");


        adapterMor = new MyAdapter(this, morningTimeList);
        adapterAftr = new MyAdapterAftr(this, afternoonTimeList);
        adapEve = new MyAdaptEve(this, eveningTimeList);
        adaNight = new MyAdaptNight(this, nightTimeList);


        gvMorning.setNumColumns(4);
        gvMorning.setExpanded(true);
        gvMorning.setAdapter(adapterMor);


        gvAfternoon.setNumColumns(4);
        gvAfternoon.setAdapter(adapterAftr);
        gvAfternoon.setExpanded(true);

        gvEvening.setNumColumns(4);
        gvEvening.setAdapter(adapEve);
        gvEvening.setExpanded(true);

        gvNight.setNumColumns(4);
        gvNight.setAdapter(adaNight);
        gvNight.setExpanded(true);


        gvMorning.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                gvAfternoon.setAdapter(adapterAftr);
                gvEvening.setAdapter(adapEve);
                gvNight.setAdapter(adaNight);
                selectedItem = parent.getItemAtPosition(position).toString();
                strTime = selectedItem;

//               timingObject.setTime(String.valueOf(position));


//                strTime =timingObject.getTime() ;

            }
        });
        gvAfternoon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gvMorning.setAdapter(adapterMor);
                gvEvening.setAdapter(adapEve);
                gvNight.setAdapter(adaNight);
//                Object obj = gvAfternoon.getAdapter().getItem(position);
//                timingObject.setTime(obj.toString());
//                strTime =timingObject.getTime() ;
//                timingObject.setTime(String.valueOf(position));
                selectedItem = parent.getItemAtPosition(position).toString();
                strTime = selectedItem;

            }
        });
        gvEvening.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                gvMorning.setAdapter(adapterMor);
                gvAfternoon.setAdapter(adapterAftr);

                gvNight.setAdapter(adaNight);
//                Object obj = gvEvening.getAdapter().getItem(position);
//                timingObject.setTime(obj.toString());
//                strTime =timingObject.getTime() ;
//                timingObject.setTime(String.valueOf(position));
                selectedItem = parent.getItemAtPosition(position).toString();
                strTime = selectedItem;

            }
        });
        gvNight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                gvMorning.setAdapter(adapterMor);
                gvAfternoon.setAdapter(adapterAftr);
                gvEvening.setAdapter(adapEve);
//                Object obj = gvNight.getAdapter().getItem(position);
//                timingObject.setTime(obj.toString());
//                strTime =timingObject.getTime() ;
//                timingObject.setTime(String.valueOf(position));
                selectedItem = parent.getItemAtPosition(position).toString();
                strTime = selectedItem;

            }

        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strDrName = SessionData.getInstance().getDrName();
                strSpecialist = "Cardiology";
                strPatientNme = edtPatientName.getText().toString();
                strSymptoms = edtSymptoms.getText().toString();
                strDecreption = edtDescription.getText().toString();
                if(button==0){
                    if (edtPatientName.getText().toString().length() == 0 && edtSymptoms.getText().toString().length() == 0 &&
                            edtDescription.getText().toString().length() == 0) {
                        Toast.makeText(AppointmentActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    } else {
                        createAppointment(strPatientNme, strSymptoms, strDecreption, strDate, strTime, strDrName, strSpecialist);
                        showListData();
                        Intent i = new Intent(AppointmentActivity.this, AppointmentSpecialityDetails.class);
                        startActivity(i);
                    }
                }else if(button==1){
                    if(AppointmentSpecialityDetails.flag==0){

                        if (edtPatientName.getText().toString().length() == 0 && edtSymptoms.getText().toString().length() == 0 &&
                                edtDescription.getText().toString().length() == 0) {
                            Toast.makeText(AppointmentActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                        } else {
//                            db.updatePatientDetails(strPatientNme, strSymptoms, strDecreption, strDate, strTime, strDrName, strSpecialist);
                        updateAppointment(strPatientNme, strSymptoms, strDecreption, strDate, strTime, strDrName, strSpecialist);
                            showListData();
                            Intent i = new Intent(AppointmentActivity.this, AppointmentSpecialityDetails.class);
                            startActivity(i);
                        }


                    }
                }




            }
        });


        profileDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(AppointmentActivity.this,
                        R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        profileDob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        strDate = profileDob.getText().toString();
                        if (view != null) {
                            lnrlyt.setVisibility(View.VISIBLE);
                        } else {
                            lnrlyt.setVisibility(View.GONE);
                        }

                    }
                },


                        year, month, day);

                picker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                picker.show();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void showListData(){

        patientDetailsObjects = db.getAllAppointmentList();


//        Toast.makeText(getApplicationContext(), String.valueOf(studentLists.size()),Toast.LENGTH_SHORT).show();


        if(patientDetailsObjects.size() != 0){
            for(int i=0; i< patientDetailsObjects.size(); i++){
                patientDetailsObject=patientDetailsObjects.get(i);
                strPatientNme=((PatientDetailsObject)patientDetailsObject).getPatient();
                strSymptoms=((PatientDetailsObject)patientDetailsObject).getSymptoms();
                strDate=((PatientDetailsObject)patientDetailsObject).getDate();
                strTime=((PatientDetailsObject)patientDetailsObject).getTime();
                strDecreption=((PatientDetailsObject)patientDetailsObject).getDescription();

            }
        }
    }

    private void createAppointment(String patientNme, String symptoms, String description, String date, String time, String drname, String speciality) {

        PatientDetailsObject patientDetailsObject = new PatientDetailsObject(patientNme, symptoms, description, date, time, drname, speciality);
        patientDetailsObject.setPatient(patientNme);
        patientDetailsObject.setSymptoms(symptoms);
        patientDetailsObject.setDescription(description);
        patientDetailsObject.setDate(date);
        patientDetailsObject.setTime(time);
        patientDetailsObject.setDrname(drname);
        patientDetailsObject.setSpeciality(speciality);

        db.insertAppointmentDetails(AppointmentActivity.this, patientDetailsObject);
//        db.GetAppoinmtmentList().add(patientDetailsObject);


    }
    private void updateAppointment(String patientNme, String symptoms, String description, String date, String time, String drname, String speciality) {

        PatientDetailsObject patientDetailsObject = new PatientDetailsObject(patientNme, symptoms, description, date, time, drname, speciality);
        patientDetailsObject.setPatient(patientNme);
        patientDetailsObject.setSymptoms(symptoms);
        patientDetailsObject.setDescription(description);
        patientDetailsObject.setDate(date);
        patientDetailsObject.setTime(time);
        patientDetailsObject.setDrname(drname);
        patientDetailsObject.setSpeciality(speciality);
        db.updatePatientDetails(patientDetailsObject);

//        db.updatePatientDetails(patientDetailsObject);
//        db.updatePatientDetails(strPatientNme, strSymptoms, strDecreption, strDate, strTime, strDrName, strSpecialist);
//        db.GetAppoinmtmentList().add(patientDetailsObject);


    }

    public class MyAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<String> arrayList;
        private TextView textView;

        public MyAdapter(Context context, ArrayList<String> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
//            return position;
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(context).inflate(R.layout.appoinment_gridview, parent, false);
            textView = convertView.findViewById(R.id.textView);
            textView.setText(arrayList.get(position));
            return convertView;
        }

    }

    public class MyAdapterAftr extends BaseAdapter {
        private Context context;
        private ArrayList<String> arrayList;
        private TextView textView;

        public MyAdapterAftr(Context context, ArrayList<String> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
//            return position;
            return arrayList.get(position);

        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(context).inflate(R.layout.appoinment_gridview, parent, false);
            textView = convertView.findViewById(R.id.textView);

            textView.setText(arrayList.get(position));
            return convertView;
        }

    }

    public class MyAdaptEve extends BaseAdapter {
        private Context context;
        private ArrayList<String> arrayList;
        private TextView textView;

        public MyAdaptEve(Context context, ArrayList<String> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
//            return position;
            return arrayList.get(position);

        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(context).inflate(R.layout.appoinment_gridview, parent, false);
            textView = convertView.findViewById(R.id.textView);
            textView.setText(arrayList.get(position));
            return convertView;
        }

    }

    public class MyAdaptNight extends BaseAdapter {
        private Context context;
        private ArrayList<String> arrayList;
        private TextView textView;

        public MyAdaptNight(Context context, ArrayList<String> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
//            return position;
            return arrayList.get(position);

        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(context).inflate(R.layout.appoinment_gridview, parent, false);
            textView = convertView.findViewById(R.id.textView);
            textView.setText(arrayList.get(position));
            return convertView;
        }

    }


}
