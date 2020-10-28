package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.app.staycured.Object.Doctorlist;
import com.app.staycured.Object.PatientDetailsObject;
import com.app.staycured.Object.SpecialistObject;
import com.app.staycured.geoutils.SessionData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SpecialistActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<SpecialistObject> arrayList = new ArrayList<>();
    MyAdapter adapter;
    LinearLayout lnrName,lnrfees,lnrRating;
    TextView txtName,txtFee,txtRating;
    ImageView imgName,imgFee,imgRating;
    EditText edtSearch;

    int click=0;

    String drName,drFee,drRating;
    int  drawable,sno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist);
        ImageView imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        listView = (ListView) findViewById(R.id.lst_specialist);

        lnrName = (LinearLayout) findViewById(R.id.lnr_name);
        lnrfees = (LinearLayout) findViewById(R.id.lnr_fee);
        lnrRating = (LinearLayout) findViewById(R.id.lnr_rating);
//        txtName=(TextView)findViewById(R.id.txtname);
//        txtFee=(TextView)findViewById(R.id.txtFee);
//        txtRating=(TextView)findViewById(R.id.txtRating);
        imgName = (ImageView) findViewById(R.id.img_name);
        imgFee = (ImageView) findViewById(R.id.img_fee);
        imgRating = (ImageView) findViewById(R.id.img_rating);
        edtSearch = (EditText) findViewById(R.id.edtSearch);


        arrayList.add(new SpecialistObject(R.drawable.doc1,1, "Dr Parneesh Arora", "150", "4.6"));
        arrayList.add(new SpecialistObject(R.drawable.doc2,2, "Dr S P Gupta", "200", "4.5"));
        arrayList.add(new SpecialistObject(R.drawable.doc3,3, "Dr John Michael", " ₹120", "3.8"));
        arrayList.add(new SpecialistObject(R.drawable.doc4,4, "Dr Arora venky", " ₹140", "3.9"));
        arrayList.add(new SpecialistObject(R.drawable.doc5,5, "Dr David Johnson", " ₹125", "4.4"));
        arrayList.add(new SpecialistObject(R.drawable.doc6,6, "Dr Williamson", " ₹105", "4.1"));

        adapter = new MyAdapter(this, arrayList);
        listView.setAdapter(adapter);
        Collections.sort(arrayList, StringworkorderDseComparatorRating);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                SpecialistObject selectedElement = arrayList.get(i);
//
//
//                Intent intent=new Intent(SpecialistActivity.this, SpecialistDetails.class);
//                startActivity(intent);
//
//
//
//            }
//        });



        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int textlength = s.length();
                ArrayList<SpecialistObject> arrayList1 = new ArrayList<>();
                for (SpecialistObject c : arrayList) {
                    if (textlength <= c.getDrname().length()) {
                        if (c.getDrname().toLowerCase().contains(s.toString().toLowerCase())) {
                            arrayList1.add(c);
                        }else if(c.getDrfee().toLowerCase().contains(s.toString().toLowerCase())){
                            arrayList1.add(c);
                        }else if(c.getDrrating().toLowerCase().contains(s.toString().toLowerCase())){
                            arrayList1.add(c);
                        }
                    }
                }
                adapter = new MyAdapter(SpecialistActivity.this, arrayList1);
                listView.setAdapter(adapter);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        lnrName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (click == 0) {
                    Collections.sort(arrayList, StringworkorderAscComparator);
                    Drawable res = getResources().getDrawable(R.drawable.ic_baseline_arrow_downward_24);
                    imgName.setImageDrawable(res);

                    click = 1;
                } else if (click == 1) {
                    Collections.sort(arrayList, StringworkorderDseComparator);
                    Drawable res = getResources().getDrawable(R.drawable.ic_baseline_arrow_upward_24);
                    imgName.setImageDrawable(res);
                    click = 0;

                }
                adapter.notifyDataSetChanged();

            }
        });
        lnrfees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (click == 0) {
                    Collections.sort(arrayList, StringworkorderAscComparatorFees);
                    Drawable res = getResources().getDrawable(R.drawable.ic_baseline_arrow_downward_24);
                    imgFee.setImageDrawable(res);
                    click = 1;
                } else if (click == 1) {
                    Collections.sort(arrayList, StringworkorderDseComparatorFees);
                    Drawable res = getResources().getDrawable(R.drawable.ic_baseline_arrow_upward_24);
                    imgFee.setImageDrawable(res);
                    click = 0;

                }
                adapter.notifyDataSetChanged();

            }
        });
        lnrRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (click == 0) {

                    Collections.sort(arrayList, StringworkorderAscComparatorRating);
                    Drawable res = getResources().getDrawable(R.drawable.ic_baseline_arrow_downward_24);
                    imgRating.setImageDrawable(res);
                    click = 1;
                } else if (click == 1) {

                    Collections.sort(arrayList, StringworkorderDseComparatorRating);
                    Drawable res = getResources().getDrawable(R.drawable.ic_baseline_arrow_upward_24);
                    imgRating.setImageDrawable(res);
                    click = 0;

                }
                adapter.notifyDataSetChanged();

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3)
            {
                String selectedFromList =(String) (listView.getItemAtPosition(position).toString());

                SessionData.getInstance().setPos(position);
                SpecialistObject specialistObject=arrayList.get(position);
                drName=specialistObject.getDrname();
                SessionData.getInstance().setDrName(drName);

//                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();

                if (SessionData.getInstance().getClick1() == 0) {

                    Intent i = new Intent(SpecialistActivity.this, ChatActivity.class);
                    startActivity(i);
                } else {

                    Intent i = new Intent(SpecialistActivity.this, SpecialistDetails.class);
                    startActivity(i);

                }

            }
        });

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

    public static Comparator<SpecialistObject> StringworkorderAscComparator =
            new Comparator<SpecialistObject>() {

                public int compare(SpecialistObject app1,
                                   SpecialistObject app2) {

                    SpecialistObject stringName1 = app1;
                    SpecialistObject stringName2 = app2;

                    return stringName1.getDrname().compareToIgnoreCase(
                            stringName2.getDrname());
                }
            };
    public static Comparator<SpecialistObject> StringworkorderDseComparator =
            new Comparator<SpecialistObject>() {

                public int compare(SpecialistObject app1,
                                   SpecialistObject app2) {

                    SpecialistObject stringName1 = app1;
                    SpecialistObject stringName2 = app2;

                    return stringName2.getDrname().compareToIgnoreCase(
                            stringName1.getDrname());
                }
            };
    public static Comparator<SpecialistObject> StringworkorderAscComparatorFees =
            new Comparator<SpecialistObject>() {

                public int compare(SpecialistObject app1,
                                   SpecialistObject app2) {

                    SpecialistObject stringName1 = app1;
                    SpecialistObject stringName2 = app2;

                    return stringName1.getDrfee().compareToIgnoreCase(
                            stringName2.getDrfee());
                }
            };
    public static Comparator<SpecialistObject> StringworkorderDseComparatorFees =
            new Comparator<SpecialistObject>() {

                public int compare(SpecialistObject app1,
                                   SpecialistObject app2) {

                    SpecialistObject stringName1 = app1;
                    SpecialistObject stringName2 = app2;

                    return stringName2.getDrfee().compareToIgnoreCase(
                            stringName1.getDrfee());
                }
            };
    public static Comparator<SpecialistObject> StringworkorderAscComparatorRating =
            new Comparator<SpecialistObject>() {

                public int compare(SpecialistObject app1,
                                   SpecialistObject app2) {

                    SpecialistObject stringName1 = app1;
                    SpecialistObject stringName2 = app2;

                    return stringName1.getDrrating().compareToIgnoreCase(
                            stringName2.getDrrating());
                }
            };
    public static Comparator<SpecialistObject> StringworkorderDseComparatorRating =
            new Comparator<SpecialistObject>() {

                public int compare(SpecialistObject app1,
                                   SpecialistObject app2) {

                    SpecialistObject stringName1 = app1;
                    SpecialistObject stringName2 = app2;


                    return stringName2.getDrrating().compareToIgnoreCase(
                            stringName1.getDrrating());
                }
            };



}
