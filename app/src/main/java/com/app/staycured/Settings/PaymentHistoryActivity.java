package com.app.staycured.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.app.staycured.MyAppointmentActivity;
import com.app.staycured.MyAppointmentDetailsActivity;
import com.app.staycured.Object.Doctorlist;
import com.app.staycured.Object.PatientDetailsObject;
import com.app.staycured.Object.PaymentHistoryObject;
import com.app.staycured.R;
import com.app.staycured.geoutils.SessionData;

import java.util.ArrayList;

public class PaymentHistoryActivity extends AppCompatActivity {
    private  ListView lstPayHistory;

    ArrayList<PaymentHistoryObject> paymentHistoryArrayList = new ArrayList<>() ;
    PaymentHistoryObject paymentHistoryObject;
    MyAdapterPayment adapterPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        lstPayHistory=(ListView) findViewById(R.id.lst_paymentHistory);

        paymentHistoryArrayList.add(new PaymentHistoryObject("Dr Parneesh Arora", "05 sep 2020", "₹150"));
        paymentHistoryArrayList.add(new PaymentHistoryObject("Dr Kishore", " 08 sep 2020", "₹100"));
        paymentHistoryArrayList.add(new PaymentHistoryObject("Dr John Michael", " 09 sep 2020", "₹200"));
        paymentHistoryArrayList.add(new PaymentHistoryObject("Dr Arora venky", " 15 sep 2020", "₹250"));
        adapterPayment = new MyAdapterPayment(this, paymentHistoryArrayList);
        lstPayHistory.setAdapter(adapterPayment);


    }

    public class MyAdapterPayment extends BaseAdapter {
        private Context context;
        private ArrayList<PaymentHistoryObject> arrayList;
        private TextView drName, date, amount;


        public MyAdapterPayment(Context context, ArrayList<PaymentHistoryObject> arrayList) {
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


            convertView = LayoutInflater.from(context).inflate(R.layout.child_payment_history, parent, false);
            drName = convertView.findViewById(R.id.txtName);
            date = convertView.findViewById(R.id.txtDate);
            amount = convertView.findViewById(R.id.txtAmount);

            drName.setText(arrayList.get(position).getDocName());
            date.setText(arrayList.get(position).getDate());
            amount.setText(arrayList.get(position).getAmount());

            return convertView;



        }
    }
}