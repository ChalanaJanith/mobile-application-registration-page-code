package com.example.tourassistsrilankapro2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private TextView proName,proEmail,proPhone,proAddress;
  //  TextView proEditBtn;
    private String email, password;

    String userId;

    TextView txt;
    private FirebaseAuth auth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("users");

    // updated by RAVB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        proName = findViewById(R.id.USERNAME);
        proEmail = findViewById(R.id.USEREMAIL);
        proPhone = findViewById(R.id.USERPHONE);
        proAddress = findViewById(R.id.USERADDRESS);

        auth = FirebaseAuth.getInstance();
        DatabaseReference userRef = reference.child(auth.getCurrentUser().getUid());

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                regClass RegClass = snapshot.getValue(regClass.class);
                proName.setText(RegClass.getName());
                proEmail.setText(RegClass.getEmail());
                proPhone.setText(RegClass.getPhone());
                proAddress.setText(RegClass.getEAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, error.getCode(),Toast.LENGTH_SHORT).show();
            }

            });
    }
}