package com.example.tourassistsrilankapro2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class User_Forget_Password extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__forget__password);

        passwordEmail = (EditText)findViewById(R.id.forgot_Email);
        resetPassword = (Button)findViewById(R.id.CHANGE_PASSWORD);
        firebaseAuth =  FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String useremail = passwordEmail.getText().toString().trim();


                if(useremail.equals("")){
                    Toast.makeText(User_Forget_Password.this,"Please Enter your Registration Email ID",Toast.LENGTH_SHORT).show();

                }else{

                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(User_Forget_Password.this,"password reset email sent",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(User_Forget_Password.this,loginactivity.class));
                            }else{
                                Toast.makeText(User_Forget_Password.this,"Error in Sending Password to reset email....",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}