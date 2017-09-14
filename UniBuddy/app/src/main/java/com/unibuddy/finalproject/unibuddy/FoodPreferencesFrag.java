package com.unibuddy.finalproject.unibuddy;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodPreferencesFrag extends Fragment {

    String[] arraySpinnerFood;
    Spinner fPref;
    SharedPreferences.Editor editor;


    public FoodPreferencesFrag() {
        // Required empty public constructor
    }

    public static Fragment newInstance(String prefName){
        FoodPreferencesFrag foodPref=new FoodPreferencesFrag();
        Bundle args=new Bundle();
        args.putString("prefName",prefName);
        foodPref.setArguments(args);
        return foodPref;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.arraySpinnerFood = new String[] {
                "Vegetarian","Non-Vegetarian"
        };
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_food_preferences, container, false);
        String prefName=getArguments().getString("prefName").toString();
        editor = getActivity().getSharedPreferences(prefName, Context.MODE_PRIVATE).edit();
        try {
            fPref = (Spinner) rootView.findViewById(R.id.spinnerFoodPref);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arraySpinnerFood);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fPref.setAdapter(adapter);
            fPref.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapter, View view,
                                           int position, long id) {
                    String foodPref = fPref.getSelectedItem().toString();
                    editor.putString("fPref",foodPref);
                    editor.commit();

                }

                public void onNothingSelected(AdapterView<?> arg0) {

                }
            });
            TextView fpQues = (TextView) rootView.findViewById(R.id.foodPrefQues);
            Button nxBtn = (Button) rootView.findViewById(R.id.nxFooPre);
        }
        catch(Exception e){

            e.printStackTrace();
        }

        return rootView;
    }

}
