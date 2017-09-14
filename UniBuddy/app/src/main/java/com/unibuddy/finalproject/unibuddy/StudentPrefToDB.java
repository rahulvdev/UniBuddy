package com.unibuddy.finalproject.unibuddy;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by RAHUL VIJAYDEV on 24-Apr-17.
 */

public class StudentPrefToDB extends AsyncTask<String,Void,Boolean> {

    LaunchStudMatchList launchListObj;

    StudentPrefToDB(LaunchStudMatchList lObj){
        launchListObj=lObj;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        Boolean success=false;
        try {
            JSONObject response=new JSONObject(MyUtility.downloadJSONusingHTTPGetRequest(params[1]));
            JSONObject jsonObject=response.getJSONObject("StudentInfo");
            String nameOfUser=jsonObject.getString("Name").toString();
            String contact=jsonObject.getString("Contact").toString();
            String gender=jsonObject.getString("Sex").toString();
            String country=jsonObject.getString("Country").toString();
            String city=jsonObject.get("City").toString();
            String university=jsonObject.getString("University").toString();
            String imageURL=jsonObject.getString("ImageUrl").toString();
            String datToPost="userName="+params[2]+
                    "&Name="+nameOfUser+
                    "&rt="+params[3]+
                    "&fPre="+params[4]+
                    "&coPre="+params[5]+
                    "&smoPre="+params[6]+
                    "&smoOkPre="+params[7]+
                    "&alcPre="+params[8]+
                    "&alcOkPre="+params[9]+
                    "&abtMe="+params[10]+
                    "&contact="+contact+
                    "&sex="+gender+
                    "&country="+country+
                    "&city="+city+
                    "&uni="+university+
                    "&ImgURL="+imageURL;
            String resultCode = MyUtility.sendHttpPostRequest(params[0],datToPost);
            if(resultCode!=null)
                success=true;
            else
                success=false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return success;
    }

    @Override
    protected void onPostExecute(Boolean success) {
            if(success && launchListObj!=null) {
                Log.d("RoommatePrefToDB", "Data succesfully inserted");
                launchListObj.launchStudentMatchView();
            }
            else
                Log.d("RoommatePrefToDB","Data not inserted into the DB");
    }
}
