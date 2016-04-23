package com.google.android.gms.fit.samples.basicsensorsapi;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CreateProfile extends AppCompatActivity {

    Button savebutton, gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        savebutton = (Button) findViewById(R.id.savebutton);
        gallery = (Button) findViewById(R.id.gallery);

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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

    //When we setup the data base there's going to be a Toast message that says "incorrect password/email" incase
    //the password or email entered by the user is incorrect.


}
