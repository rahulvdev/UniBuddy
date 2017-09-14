package com.unibuddy.finalproject.unibuddy;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAHUL VIJAYDEV on 25-Apr-17.
 */

public class FetchStudMatchesFromDb extends AsyncTask<String,Void,Boolean> {

    private final WeakReference<RoomPrefAdapter> roomieMatchAdapter;

    FetchStudMatchesFromDb(final RoomPrefAdapter roomPrefAdapter) {
        roomieMatchAdapter = new WeakReference<RoomPrefAdapter>(roomPrefAdapter);
    }
    @Override
    protected Boolean doInBackground(String... params) {
        List<StudentListDataJson.StudentItem> resultStudentList = new ArrayList<>();
        try {
            JSONArray response=new JSONArray(MyUtility.downloadJSONusingHTTPGetRequest(params[0]));
            JSONObject jsonObject = response.getJSONObject(0);
            String roomType=jsonObject.getString("roomType").toString();
            String foodPref=jsonObject.getString("FoodPreference").toString();
            String coEdPref=jsonObject.getString("CoEdPreference").toString();
            String smokPref=jsonObject.getString("SmokePreference").toString();
            String smokOkPref=jsonObject.getString("SmokeOkPreference").toString();
            String alcPref=jsonObject.getString("AlcoholPreference").toString();
            String alcOkPref=jsonObject.getString("AlcoholOkPreference").toString();
            String gender=jsonObject.getString("Sex").toString();
            String country=jsonObject.getString("Country").toString();
            String city=jsonObject.getString("City").toString();
            String university=jsonObject.getString("University").toString();
            JSONArray jsonArray=new JSONArray(MyUtility.downloadJSONusingHTTPGetRequest(params[1]+roomType+"/"+foodPref+"/"+coEdPref
            +"/"+smokPref+"/"+smokOkPref+"/"+alcPref+"/"+alcOkPref+"/"+gender+"/"+country+"/"+city+"/"+university));
            for (int i = 0; i < jsonArray.length(); i++) {
                StudentListDataJson.StudentItem studentItem = new StudentListDataJson.StudentItem();
                JSONObject jsOBJ=jsonArray.getJSONObject(i);
                studentItem.details = jsonArray.getJSONObject(i);
                studentItem.image = MyUtility.downloadImageusingHTTPGetRequest(studentItem.details.getString("ImageUrl"));
                resultStudentList.add(studentItem);
            }
        } catch (Exception ae) {
            Log.d("Update Match List", "Unable to fetch user list from: " + params[0]);
            ae.printStackTrace();
            return false;
        }
        StudentListDataJson.ITEMS = resultStudentList;
        return true;
    }


    @Override
    protected void onPostExecute(Boolean success) {
        final RoomPrefAdapter adapter;
        if (success == true
                && ((adapter = roomieMatchAdapter.get()) != null)) {
            adapter.notifyDataSetChanged();
        }
    }
}

