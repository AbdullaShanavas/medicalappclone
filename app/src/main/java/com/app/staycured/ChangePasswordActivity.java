package com.app.staycured;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.staycured.Settings.SettingsActivity;

public class ChangePasswordActivity  extends AppCompatActivity {
    EditText edtOldPswd,edtNewPswd,edtrePswd;
    ImageView oldPswd,newPswd,reEnterPswd;

    Button savePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ImageView imgBack=findViewById(R.id.imgBack);
        TextView back=findViewById(R.id.txt_back);

        edtOldPswd= findViewById(R.id.edt_oldpswd);
        edtNewPswd= findViewById(R.id.edt_newpswd);
        edtrePswd= findViewById(R.id.edt_repswd);
        savePassword= findViewById(R.id.btn_save);


savePassword.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        Intent intent= new Intent(ChangePasswordActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
});

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
}

    public void ShowHidePass1(View view){

        if(view.getId()==R.id.img_show_pswd1){

            if(edtOldPswd.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                (( ImageView)(view)).setImageResource(R.drawable.ic_baseline_remove_red_eye_24);

//Show Password
                edtOldPswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.hideicon);

//Hide Password
                edtOldPswd.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }


    public void ShowHidePass2(View view){

        if(view.getId()==R.id.img_show_pswd2){

            if(edtNewPswd.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                (( ImageView)(view)).setImageResource(R.drawable.ic_baseline_remove_red_eye_24);

//Show Password
                edtNewPswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.hideicon);

//Hide Password
                edtNewPswd.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

    public void ShowHidePass3(View view){

        if(view.getId()==R.id.img_show_pswd3){

            if(edtrePswd.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                (( ImageView)(view)).setImageResource(R.drawable.ic_baseline_remove_red_eye_24);

//Show Password
                edtrePswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.hideicon);

//Hide Password
                edtrePswd.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

}
