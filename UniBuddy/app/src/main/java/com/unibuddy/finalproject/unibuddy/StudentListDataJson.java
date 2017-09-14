package com.unibuddy.finalproject.unibuddy;

import android.graphics.Bitmap;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class StudentListDataJson {

    public static List<StudentItem> ITEMS = new ArrayList<>();

    /**
     * A Movie item representing a piece of content.
     */
    public static class StudentItem {
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
