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
public class AlcoholPrefFrag extends Fragment {

    String[] arraySpinnerAlc;
    String[] arraySpinnerOkAlc;
    SharedPreferences.Editor editor;
    Spinner alcoholSpinner;
    Spinner alcoholOkSpinner;

    public AlcoholPrefFrag() {
        // Required empty public constructor
    }

    public static Fragment newInstance(String prefName){
        AlcoholPrefFrag alcFrag=new AlcoholPrefFrag();
        Bundle args=new Bundle();
        args.putString("prefName",prefName);
        alcFrag.setArguments(args);
        return alcFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.arraySpinnerAlc = new String[] {
                "Yes","No"
        };
        this.arraySpinnerOkAlc=new String[]{
                "Yes","No"
        };
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_alcohol_pref, container, false);
        String prefName=getArguments().getString("prefName").toString();
        editor = getActivity().getSharedPreferences(prefName, Context.MODE_PRIVATE).edit();

        try {
            alcoholSpinner = (Spinner) rootView.findViewById(R.id.spinnerAlcoholPref);
            ArrayAdapter<String> adapter_a = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arraySpinnerAlc);
            adapter_a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            alcoholSpinner.setAdapter(adapter_a);
            alcoholSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapter, View view,
                                           int position, long id) {
                    String alcPref = alcoholSpinner.getSelectedItem().toString();
                    editor.putString("alcPref",alcPref);
                    editor.commit();
                }

                public void onNothingSelected(AdapterView<?> arg0) {

                }
            });

            alcoholOkSpinner = (Spinner) rootView.findViewById(R.id.spinnerOkWithAlcohol);
            ArrayAdapter<String> adapter_b = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arraySpinnerOkAlc);
            adapter_b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            alcoholOkSpinner.setAdapter(adapter_b);
            alcoholOkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapter, View view,
                                           int position, long id) {
                    String alcOkPref = alcoholOkSpinner.getSelectedItem().toString();
                    editor.putString("alcOkPref",alcOkPref);
                    editor.commit();
                }

                public void onNothingSelected(AdapterView<?> arg0) {

                }
            });

            TextView alcQues = (TextView) rootView.findViewById(R.id.drinkQues);
            TextView alcOkQues = (TextView) rootView.findViewById(R.id.drinkOkQues);
            Button nxBtn = (Button) rootView.findViewById(R.id.nxAl);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rootView;
    }

}
