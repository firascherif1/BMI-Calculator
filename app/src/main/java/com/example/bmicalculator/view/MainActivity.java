package com.example.bmicalculator.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bmicalculator.R;
import com.example.bmicalculator.controller.Controller;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private TextView tvWelcome;
    private TextView tvAge;
    private TextView tvHeight;
    private TextView tvWeight;
    private TextView tvSexe;
    private TextView tvBMI;
    private TextView tvResult;
    private Button btnEdit;
    private Button btnLogOut;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        DatabaseReference usersRef = mDatabase.child("users").child(uid);
        usersRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String age = dataSnapshot.child("age").getValue(String.class);
                    String height = dataSnapshot.child("height").getValue(String.class);
                    String weight = dataSnapshot.child("weight").getValue(String.class);
                    String gender = dataSnapshot.child("gender").getValue(String.class);
                    String BMI = dataSnapshot.child("BMI").getValue(String.class);
                    String result = dataSnapshot.child("result").getValue(String.class);

                    tvWelcome.setText("Welcome " + name);
                    tvAge.setText("age : " + age + " ans");
                    tvHeight.setText("height : " + height + " cm");
                    tvWeight.setText("weight : " + weight + " Kg");
                    tvBMI.setText("BMI : " + BMI);
                    tvResult.setText(result);
                    if(gender.equals("male"))
                        tvSexe.setText("gender : male");
                    else
                        tvSexe.setText("gender : female");


                    boolean sexe;

                    if(gender.equals("male"))
                        sexe = true;
                    else
                        sexe = false;

                    controller.createUser(Integer.parseInt(age), Float.parseFloat(height), Float.parseFloat(weight),sexe);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Erreur : " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void init(){
        tvWelcome = findViewById(R.id.tvWelcome);
        tvAge = findViewById(R.id.tvAge);
        tvHeight = findViewById(R.id.tvHeight);
        tvWeight = findViewById(R.id.tvWeight);
        tvSexe = findViewById(R.id.tvSexe);
        tvBMI = findViewById(R.id.tvBMI);
        tvResult = findViewById(R.id.tvResult);
        btnEdit = findViewById(R.id.btnEdit);
        btnLogOut = findViewById(R.id.btnLogOut);
        controller = Controller.getInstance();
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
}