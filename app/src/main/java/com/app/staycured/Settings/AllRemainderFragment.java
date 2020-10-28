package com.app.staycured.Settings;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.app.staycured.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllRemainderFragment extends Fragment {

FloatingActionButton fltBtn;
    public AllRemainderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_remainder_list, container, false);

        fltBtn=view.findViewById(R.id.flt_btn);
        fltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getActivity(),MedicineDetailsActivity.class);
                startActivity(i);

            }
        });
        return view;


    }


}

