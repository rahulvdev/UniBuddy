package com.unibuddy.finalproject.unibuddy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by RAHUL VIJAYDEV on 18-Apr-17.
 */

public class LoginAsync extends AsyncTask<String,Void,Boolean> {

    private final WeakReference<TextView> displayText;
    private final WeakReference<Context> context;
    String userName;

    LoginAsync(TextView tView,Context context_param,String userName){
        displayText=new WeakReference(tView);
        context=new WeakReference(context_param);
        this.userName=userName;
    }

    @Override
    protected Boolean doInBackground(String... params) {
    Boolean success=false;
        try {
            JSONObject jsonObject=new JSONObject(MyUtility.downloadJSONusingHTTPGetRequest(params[0]));
            String result=(String)jsonObject.get("Authentication").toString();
            if(result.equals("valid user")){
                success=true;
            }
            else{
                success=false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            success=false;
        }
        return success;
    }


    @Override
    protected void onPostExecute(Boolean success) {
        final TextView tView=displayText.get();
        final Context contextVal=context.get();
        if (success && (tView!=null)&& (contextVal!=null)){
            tView.setText("Login Succeeded");
            Intent intent=new Intent(contextVal,StudentHomeAct.class);
            Bundle args=new Bundle();
            args.putString("userName",userName);
            intent.putExtras(args);
            contextVal.startActivity(intent);
        }
        else{
            tView.setText("Login failed");
        }
    }
    }

