package com.unibuddy.finalproject.unibuddy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by RAHUL VIJAYDEV on 25-Apr-17.
 */

public class RecordCheckRoomPref extends AsyncTask<String,Void,Boolean> {

    private final WeakReference<Context> contextRef;
    String userName;

    RecordCheckRoomPref(Context context,String userName){
        contextRef=new WeakReference(context);
        this.userName=userName;
    }
    @Override
    protected Boolean doInBackground(String... params) {
        //params[0]=URL
        Boolean userExists=false;
        try{
            JSONObject jsonObject=new JSONObject(MyUtility.downloadJSONusingHTTPGetRequest(params[0]));
            String userExistStatus=jsonObject.get("UserExistStatus").toString();
            if(userExistStatus.equals("User Exists")){
                userExists=true;
            }
            else{
                userExists=false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return userExists;
    }

    @Override
    protected void onPostExecute(Boolean userExist) {
        final Context contextVal=contextRef.get();
        Bundle args=new Bundle();
        args.putString("userName",userName);

        if (userExist && (contextVal != null)) {
            ///Launch RoommatePrefRecycViewAct
            Intent intent=new Intent(contextVal,RoomPrefRecycViewAct.class);
            intent.putExtras(args);
            contextVal.startActivity(intent);
        }

        else{
            //Launch RoommatePrefAct
            Intent intent=new Intent(contextVal,RoommatePrefAct.class);
            intent.putExtras(args);
            contextVal.startActivity(intent);
        }
    }
}
