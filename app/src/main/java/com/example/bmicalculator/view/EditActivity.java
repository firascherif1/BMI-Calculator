package com.example.bmicalculator.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bmicalculator.R;
import com.example.bmicalculator.controller.Controller;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private TextView tvWelcome;
    private EditText etAge;
    private EditText etHeight;
    private EditText etWeight;
    private RadioButton rbtSexe;
    private Button btnSubmit;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean verifAge = false, verifHeight = false, verifWeight = false;
                if(!etAge.getText().toString().isEmpty())
                    verifAge = true;
                else
                    Toast.makeText(EditActivity.this, "insert your age !", Toast.LENGTH_SHORT).show();
                if(!etHeight.getText().toString().isEmpty())
                    verifHeight = true;
                else
                    Toast.makeText(EditActivity.this, "insert your height !", Toast.LENGTH_SHORT).show();
                if(!etWeight.getText().toString().isEmpty())
                    verifWeight = true;
                else
                    Toast.makeText(EditActivity.this, "insert your weight !", Toast.LENGTH_SHORT).show();
                if(verifAge && verifHeight && verifWeight)
                {
                    String age = etAge.getText().toString();
                    String height = etHeight.getText().toString();
                    String weight = etWeight.getText().toString();

                    FirebaseUser user = auth.getCurrentUser();
                    String uid = user.getUid();
                    DatabaseReference usersRef = mDatabase.child("users").child(uid);
                    usersRef.child("age").setValue(age);
                    usersRef.child("height").setValue(height);
                    usersRef.child("weight").setValue(weight);
                    if(rbtSexe.isChecked())
                        usersRef.child("gender").setValue("male");
                    else
                        usersRef.child("gender").setValue("female");


                    controller.createUser(Integer.valueOf(age), Float.valueOf(height), Float.valueOf(weight), rbtSexe.isChecked());

                    usersRef.child("BMI").setValue(String.valueOf(controller.getBMI()));
                    usersRef.child("result").setValue(controller.getResult());


                    Intent intent = new Intent(EditActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        });
    }

    void init(){
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        tvWelcome = findViewById(R.id.tvWelcome);
        etAge = findViewById(R.id.etAge);
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        rbtSexe = findViewById(R.id.rbtMale);
        btnSubmit = findViewById(R.id.btnSubmit);
        controller = Controller.getInstance();
    }

}