package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.app.staycured.Object.Doctorlist;
import com.app.staycured.Object.SpecialistObject;
import com.app.staycured.geoutils.SessionData;

import java.util.ArrayList;

public class FreqSpecialistActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<SpecialistObject> arrayList = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freq_specialist);
        ImageView imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        init();
        arrayList.add(new SpecialistObject(R.drawable.doc1,1, "Dr Parneesh Arora", "150", "4.6"));
        arrayList.add(new SpecialistObject(R.drawable.doc2,2, "Dr S P Gupta", "200", "4.5"));
        arrayList.add(new SpecialistObject(R.drawable.doc3,3, "Dr John Michael", " ₹120", "3.8"));
        arrayList.add(new SpecialistObject(R.drawable.doc4,4, "Dr Arora venky", " ₹140", "3.9"));
        arrayList.add(new SpecialistObject(R.drawable.doc5,5, "Dr David Johnson", " ₹125", "4.4"));
        arrayList.add(new SpecialistObject(R.drawable.doc6,6, "Dr Williamson", " ₹105", "4.1"));



        adapter = new MyAdapter(this, arrayList);
        listView.setAdapter(adapter);



    }


    public void init() {
        listView = (ListView) findViewById(R.id.listview);


    }

    public class MyAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<SpecialistObject> arrayList;
        private TextView slNo, drname, drfee,drrating;
        private ImageView imgspcialist;
        LinearLayout lnr_splt;

        public MyAdapter(Context context, ArrayList<SpecialistObject> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return  arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            convertView = LayoutInflater.from(context).inflate(R.layout.child_specialist, parent, false);


            imgspcialist=convertView.findViewById(R.id.img_spcialist);
            slNo = convertView.findViewById(R.id.sl_no);
            drname = convertView.findViewById(R.id.drName);
            drfee = convertView.findViewById(R.id.dr_fees);
            drrating = convertView.findViewById(R.id.dr_rating);
            lnr_splt = convertView.findViewById(R.id.lnr_splt);

            imgspcialist.setImageResource(arrayList.get(position).getSplst());
            slNo.setText(String.valueOf(arrayList.get(position).getSlno()));
            drname.setText(arrayList.get(position).getDrname());
            drfee.setText(String.valueOf(arrayList.get(position).getDrfee()));
            drrating.setText(arrayList.get(position).getDrrating());

            SessionData.getInstance().setDrName(arrayList.get(position).getDrname());
            SessionData.getInstance().setSpeciality("Cardiology");





            return convertView;



        }
    }




}