package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.staycured.geoutils.SessionData;

import org.w3c.dom.Text;

public class MyQuestionActivity extends AppCompatActivity {
EditText edtQuestion;
ImageView imgBack;
TextView txtBack;
Button btnSpecialty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question);
        ImageView imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        init();
        btnSpecialty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = edtQuestion.getText().toString();

                SessionData.getInstance().setQuestion(value);

                SessionData.getInstance().setClick1(0);


                Intent i=new Intent(MyQuestionActivity.this, SpecialityActivity.class);
                startActivity(i);
            }
        });
    }


    public void init(){
        edtQuestion=(EditText)findViewById(R.id.edtQuestion);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        txtBack=(TextView)findViewById(R.id.txtBack);
        btnSpecialty=(Button)findViewById(R.id.btnSpecialty);

    }
}