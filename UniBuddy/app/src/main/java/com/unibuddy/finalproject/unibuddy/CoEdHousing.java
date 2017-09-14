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
public class CoEdHousing extends Fragment {

    String[] arraySpinnerCoEd;
    Spinner coEd;
    SharedPreferences.Editor editor;

    public CoEdHousing() {
        // Required empty public constructor
    }

    public static Fragment newInstance(String prefName){
        CoEdHousing coEdFrag=new CoEdHousing();
        Bundle args=new Bundle();
        args.putString("prefName",prefName);
        coEdFrag.setArguments(args);
        return coEdFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.arraySpinnerCoEd = new String[] {
                "Yes","No"
        };
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_co_ed_housing, container, false);
        String prefName=getArguments().getString("prefName").toString();
        editor = getActivity().getSharedPreferences(prefName, Context.MODE_PRIVATE).edit();
        try {
            coEd = (Spinner) rootView.findViewById(R.id.spinnerCoEd);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arraySpinnerCoEd);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            coEd.setAdapter(adapter);
            coEd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapter, View view,
                                           int position, long id) {
                    String coEdPref = coEd.getSelectedItem().toString();
                    editor.putString("coEdPref",coEdPref);
                    editor.commit();

                }

                public void onNothingSelected(AdapterView<?> arg0) {

                }
            });
            TextView coQues = (TextView) rootView.findViewById(R.id.coEdQues);
            Button nxBtn = (Button) rootView.findViewById(R.id.nxCo);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rootView;
    }

}
