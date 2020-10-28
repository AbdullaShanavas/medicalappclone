package com.app.staycured;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.staycured.Object.FamilyMember;

import com.app.staycured.geoutils.SessionData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<FamilyMember> arrayList = new ArrayList<>();
    MyAdapter adapter;

    FloatingActionButton buttonFab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members);

        listView = (ListView) findViewById(R.id.fam_members);
        buttonFab= (FloatingActionButton)findViewById(R.id.flt_btn) ;

//        FloatingActionButton myFab = (FloatingActionButton) myView.findViewById(R.id.flt_btn);


        arrayList.add(new FamilyMember("Dr Parneesh Arora","Father"));
        arrayList.add(new FamilyMember("Dr S P Gupta","Cousin"));
//        arrayList.add(new SpecialistObject("3","Dr Parneesh Arora","150","4.6"));
//        arrayList.add(new SpecialistObject("4","Dr Parneesh Arora","150","4.6"));
//        adapter = MyAdapter(this, arrayList);

        adapter= new MyAdapter(this,arrayList);
        listView.setAdapter(adapter);


        buttonFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionData.getInstance().setClick(0);
                Intent intent=new Intent(FamilyMembersActivity.this, FamilyMemberDetail.class);
                startActivity(intent);
            }
        });

    }



    public class MyAdapter extends BaseAdapter {
        private Context context;
        LinearLayout famlist;
        private ArrayList<FamilyMember> arrayList;
        private TextView name, relationShip;

        public MyAdapter(Context context, ArrayList<FamilyMember> arrayList) {
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


            convertView = LayoutInflater.from(context).inflate(R.layout.child_family, parent, false);
            name = convertView.findViewById(R.id.txt_fm_name);
            relationShip = convertView.findViewById(R.id.txt_fm_rlship);
            famlist = convertView.findViewById(R.id.fam_list);


            name.setText(String.valueOf(arrayList.get(position).getName()));
            relationShip.setText(arrayList.get(position).getRlship());
            famlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SessionData.getInstance().setClick(1);
                    Intent i=new Intent(context, FamilyMemberDetail.class);
                    startActivity(i);
                }
            });
            return convertView;



        }

    }

}
