package com.unibuddy.finalproject.unibuddy;

import android.graphics.Bitmap;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MatchDataJson {

    public static List<MatchItem> ITEMS = new ArrayList<>();

    /**
     * A Movie item representing a piece of content.
     */
    public static class MatchItem {
        public JSONObject details;
        public Bitmap image;

        @Override
        public String toString() {
            try {
                return details.getString("UserName");
            } catch (Exception ae) {
                return null;
            }
        }
    }
}
