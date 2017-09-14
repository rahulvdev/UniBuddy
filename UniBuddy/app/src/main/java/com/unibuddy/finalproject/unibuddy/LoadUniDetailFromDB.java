package com.unibuddy.finalproject.unibuddy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.ref.WeakReference;

/**
 * Created by RAHUL VIJAYDEV on 27-Apr-17.
 */

public class LoadUniDetailFromDB extends AsyncTask<String,Void,UniDetailWrapper.UniItem> {
    private final WeakReference<ImageView> uniImage;
    private final WeakReference<TextView> aboutUni;
    private final WeakReference<TextView> uniWebPage;
    private final WeakReference<TextView> uniName;

    LoadUniDetailFromDB(ImageView imgView,TextView tView,TextView tView_uniWeb,TextView universityName){
        uniImage=new WeakReference(imgView);
        aboutUni=new WeakReference(tView);
        uniWebPage=new WeakReference(tView_uniWeb);
        uniName=new WeakReference(universityName);
    }


    @Override
    protected UniDetailWrapper.UniItem doInBackground(String... params) {

        try{
            UniDetailWrapper.UniItem detailWrapper=new UniDetailWrapper.UniItem();
            JSONObject jsonObject=new JSONObject(MyUtility.downloadJSONusingHTTPGetRequest(params[1]));
            JSONObject valObj=jsonObject.getJSONObject("StudentInfo");
            String universityName=valObj.getString("University").toString();
            JSONObject jsonObjectUni=new JSONObject(MyUtility.downloadJSONusingHTTPGetRequest(params[0]+universityName));
            JSONObject valUniObj=jsonObjectUni.getJSONObject("UniversityInfo");
            detailWrapper.details=valUniObj;
            detailWrapper.image = MyUtility.downloadImageusingHTTPGetRequest(detailWrapper.details.getString("UniImage"));
            return detailWrapper;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(UniDetailWrapper.UniItem wrapObj) {
        ImageView uniPic=uniImage.get();
        TextView aboutTheUni=aboutUni.get();
        TextView univURL=uniWebPage.get();
        TextView nameOfUni=uniName.get();
        if(wrapObj!=null && (uniPic!=null) && (aboutTheUni!=null) && (univURL!=null) && (nameOfUni!=null)){
            try {
                uniPic.setImageBitmap(wrapObj.image);
                aboutTheUni.setText((String)wrapObj.details.getString("AboutUni"));
                univURL.setText((String)wrapObj.details.getString("UniWeb"));
                nameOfUni.setText((String)wrapObj.details.getString("UniName"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            aboutTheUni.setText("Could Not load University Data".toString());
        }
    }
}
