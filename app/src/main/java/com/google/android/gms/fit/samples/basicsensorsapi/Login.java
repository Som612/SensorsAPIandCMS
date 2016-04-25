package com.google.android.gms.fit.samples.basicsensorsapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.fit.samples.DBLayout.ProxyDB;
import com.google.android.gms.fit.samples.DBLayout.RemoteProxyDB;

public class Login extends AppCompatActivity {

    Button loginbutton, signupbutton;
    EditText usernamesignup, passwordsignup, usernamesignin, passwordsignin;
    ProxyDB profiledb;
    RemoteProxyDB remotedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profiledb = new ProxyDB(this);

        loginbutton = (Button) findViewById(R.id.loginbutton);
        signupbutton = (Button) findViewById(R.id.signupbutton);
        usernamesignin = (EditText)findViewById(R.id.UsernameID);
        passwordsignin = (EditText)findViewById(R.id.PasswordID);
        usernamesignup = (EditText)findViewById(R.id.editText);
        passwordsignup = (EditText)findViewById(R.id.editText2);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("usernamesignin", usernamesignin.getText().toString());
                startActivity(intent);
            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(usernamesignup.getText().toString().isEmpty() || passwordsignup.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "CANNOT LEAVE FIELDS EMPTY! ",
                            Toast.LENGTH_SHORT).show();
                } else {

                    new AddDetails().execute();

                    Intent intent = new Intent(getApplicationContext(), CreateProfile.class);
                    intent.putExtra("usernamesignup", usernamesignup.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }


    private class AddDetails extends AsyncTask<Void, Void, Void> {

        String username, password;
        private ProgressDialog pDialog;
        boolean result;

        @Override
        protected void onPreExecute(){

            username = usernamesignup.getText().toString();
            password = passwordsignup.getText().toString();
            result = false;

            pDialog = new ProgressDialog(Login.this);
            pDialog.setCancelable(false);
            pDialog.setMessage("Adding info...");
            showDialog();

        }

        @Override
        protected Void doInBackground(Void ... params){
            remotedb = new RemoteProxyDB();
            result = remotedb.addUserData(username, password);



            return null;
        }

        @Override
        protected void onPostExecute(Void r){

            hideDialog();


            if(result == false){


            } else{
                //not added
            }


        }

        private void showDialog(){
            if(!pDialog.isShowing()){
                pDialog.show();
            }
        }

        private void hideDialog(){
            if(pDialog.isShowing()){
                pDialog.dismiss();
            }
        }

    }


}
