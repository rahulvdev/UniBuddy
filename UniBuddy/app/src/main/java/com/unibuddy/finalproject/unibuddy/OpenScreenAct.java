package com.unibuddy.finalproject.unibuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class OpenScreenAct extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_screen);
        Toolbar tBar=(Toolbar)findViewById(R.id.tbarOpenScreen);
        setSupportActionBar(tBar);
        Button loginButton=(Button)findViewById(R.id.loginButn);
        loginButton.setOnClickListener(this);
        Button signUpButton=(Button)findViewById(R.id.signupButn);
        signUpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Button button=(Button)v;
        String buttonText=button.getText().toString();
        switch(buttonText){
            case "Sign Up":
                Intent signUpIntent=new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(signUpIntent);
                break;
            case "Login":
                Intent loginIntent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(loginIntent);
                break;
        }
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
