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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.staycured.Object.Doctorlist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class BookmarkActitvity extends AppCompatActivity {
    private ListView listView;
    ArrayList<Doctorlist> arrayList = new ArrayList<>();
    MyAdapter adapter;
    LinearLayout lnrName, lnrfees, lnrRating;
    TextView txtName, txtFee, txtRating;
    ImageView imgName, imgFee, imgRating;
    int click = 0;
    Context context;
    EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_actitvity);
        ImageView imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        init();

        arrayList.add(new Doctorlist("Dr Parneesh Arora", "₹150", "4.2"));
        arrayList.add(new Doctorlist("Dr Kishore", " ₹180", "4.3"));
        arrayList.add(new Doctorlist("Dr John Michael", " ₹120", "3.8"));
        arrayList.add(new Doctorlist("Dr Arora venky", " ₹140", "3.9"));
        arrayList.add(new Doctorlist("Dr David Johnson", " ₹125", "3.4"));
        arrayList.add(new Doctorlist("Dr Williamson", " ₹105", "3.1"));


        adapter = new MyAdapter(this, arrayList);
        listView.setAdapter(adapter);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int textlength = s.length();
                ArrayList<Doctorlist> arrayList1 = new ArrayList<>();
                for (Doctorlist c : arrayList) {
                    if (textlength <= c.getName().length()) {
                        if (c.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                            arrayList1.add(c);
                        }else if(c.getFee().toLowerCase().contains(s.toString().toLowerCase())){
                            arrayList1.add(c);
                        }else if(c.getRating().toLowerCase().contains(s.toString().toLowerCase())){
                            arrayList1.add(c);
                        }
                    }
                }
                adapter = new MyAdapter(BookmarkActitvity.this, arrayList1);
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

    }


    public void init() {
        listView = (ListView) findViewById(R.id.listview);
        lnrName = (LinearLayout) findViewById(R.id.lnrName);
        lnrfees = (LinearLayout) findViewById(R.id.lnrfees);
        lnrRating = (LinearLayout) findViewById(R.id.lnrRating);
        txtName = (TextView) findViewById(R.id.txtname);
        txtFee = (TextView) findViewById(R.id.txtFee);
        txtRating = (TextView) findViewById(R.id.txtRating);
        imgName = (ImageView) findViewById(R.id.imgName);
        imgFee = (ImageView) findViewById(R.id.imgFee);
        imgRating = (ImageView) findViewById(R.id.imgRating);
        edtSearch = (EditText) findViewById(R.id.edtSearch);


    }

    public class MyAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<Doctorlist> arrayList;
        private TextView serialNum, name, drFees, drRating;

        public MyAdapter(Context context, ArrayList<Doctorlist> arrayList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.child_bookmark_activity, parent, false);
            LinearLayout lnrlyt=convertView.findViewById(R.id.lnrlyt);
            serialNum = convertView.findViewById(R.id.serailNumber);
            name = convertView.findViewById(R.id.drName);
            drFees = convertView.findViewById(R.id.txt_fees);
            serialNum.setText(String.valueOf(position + 1));
            name.setText(arrayList.get(position).getName());
            drFees.setText(arrayList.get(position).getFee());
            drRating = convertView.findViewById(R.id.dr_rating);
            drRating.setText(arrayList.get(position).getRating());
            lnrlyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i=new Intent(context,BookmarkDetailsActivity.class);
                    startActivity(i);
                }
            });

            return convertView;
        }

    }



    public static Comparator<Doctorlist> StringworkorderAscComparator =
            new Comparator<Doctorlist>() {

                public int compare(Doctorlist app1,
                                   Doctorlist app2) {

                    Doctorlist stringName1 = app1;
                    Doctorlist stringName2 = app2;

                    return stringName1.getName().compareToIgnoreCase(
                            stringName2.getName());
                }
            };
    public static Comparator<Doctorlist> StringworkorderDseComparator =
            new Comparator<Doctorlist>() {

                public int compare(Doctorlist app1,
                                   Doctorlist app2) {

                    Doctorlist stringName1 = app1;
                    Doctorlist stringName2 = app2;

                    return stringName2.getName().compareToIgnoreCase(
                            stringName1.getName());
                }
            };
    public static Comparator<Doctorlist> StringworkorderAscComparatorFees =
            new Comparator<Doctorlist>() {

                public int compare(Doctorlist app1,
                                   Doctorlist app2) {

                    Doctorlist stringName1 = app1;
                    Doctorlist stringName2 = app2;

                    return stringName1.getFee().compareToIgnoreCase(
                            stringName2.getFee());
                }
            };
    public static Comparator<Doctorlist> StringworkorderDseComparatorFees =
            new Comparator<Doctorlist>() {

                public int compare(Doctorlist app1,
                                   Doctorlist app2) {

                    Doctorlist stringName1 = app1;
                    Doctorlist stringName2 = app2;

                    return stringName2.getFee().compareToIgnoreCase(
                            stringName1.getFee());
                }
            };
    public static Comparator<Doctorlist> StringworkorderAscComparatorRating =
            new Comparator<Doctorlist>() {

                public int compare(Doctorlist app1,
                                   Doctorlist app2) {

                    Doctorlist stringName1 = app1;
                    Doctorlist stringName2 = app2;

                    return stringName1.getRating().compareToIgnoreCase(
                            stringName2.getRating());
                }
            };
    public static Comparator<Doctorlist> StringworkorderDseComparatorRating =
            new Comparator<Doctorlist>() {

                public int compare(Doctorlist app1,
                                   Doctorlist app2) {

                    Doctorlist stringName1 = app1;
                    Doctorlist stringName2 = app2;


                    return stringName2.getRating().compareToIgnoreCase(
                            stringName1.getRating());
                }
            };

}