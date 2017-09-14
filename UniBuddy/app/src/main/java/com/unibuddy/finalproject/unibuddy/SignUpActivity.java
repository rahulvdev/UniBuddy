package com.unibuddy.finalproject.unibuddy;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    CallbackManager callbackManager;
    TextView msgView;
    EditText emailText;
    EditText passText;
    String emailTxt=null;
    String passTxt=null;
    public String email;
    public static final String serverURL="http://www.unibuddy.com";
    public static final String signUpStr="/authenticate/signup";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar tBar=(Toolbar)findViewById(R.id.tbarSignUp);
        setSupportActionBar(tBar);
        Button signUpButton=(Button)findViewById(R.id.submitSignUp);
        signUpButton.setOnClickListener(this);
        emailText=(EditText)findViewById(R.id.emailEdit);
        passText=(EditText)findViewById(R.id.passEdit);
        msgView=(TextView)findViewById(R.id.resultText);
        LoginButton loginButton = (LoginButton)findViewById(R.id.signup_button);

        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email"));

        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                // Application code
                                try {
                                    String email = object.getString("email");
                                    new SignUpAsync(msgView).execute(serverURL+signUpStr,"userName="+email
                                            +"&password=fb");
                                    Log.d("",email);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                              //  String birthday = object.getString("birthday"); // 01/31/1980 format
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();


            }

            @Override
            public void onCancel() {
                // App code
                Log.v("LoginActivity", "cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });


    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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

    @Override
    public void onClick(View v) {
        Button butn=(Button)v;
        emailTxt=emailText.getText().toString();
        passTxt=passText.getText().toString();
        String butnText=butn.getText().toString();
        if(butnText.equals("SIGN UP") && (emailTxt!=null) &&(passTxt!=null)){
            new SignUpAsync(msgView).execute(serverURL+signUpStr,"userName="+emailTxt
                    +"&password="+passTxt);
        }
        else{
            msgView.setText("Enter both email and password");
        }
    }
}

