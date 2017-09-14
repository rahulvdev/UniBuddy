package com.unibuddy.finalproject.unibuddy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomPrefRecycViewFrag extends Fragment {

    public RecyclerView recyCView;
    public RecyclerView.LayoutManager layout;
    RoomPrefAdapter adapter;
    String userPreferenceURL="http://www.unibuddy.com/userPref/get/";
    String userName;
    String matchListURL="http://www.unibuddy.com/matchList/get/";
    ShowMatchInfo showMatch;

    public RoomPrefRecycViewFrag() {
        // Required empty public constructor
    }


    public static Fragment newInstance(String uName){
        RoomPrefRecycViewFrag rPrefRecyc=new RoomPrefRecycViewFrag();
        Bundle args=new Bundle();
        args.putString("userName",uName);
        rPrefRecyc.setArguments(args);
        return rPrefRecyc;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        userName=getArguments().getString("userName").toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView=inflater.inflate(R.layout.fragment_room_pref_recyc_view,container,false);
        try {
            showMatch = (ShowMatchInfo)rootView.getContext();
        }
        catch(ClassCastException e){
            e.printStackTrace();
        }
        recyCView = (RecyclerView) rootView.findViewById(R.id.rPreRecyclerView);
        recyCView.setHasFixedSize(true);
        layout = new LinearLayoutManager(getActivity());
        recyCView.setLayoutManager(layout);
        adapter = new RoomPrefAdapter();
        try {
            recyCView.setAdapter(adapter);
        }
        catch(Exception e){

            Log.d("AdapterExc",e.getMessage());
        }

        adapter.SetOnItemClick(new RoomPrefAdapter.ClickItems() {
            @Override
            public void ShortClick(View v, int position) {
                //StudentDetailsFragment.newInstance(UniBuddyServerURL+studentDetails+StudentListDataJson.ITEMS.get(position).details.getString("UserName"))
                try {
                    showMatch.displayMatchData(new PreferenceMatchDataFragment().newInstance(userPreferenceURL+StudentListDataJson.ITEMS.get(position).details.getString("userName")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        new FetchStudMatchesFromDb(adapter).execute(userPreferenceURL+userName,matchListURL);
        return rootView;
    }
}


