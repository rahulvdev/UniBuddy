package com.unibuddy.finalproject.unibuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class StudentHomeAct extends AppCompatActivity {

    String URL="http://www.unibuddy.com";
    String getStr="/studentPref/get/";
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        Bundle valBundle=getIntent().getExtras();
        userName=valBundle.getString("userName").toString();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("STUDENT HOME");

        Button findRoomieBtn=(Button)findViewById(R.id.findRoomieButn);
        findRoomieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RecordCheckRoomPref(getApplicationContext(),userName).execute(URL+getStr+userName);
            }
        });
        Button findHousingBtn=(Button)findViewById(R.id.findHousingBtn);
        findHousingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button studentGrpBtn=(Button)findViewById(R.id.studentGrpButn);
        studentGrpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button uniInfoBtn=(Button)findViewById(R.id.uniInfoButn);
        uniInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UniversityDetailAct.class);
                Bundle args=new Bundle();
                args.putString("userName",userName);
                intent.putExtras(args);
                startActivity(intent);
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
