package com.google.android.gms.fit.samples.basicsensorsapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.fit.samples.DBLayout.ProxyDB;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateProfile extends AppCompatActivity {

    Button savebutton, gallery;
    ProxyDB profiledb;
    EditText age, weight, stepgoal, calgoal;
    Calendar calendar;
    SimpleDateFormat mdformat;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        profiledb = new ProxyDB(this);


        Intent in = getIntent();
        username= in.getExtras().getString("usernamesignup");

        savebutton = (Button) findViewById(R.id.savebutton);
        gallery = (Button) findViewById(R.id.gallery);
        age = (EditText)findViewById(R.id.editText3);
        weight = (EditText)findViewById(R.id.editText4);
        stepgoal = (EditText)findViewById(R.id.editText5);
        calgoal = (EditText)findViewById(R.id.editText6);
        calendar = Calendar.getInstance();
        mdformat = new SimpleDateFormat("yyyy / MM / dd ");



        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddtoDB();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CreateProfile.this, "Image Gallery Opened!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivity(intent);

            }
        });


    }

    public  void AddtoDB() {
        boolean isInserted = profiledb.insertDataT1(username,
                Integer.parseInt(age.getText().toString()),
                Integer.parseInt(weight.getText().toString()),
                mdformat.format(calendar.getTime()).toString(),
                Integer.parseInt(stepgoal.getText().toString()),
                Integer.parseInt(calgoal.getText().toString()));
    }



    //When we setup the data base there's going to be a Toast message that says "incorrect password/email" incase
    //the password or email entered by the user is incorrect.


}
