package com.example.tourassistsrilankapro2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public  EditText cusname;
    public  EditText cusemail;
    public  EditText cusphone;
    public  EditText cusaddress;
    public  EditText cuspsw1;
    public  EditText cuspsw2;

    public Button regsignBtn;
    private ProgressDialog dialog;

    FirebaseAuth auth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbref = database.getReference().child("Users");
    private String userId;
    regClass registration;

    private void clearControls(){
        cusname.setText("");
        cusemail.setText("");
        cusphone.setText("");
        cusaddress.setText("");
        cuspsw1.setText("");
        cuspsw2.setText("");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        cusname = (EditText)findViewById(R.id.Reg_yourname);
        cusemail = (EditText)findViewById(R.id.Reg_email);
        cusphone = (EditText)findViewById(R.id.Reg_phonenumber);
        cusaddress = (EditText)findViewById(R.id.Reg_address);
        cuspsw1 = (EditText)findViewById(R.id.Reg_password);
        cuspsw2 = (EditText)findViewById(R.id.Reg_password2);

        regsignBtn = (Button)findViewById(R.id.Reg_signup);

        registration = new regClass();


        regsignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = cusname.getText().toString().trim();
                final String useremail = cusemail.getText().toString().trim();
                final String userphone = cusphone.getText().toString().trim();
                final String useradd = cusaddress.getText().toString().trim();
                final String userpwd1 = cuspsw1.getText().toString().trim();
                String userpwd2 = cuspsw2.getText().toString().trim();
               dbref = FirebaseDatabase.getInstance().getReference().child("Users") ;


                // validation


                    if (TextUtils.isEmpty(username)) {
                        cusname.setError("Name is Required");
                        cusname.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(useremail)) {
                        cusemail.setError("Email is Required");
                        cusemail.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(userphone)) {
                        cusphone.setError("Phone Number is Required");
                        cusphone.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(useradd)) {
                        cusaddress.setError("Address is Required");
                        cusaddress.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(userpwd1)) {
                        cuspsw1.setError("Password is Required");
                        cuspsw1.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(userpwd2)) {
                        cuspsw2.setError("Password is Required");
                        cuspsw2.requestFocus();
                        return;
                    }
                    if (!TextUtils.equals(userpwd1, userpwd2)) {
                        cuspsw2.setError("Passwords Not Match");
                        cuspsw2.requestFocus();
                        return;
                    }
                    if (username.isEmpty() && useremail.isEmpty() && useradd.isEmpty() && userphone.isEmpty() && userpwd1.isEmpty() && userpwd2.isEmpty()) {

                        Toast.makeText(MainActivity.this, "Fields are Empty!", Toast.LENGTH_LONG).show();

                    }
                dialog.setMessage("Sending Data...");
                dialog.show();

                auth.createUserWithEmailAndPassword(useremail,userpwd1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration Success",Toast.LENGTH_LONG).show();
                            userId = auth.getCurrentUser().getUid();
                            regClass regClass = new regClass(username,useremail,useradd,userphone,userpwd1);
                            dbref.child(userId).setValue(regClass);
                        }else{
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }





    public void gotoNext(View view) {

          //  Intent dsp = new Intent(MainActivity.this,loginpage.class);
           // startActivity(dsp);

    }
}