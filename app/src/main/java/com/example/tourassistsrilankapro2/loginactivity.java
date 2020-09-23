package com.example.tourassistsrilankapro2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class loginactivity extends AppCompatActivity {

    EditText loginName;
    EditText loginpassword;
    TextView createAcc;
    TextView forgotpsw;
    Button loginButton;
    ProgressDialog progress;
    DatabaseReference dbref;


    private FirebaseAuth mAuth;

    String emailpattern = "[a-zA-z0-9._=]+@[a-z]+\\.+[a-z]+";




    private void clearControls(){
        loginName.setText("");
        loginpassword.setText("");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        progress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {

            startActivity(new Intent(getApplicationContext(),Profile.class));
        }

        userlogin();
    }
    private  void userlogin(){

       loginName = findViewById(R.id.login_yourname);
       loginpassword = findViewById(R.id.login_password);
       loginButton = findViewById(R.id.Login_Btn);
        forgotpsw = findViewById(R.id.forgot_Password);

       loginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               final String LoginEmail = loginName.getText().toString().trim();
               final String PasswordLogin = loginpassword.getText().toString().trim();

               if (TextUtils.isEmpty(LoginEmail)){
                    loginName.setError("Email is Required");
                    loginName.requestFocus();
                    return;
                }
                 if(!LoginEmail.matches(emailpattern)){
                    loginName.setError("invalid email pattern");
                    loginName.requestFocus();
                    return;

                } if (TextUtils.isEmpty(PasswordLogin)) {
                    loginpassword.setError("Password is Required");
                    loginpassword.requestFocus();
                    return;
                }

                 progress.setMessage("Login in...");
                 progress.show();

                 mAuth.signInWithEmailAndPassword(LoginEmail,PasswordLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             progress.dismiss();
                             clearControls();
                             Toast.makeText(getApplicationContext(),"Welcome.....\nTour Assist Sri Lanka",Toast.LENGTH_LONG).show();
                             Intent intent = new Intent(loginactivity.this,Profile.class);
                             intent.putExtra("id",LoginEmail);
                             startActivity(intent);
                         }else{
                             progress.dismiss();
                             Toast.makeText(getApplicationContext(),"login failed",Toast.LENGTH_LONG).show();
                         }
                     }
                 });


           }
       });

        forgotpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginactivity.this,User_Forget_Password.class));
            }
        });


    }


}