package com.app.staycured;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.app.staycured.Database.DbHandler;
import com.app.staycured.Object.PatientDetailsObject;
import com.app.staycured.Object.RegistrationDetailsObject;
import com.app.staycured.geoutils.SessionData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    EditText edtEmail, edtPassword;
    Button btnSignIn;
    TextView txtRegScreen,txtfrgtpswd;
//    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ArrayList<RegistrationDetailsObject>registrationDetailsArrayList;
    RegistrationDetailsObject registrationDetailsObject;
    DbHandler db;
    String strEmail,strPwd;
    ToggleButton rememberMe;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPrefsEditor;
    String email,password;

    Boolean saveLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);



//        if(rememberMe.isChecked()){
//                        if (sharedpreferences.contains(Email)) {
//                edtEmail.setText(sharedpreferences.getString(Email, ""));
//            }
//            if (sharedpreferences.contains(Password)) {
//                edtPassword.setText(sharedpreferences.getString(Password, ""));
//
//        }
//
//        }else{
//            edtEmail.setText("");
//            edtPassword.setText("");
//        }
        if (saveLogin) {
            edtEmail.setText(loginPreferences.getString("username",""));
            edtPassword.setText(loginPreferences.getString("password",""));
            rememberMe.setChecked(true);
        }
        db=new DbHandler(MainActivity.this);
        registrationDetailsArrayList=db.registrationDetailsObjectArrayList();
        for(int i=0;i<registrationDetailsArrayList.size();i++){
            registrationDetailsObject=registrationDetailsArrayList.get(i);
            strEmail=((RegistrationDetailsObject)registrationDetailsObject).getEmail();
            strPwd=((RegistrationDetailsObject)registrationDetailsObject).getPassword();
        }


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEmail.getText().toString().isEmpty()&&!edtEmail.getText().toString().trim().matches(strEmail)) {

                    Toast.makeText(getApplicationContext(),"please enter valid email", Toast.LENGTH_SHORT).show();
                }else if(edtPassword.getText().toString()
                .isEmpty()&&!edtPassword.getText().toString().trim().matches(strPwd)){
                    Toast.makeText(getApplicationContext(),"invalid password", Toast.LENGTH_SHORT).show();
                }else

                    if (edtEmail.getText().toString().trim().matches(strEmail)&&edtPassword.getText().toString().trim().matches(strPwd)) {
                        email=edtEmail.getText().toString();
                        password=edtPassword.getText().toString();
//                        String mail  = edtEmail.getText().toString();
//                        String pwd= edtPassword.getText().toString();
//                        SharedPreferences.Editor editor = sharedpreferences.edit();
//                        editor.putString(Email, mail);
//                        editor.putString(Password,pwd);
                        Intent intent=new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(intent);


                    } else{
                        Toast.makeText(getApplicationContext(), "Invalid login credentials", Toast.LENGTH_SHORT).show();
                    }
                if (rememberMe.isChecked()){
                    loginPrefsEditor.putBoolean("saveLogin",true);
                    loginPrefsEditor.putString("username",email);
                    loginPrefsEditor.putString("password",password);
                    loginPrefsEditor.commit();
                }else {
                    loginPrefsEditor.putBoolean("saveLogin",false);
                    loginPrefsEditor.putString("username","");
                    loginPrefsEditor.putString("password","");
                    loginPrefsEditor.commit();
                }


            }
        });

        txtRegScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });

        txtfrgtpswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this, ForgetPasswordActivity.class);
                startActivity(i);

            }
        });
    }

    public void init() {
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPwd);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        txtRegScreen=(TextView)findViewById(R.id.txtRegister);
        txtfrgtpswd=(TextView)findViewById(R.id.txt_frgt_pswd);
        rememberMe=(ToggleButton) findViewById(R.id.remember_me);
//        ShowHidePass=(ImageView)findViewById(R.id.show_pass_btn);

    }
    public void ShowHidePass(View view){

        if(view.getId()==R.id.show_pass_btn){

            if(edtPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                (( ImageView)(view)).setImageResource(R.drawable.ic_baseline_remove_red_eye_24);

                //Show Password
                edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.hideicon);

                //Hide Password
                edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }


}