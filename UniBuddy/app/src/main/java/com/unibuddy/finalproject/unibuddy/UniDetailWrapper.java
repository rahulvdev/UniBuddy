package com.unibuddy.finalproject.unibuddy;

import android.graphics.Bitmap;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAHUL VIJAYDEV on 27-Apr-17.
 */

public class UniDetailWrapper {

    public static List<UniDetailWrapper.UniItem> ITEMS = new ArrayList<>();

    public static class UniItem {
        public JSONObject details;
        public Bitmap image;


        @Override
        public String toString() {
            try {
                return details.getString("UniWeb");
            } catch (Exception ae) {
                return null;
            }
        }
    }
}
