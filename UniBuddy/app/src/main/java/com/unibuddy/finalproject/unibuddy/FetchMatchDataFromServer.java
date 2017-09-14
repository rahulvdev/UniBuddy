package com.unibuddy.finalproject.unibuddy;

import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by RAHUL VIJAYDEV on 27-Apr-17.
 */

public class FetchMatchDataFromServer extends AsyncTask<String,Void,MatchDataJson.MatchItem> {

    private final WeakReference<View> rootViewReference;

    FetchMatchDataFromServer(final View rootView) {
        rootViewReference = new WeakReference(rootView);
    }

    @Override
    protected MatchDataJson.MatchItem doInBackground(String... params) {
        try {
            MatchDataJson.MatchItem studentItem = new MatchDataJson.MatchItem();
            JSONArray responsev= new JSONArray(MyUtility.downloadJSONusingHTTPGetRequest(params[0]));
            JSONObject objResponse = responsev.getJSONObject(0);
            studentItem.details = objResponse;
            studentItem.image = MyUtility.downloadImageusingHTTPGetRequest(studentItem.details.getString("ImageUrl"));
            return studentItem;
        } catch (Exception ae) {
            Log.d("UpdateMovieDetail", "Unable to fetch movie details from: " + params[0]);
            ae.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(MatchDataJson.MatchItem item) {
        final View rootView;

        if (item != null
                && ((rootView = rootViewReference.get()) != null)) {

            //getActivity().setTitle((String)movieData.get("name"));
            final TextView nameStud=(TextView)rootView.findViewById(R.id.nameStudMatch);
            final TextView emailStud=(TextView)rootView.findViewById(R.id.emailStudMatch);
            final TextView gender =(TextView) rootView.findViewById(R.id.genderStudMatch);
            final TextView contact =(TextView) rootView.findViewById(R.id.contactStudMatch);
            final TextView city=(TextView)rootView.findViewById(R.id.cityStudMatch);
            final TextView country=(TextView)rootView.findViewById(R.id.countryStudMatch);
            final TextView uni=(TextView)rootView.findViewById(R.id.uniStudMatch);
            final ImageView studImage=(ImageView)rootView.findViewById(R.id.imgStudMatch);
            final TextView roomType =(TextView) rootView.findViewById(R.id.rtStudMatch);
            final TextView foodPref =(TextView) rootView.findViewById(R.id.foodStudMatch);
            final TextView coedPref =(TextView) rootView.findViewById(R.id.coStudMatch);
            final TextView smoPref=(TextView) rootView.findViewById(R.id.smoStudMatch);
            final TextView smoOkPref =(TextView) rootView.findViewById(R.id.smoOkStudMatch);
            final TextView alcPref =(TextView) rootView.findViewById(R.id.alcStudMatch);
            final TextView alcOkPref =(TextView) rootView.findViewById(R.id.alcOkStudMatch);
            final TextView aboutStud=(TextView)rootView.findViewById(R.id.aboutStudMatch);

            try {
                studImage.setImageBitmap(item.image);
                nameStud.setText((String)item.details.getString("Name"));
                emailStud.setText((String)item.details.getString("userName"));
                roomType.setText((String)item.details.getString("roomType"));
                foodPref.setText((String)item.details.getString("FoodPreference"));
                coedPref.setText((String)item.details.getString("CoEdPreference"));
                smoPref.setText((String)item.details.getString("SmokePreference"));
                smoOkPref.setText((String)item.details.getString("SmokeOkPreference"));
                alcPref.setText((String)item.details.getString("AlcoholPreference"));
                alcOkPref.setText((String)item.details.getString("AlcoholOkPreference"));
                gender.setText((String)item.details.getString("Sex"));
                contact.setText((String)item.details.getString("Contact"));
                city.setText((String)item.details.getString("City"));
                country.setText((String)item.details.getString("Country"));
                uni.setText((String)item.details.getString("University"));
                aboutStud.setText((String)item.details.getString("AboutMe"));

            } catch (Exception ae) {
                Log.d("UpdateMovieDetail", "Unable to set movie details to view");
                ae.printStackTrace();
            }
        }
    }
    }

