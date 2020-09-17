package com.example.tourassistsrilankapro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginactivity extends AppCompatActivity {

    EditText loginName;
    EditText loginpassword;
    TextView createAcc;
    TextView forgotpsw;
    Button loginButton;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        loginName = (EditText)findViewById(R.id.login_yourname);
        loginpassword = (EditText)findViewById(R.id.login_password);
        createAcc = (TextView)findViewById(R.id.sign_to_Customer);
        forgotpsw =(TextView)findViewById(R.id.forgot_Password);
        loginButton = (Button)findViewById(R.id.Login_Btn);

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registerIntent = new Intent(loginactivity.this,MainActivity.class);
                startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
    }

}