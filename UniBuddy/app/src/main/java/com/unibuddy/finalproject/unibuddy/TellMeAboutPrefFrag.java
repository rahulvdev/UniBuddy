package com.unibuddy.finalproject.unibuddy;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TellMeAboutPrefFrag extends Fragment {


    EditText abTxt;
    SharedPreferences prefs;
    String roommatePrefURL;
    LaunchStudMatchList launchListObj;
    String userName;
    String dataFromStudInfoURL="http://www.unibuddy.com/studentInfo/get/";

    public TellMeAboutPrefFrag() {
        // Required empty public constructor
    }

    public static Fragment newInstance(String prefName,String roommatePrefURL,String uName ){
        TellMeAboutPrefFrag taboutFrag=new TellMeAboutPrefFrag();
        Bundle args=new Bundle();
        args.putString("prefName",prefName);
        args.putString("roommatePrefUrl",roommatePrefURL);
        args.putString("userName",uName);
        taboutFrag.setArguments(args);
        return taboutFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roommatePrefURL=getArguments().getString("roommatePrefUrl").toString();
        userName=getArguments().getString("userName").toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_tell_me_about_pref, container, false);
        final String prefName=getArguments().getString("prefName").toString();

        try {
            TextView tellQues = (TextView) rootView.findViewById(R.id.tellQues);
            abTxt = (EditText) rootView.findViewById(R.id.aboutyouEdit);
            Button submitButn = (Button) rootView.findViewById(R.id.submitRoomPref);
            submitButn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String aboutUser = abTxt.getText().toString();
                    prefs = getActivity().getSharedPreferences(prefName, Context.MODE_PRIVATE);
                    String roomType=prefs.getString("roomType",null).toString();
                    String foodPref=prefs.getString("fPref",null).toString();
                    String coEdPref=prefs.getString("coEdPref",null).toString();
                    String smokPref=prefs.getString("smokPref",null).toString();
                    String smokOkPref=prefs.getString("smokOkPref",null).toString();
                    String alcPref=prefs.getString("alcPref",null).toString();
                    String alcOkPref=prefs.getString("alcOkPref",null).toString();
                    /////Fetch other data from student info table and add to preferences table///////
                   /* new StudentPrefToDB().execute(roommatePrefURL,"userName="+userName
                            +"&Name="+"Neymar Da Silva Santos Jr"
                            +"&rt="+roomType
                            +"&fPre="+foodPref
                            +"&coPre="+coEdPref
                            +"&smoPre="+smokPref
                            +"&smoOkPre="+smokOkPref
                            +"&alcPre="+alcPref
                            +"&alcOkPre="+alcOkPref
                            +"&abtMe="+aboutUser);*/
                    launchListObj = (LaunchStudMatchList) getActivity();
                   new StudentPrefToDB(launchListObj).execute(roommatePrefURL,dataFromStudInfoURL+userName,userName,roomType,foodPref,coEdPref,smokPref,smokOkPref,alcPref
                   ,alcOkPref,aboutUser);
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rootView;
    }

}
