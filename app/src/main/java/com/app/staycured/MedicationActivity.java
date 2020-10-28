package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.app.staycured.Object.Doctorlist;
import com.app.staycured.Object.MedicationObject;

import java.util.ArrayList;

public class MedicationActivity extends AppCompatActivity {
Button btn_register;
ListView listView;
  MyAdapter adapter;
    ArrayList<MedicationObject> arrayList = new ArrayList<>();
    public static final String Name = "Dola650";
    public static final String Dosage = "emailKey";
    public static final String DrtnFrom = "2020-08-03";
    public static final String DrnTo = "2021-02-05";
    public static final String Freq = "8.00AM 12.00PM 4.00 pm";
    public static final String rdFood = "Before Food";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        ImageView imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        init();
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        String name = sharedPreferences.getString(Name, "Dola650 ");
        String  dosage= sharedPreferences.getString(Dosage, " emailKey");
        String drtnFrom = sharedPreferences.getString(DrtnFrom, "2020-08-03 ");
        String drnTo = sharedPreferences.getString(DrnTo, "2021-02-05 ");
        String freq = sharedPreferences.getString(Freq, " 8.00AM 12.00PM 4.00 pm");
        String rdfood = sharedPreferences.getString(rdFood, "Before Food ");


        arrayList.add(new MedicationObject(name,dosage,drtnFrom,drnTo,freq,rdfood));


        adapter = new MyAdapter(this, arrayList);
        listView.setAdapter(adapter);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MedicationActivity.this,AddMedicationActivity.class);
                startActivity(i);
            }
        });
    }
    public void init(){
        btn_register=(Button)findViewById(R.id.btn_register);
        listView=(ListView)findViewById(R.id.listView);


    }
    public class MyAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<MedicationObject> arrayList;
        private TextView name, durationFrom, durationTo,rdFood,frequency;

        public MyAdapter(Context context, ArrayList<MedicationObject> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return arrayList.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_medication_list, parent, false);
            name = convertView.findViewById(R.id.txtName);
            durationFrom = convertView.findViewById(R.id.durationFrom);
            durationTo = convertView.findViewById(R.id.duartionTo);
            rdFood = convertView.findViewById(R.id.rdFood);
            frequency = convertView.findViewById(R.id.frequency);


            name.setText(arrayList.get(position).getDosage());
            durationFrom.setText(arrayList.get(position).getDurationFrom());
            durationTo.setText(arrayList.get(position).getDurationTo());
            rdFood.setText(arrayList.get(position).getRdFood());
            frequency.setText(arrayList.get(position).getFrequency());



            return convertView;
        }

    }
}