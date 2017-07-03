package com.example.nikhil.trackexpense;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity   {


    EditText username,password;
    Button login;
    String uname,pass;
    TextView textView,error;
    int flag=0;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username= (EditText) findViewById(R.id.editudername);
        password =(EditText) findViewById(R.id.editpassword);
        textView=(TextView)findViewById(R.id.textView2);
        error=(TextView)findViewById(R.id.textViewerror);

        final BackGroundTask backGroundTask = new BackGroundTask(this);


        login = (Button) findViewById(R.id.loginbutton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uname=username.getText().toString();
                pass=password.getText().toString();
                String method="login";
                backGroundTask.execute(method,uname,pass);




//                if (uname.equals("admin")&&pass.equals("admin")){
//                    i=new Intent(LoginActivity.this,HomeActivity.class);
//                    startActivity(i);
//                }else {
//
//                    error.setVisibility(View.VISIBLE);
//
//                    error.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            error.setVisibility(View.INVISIBLE);
//
//                        }
//                    },3000);
//                }
            }
        });
    }



}
