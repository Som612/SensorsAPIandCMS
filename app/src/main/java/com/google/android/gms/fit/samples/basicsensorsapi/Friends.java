package com.google.android.gms.fit.samples.basicsensorsapi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.fit.samples.DBLayout.RemoteProxyDB;

public class Friends extends AppCompatActivity {

    RemoteProxyDB remotedb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }



    private class AddDetails extends AsyncTask<Void, Void, Void> {

        String username, f1, f2, f3, f4,f5;
        private ProgressDialog pDialog;
        boolean result;

        @Override
        protected void onPreExecute(){


            result = false;

            pDialog = new ProgressDialog(Friends.this);
            pDialog.setCancelable(false);
            pDialog.setMessage("Adding info...");
            showDialog();

        }

        @Override
        protected Void doInBackground(Void ... params){
            remotedb = new RemoteProxyDB();
            result = remotedb.addFriendsData(username,f1,f2,f3,f4,f5);



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
