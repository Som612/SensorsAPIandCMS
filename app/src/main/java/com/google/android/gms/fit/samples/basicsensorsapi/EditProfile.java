package com.google.android.gms.fit.samples.basicsensorsapi;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.fit.samples.DBLayout.ProxyDB;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditProfile extends AppCompatActivity {

    Button updatebutton, gallery;
    ProxyDB profiledb;
    EditText age, weight, stepgoal, calgoal;
    Calendar calendar;
    SimpleDateFormat mdformat;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updatebutton = (Button) findViewById(R.id.updatebutton);
        gallery = (Button) findViewById(R.id.gallery);

        age = (EditText)findViewById(R.id.editText9);
        weight = (EditText)findViewById(R.id.editText10);
        stepgoal = (EditText)findViewById(R.id.editText11);
        calgoal = (EditText)findViewById(R.id.editText12);
        calendar = Calendar.getInstance();
        mdformat = new SimpleDateFormat("yyyy / MM / dd ");


        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddtoDB();
                Toast.makeText(EditProfile.this, "Updated!", Toast.LENGTH_LONG).show();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(EditProfile.this, "Image Gallery Opened!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivity(intent);
            }
        });

    }

    public  void AddtoDB() {
        boolean isInserted = profiledb.updateDataT1("hello",
                Integer.parseInt(age.getText().toString()),
                Integer.parseInt(weight.getText().toString()),
                mdformat.format(calendar.getTime()).toString(),
                Integer.parseInt(stepgoal.getText().toString()),
                Integer.parseInt(calgoal.getText().toString()));
    }


}
