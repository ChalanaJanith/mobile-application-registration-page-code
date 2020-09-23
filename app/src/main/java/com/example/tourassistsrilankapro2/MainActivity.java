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
import android.widget.TextView;
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
    TextView icon;
    private ProgressDialog dialog;


    TextView AlreadyAccount;

    public Button regsignBtn;
   // private ProgressDialog dialog;

    private FirebaseAuth auth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference collection = database.getReference().child("users");
    private String userId;

    String nowhitespace ="\\A\\w{4,20}\\z";
    String emailpattern = "[a-zA-z0-9._=]+@[a-z]+\\.+[a-z]+";

    String passwordval = "^.{4,8}$";



    private void clearControls(){
        cusname.setText("");
        cusemail.setText("");
        cusphone.setText("");
        cusaddress.setText("");
        cuspsw1.setText("");

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();

        AlreadyAccount=findViewById(R.id.alreadyAcc);
        AlreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,loginactivity.class);
                startActivity(intent);
            }
        });
        registerUser();


    }
    private  void registerUser(){

        cusname = (EditText)findViewById(R.id.Reg_yourname);
        cusemail = (EditText)findViewById(R.id.Reg_email);
        cusphone = (EditText)findViewById(R.id.Reg_phonenumber);
        cusaddress = (EditText)findViewById(R.id.Reg_address);
        cuspsw1 = (EditText)findViewById(R.id.Reg_password);
       // AlreadyAccount = findViewById(R.id.alreadyAcc);
        icon = findViewById(R.id.loginicon);



        regsignBtn = (Button)findViewById(R.id.Reg_signup);



        regsignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get all the values

                final String username = cusname.getText().toString().trim();
                final String useremail = cusemail.getText().toString().trim();
                final String userphone = cusphone.getText().toString().trim();
                final String useradd = cusaddress.getText().toString().trim();
                final String userpwd1 = cuspsw1.getText().toString().trim();



                if (TextUtils.isEmpty(username)){
                    cusname.setError("Name is Required");
                    cusname.requestFocus();
                    return;
                }
                if(username.length()>=20){
                    cusname.setError("Name is too long");
                    cusname.requestFocus();
                    return;
                }
                if(!username.matches(nowhitespace)){
                    cusname.setError("white spaces are not allowed!");
                    cusname.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(useremail)){
                    cusemail.setError("Email is Required");
                    cusemail.requestFocus();
                    return;
                }
                if(useremail.length()>=50){
                    cusemail.setError("Name is too long");
                    cusemail.requestFocus();
                    return;

                }
                if(!useremail.matches(emailpattern)){
                    cusemail.setError("invalid email pattern");
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
                if(!userpwd1.matches(passwordval)){
                    Toast.makeText(MainActivity.this, "Password is too weak", Toast.LENGTH_LONG).show();
                }
                dialog.setMessage("Sending data...");
                dialog.show();

                auth.createUserWithEmailAndPassword(useremail,userpwd1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration Success",Toast.LENGTH_LONG).show();
                            userId = auth.getCurrentUser().getUid();
                            regClass reg = new regClass(username,useremail,userphone,useradd,userpwd1);
                            collection.child(userId).setValue(reg);
                        }
                        else{
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });



    }

}