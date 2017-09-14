package com.unibuddy.finalproject.unibuddy;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;


public class RoomTypeFrag extends Fragment {

    private String[] arraySpinner;
    Spinner roomTypeSpinner;
    SharedPreferences.Editor editor;

    public static Fragment newInstance(String prefName){
        RoomTypeFrag rType=new RoomTypeFrag();
        Bundle args=new Bundle();
        args.putString("prefName",prefName);
        rType.setArguments(args);
        return rType;
    }

    public RoomTypeFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.arraySpinner = new String[] {
                "Single","Shared"
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_room_type, container, false);
        String prefName=getArguments().getString("prefName").toString();
        editor = getActivity().getSharedPreferences(prefName, Context.MODE_PRIVATE).edit();
        roomTypeSpinner=(Spinner)rootView.findViewById(R.id.spinnerRoomType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomTypeSpinner.setAdapter(adapter);
        roomTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view,
                                       int position, long id) {
                String roomType = roomTypeSpinner.getSelectedItem().toString();
                editor.putString("roomType",roomType);
                editor.commit();

            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        TextView rTypeView=(TextView)rootView.findViewById(R.id.roomTypeQues);
        Button nextBtn=(Button)rootView.findViewById(R.id.nxRT);

        return rootView;
    }

}
