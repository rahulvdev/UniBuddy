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
public class SmokingPrefFrag extends Fragment {
    String[] arraySpinnerSmoke;
    String[] arraySpinnerOkSmoke;
    Spinner smokSpin;
    Spinner smokOkSpin;
    SharedPreferences.Editor editor;

    public SmokingPrefFrag() {
        // Required empty public constructor
    }


    public static Fragment newInstance(String prefName){
        SmokingPrefFrag smFrag=new SmokingPrefFrag();
        Bundle args=new Bundle();
        args.putString("prefName",prefName);
        smFrag.setArguments(args);
        return smFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.arraySpinnerSmoke = new String[] {
                "Yes","No"
        };
        this.arraySpinnerOkSmoke=new String[]{
                "Yes","No"
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_smoking_pref, container, false);

        String prefName=getArguments().getString("prefName").toString();
        editor = getActivity().getSharedPreferences(prefName, Context.MODE_PRIVATE).edit();

        smokSpin=(Spinner)rootView.findViewById(R.id.spinnerSmokePref);
        ArrayAdapter<String> adapter_a = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,arraySpinnerSmoke);
        adapter_a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smokSpin.setAdapter(adapter_a);

        smokSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view,
                                       int position, long id) {
                String smokPref = smokSpin.getSelectedItem().toString();
                editor.putString("smokPref",smokPref);
                editor.commit();

            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        smokOkSpin=(Spinner)rootView.findViewById(R.id.spinnerOkWithSmoke);
        ArrayAdapter<String> adapter_b = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,arraySpinnerOkSmoke);
        adapter_b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smokOkSpin.setAdapter(adapter_b);

        smokOkSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view,
                                       int position, long id) {
                String smokOkPref = smokOkSpin.getSelectedItem().toString();
                editor.putString("smokOkPref",smokOkPref);
                editor.commit();
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });


        TextView smQues=(TextView)rootView.findViewById(R.id.smoQues);
        TextView smOkQues=(TextView)rootView.findViewById(R.id.smoOkQues);
        Button nxBtn=(Button)rootView.findViewById(R.id.nxSmo);


        return rootView;
    }

}
