package com.unibuddy.finalproject.unibuddy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UniversityDetailAct extends AppCompatActivity {

    String userName;
    Bundle valBundle;
    String getUniInfoURL="http://www.unibuddy.com/unidetail/get/";
    String getUniFromStudURL="http://www.unibuddy.com/studentInfo/get/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_detail);

        valBundle=getIntent().getExtras();
        userName=valBundle.getString("userName").toString();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button locateButton=(Button)findViewById(R.id.locateBtn);
        ImageView uniImg=(ImageView)findViewById(R.id.uniImage);
        TextView aboutUni=(TextView)findViewById(R.id.aboutUni);
        TextView uniWebPage=(TextView)findViewById(R.id.uniWebPage);
        TextView univName=(TextView)findViewById(R.id.uniNameDetail);

        new LoadUniDetailFromDB(uniImg,aboutUni,uniWebPage,univName).execute(getUniInfoURL,getUniFromStudURL+userName);

        locateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //Open on Maps
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        try {
            super.onSaveInstanceState(outState);
        }
        catch (Exception e)
        {

        }
    }

}
