package com.app.staycured;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity {

    private static Dialog dialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pswd);

        ImageView imgBack=findViewById(R.id.imgBack);
        Button done=findViewById(R.id.btn_done);
        TextView back=findViewById(R.id.txt_view_back);



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
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new Dialog(ForgetPasswordActivity.this);
                dialog.setCanceledOnTouchOutside(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.alert_dialog_paid);
                TextView title = (TextView) dialog.findViewById(R.id.txt_hdr);
                TextView content = (TextView) dialog.findViewById(R.id.txt_content);
                Button yes = (Button) dialog.findViewById(R.id.btnOk);

                title.setText("Alert");
                content.setText("Successfully OTP Sent to your registerd E-mail address");

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ForgetPasswordActivity.this, MainActivity.class);
                        startActivity(i);
                        dialog.dismiss();
                    }
                });


                dialog.show();

            }
        });

}
}

