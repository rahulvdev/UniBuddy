package com.unibuddy.finalproject.unibuddy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreferenceMatchDataFragment extends Fragment {

    public static String ARG_URL="url";
    String userDetailURL;

    public PreferenceMatchDataFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(String url){
        PreferenceMatchDataFragment prefMatchFrag=new PreferenceMatchDataFragment();
        Bundle args=new Bundle();
        args.putString(ARG_URL,url);
        prefMatchFrag.setArguments(args);
        return prefMatchFrag;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // movieData = (HashMap<String, ?>) getArguments().getSerializable(ARG_MOVIE);
            userDetailURL =getArguments().getString(ARG_URL);
        }
        if (savedInstanceState!=null) {
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_preference_match_data, container, false);

        new FetchMatchDataFromServer(rootView).execute(userDetailURL);
        //new StudentDetailsFromServer(rootView).execute(StudentDetailsURL);


        return rootView;

    }

}
