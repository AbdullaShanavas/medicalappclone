package com.app.staycured.Settings;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.app.staycured.BookmarkActitvity;
import com.app.staycured.FamilyMemberDetail;
import com.app.staycured.Object.AddAddressObject;
import com.app.staycured.Object.FamilyMember;
import com.app.staycured.R;
import com.app.staycured.geoutils.SessionData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ManageAddressActivity extends AppCompatActivity {
FloatingActionButton fabAddAdress;

    private ListView addressList;
    ArrayList<AddAddressObject> arrayList = new ArrayList<>();
   MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);
        addressList =findViewById(R.id.addressList);
        arrayList.add(new AddAddressObject("Ravi","22 MGR st\nchennai \nTamilnadu-641008"));
        arrayList.add(new AddAddressObject("Gupta","14 Sivaji Nagar\nchennai \nTamilnadu-641008"));
        adapter = new MyAdapter(this, arrayList);
        addressList.setAdapter(adapter);

        init();
        fabAddAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionData.getInstance().setClick(0);

                Intent i=new Intent(ManageAddressActivity.this,AddAdressActivity.class);
                startActivity(i);
            }
        });
    }
    private void init(){
        fabAddAdress=findViewById(R.id.fab_add);

    }
    public class MyAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<AddAddressObject> arrayList;
        private TextView name, address;
        private LinearLayout lstView;

        public MyAdapter(Context context, ArrayList<AddAddressObject> arrayList) {
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


            convertView = LayoutInflater.from(context).inflate(R.layout.child_add_address, parent, false);
            name = convertView.findViewById(R.id.txtName);
            address = convertView.findViewById(R.id.txtAddress);
            lstView=convertView.findViewById(R.id.lstView);
            lstView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SessionData.getInstance().setClick(1);
                    Intent i=new Intent(ManageAddressActivity.this, AddAdressActivity.class);
                    startActivity(i);
                }
            });



            return convertView;

        }

    }
}