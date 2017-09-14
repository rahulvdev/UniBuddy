package com.unibuddy.finalproject.unibuddy;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;

import java.awt.font.TextAttribute;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAHUL VIJAYDEV on 18-Apr-17.
 */

public class SignUpAsync extends AsyncTask<String,Void,Boolean>{

    private final WeakReference<TextView> displayText;
    SignUpAsync(TextView tView){displayText=new WeakReference(tView);}

    @Override
    protected Boolean doInBackground(String... params) {

        Boolean success=false;
        try {
            String resultCode = MyUtility.sendHttpPostRequest(params[0], params[1]);
            if (resultCode != null) {
                success = true;
            } else {
                success = false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            success=false;
        }
        return success;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        final TextView tView=displayText.get();
    if (success && (tView!=null)){
        tView.setText("Sign up succeeded");
    }
    else{
        tView.setText("Sign up failed");
    }
    }
}
