package com.unibuddy.finalproject.unibuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class RoommatePrefAct extends AppCompatActivity implements LaunchStudMatchList{
    private ViewPager viewPager;
    MyFragmentPagerAdapter myPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private Button btnNext;
    public final String myPreferences="MyPreferences";
    SharedPreferences sharedpreferences;
    public final String roommatePrefURL="http://www.unibuddy.com/roommatePref/";
    Bundle valBundle;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommate_pref);

        valBundle=getIntent().getExtras();
        userName=valBundle.getString("userName");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);

        myPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),6);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        sharedpreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);

        addBottomDots(0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        try {
            super.onSaveInstanceState(outState);
        }
        catch (Exception e)
        {

        }
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[6];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    @Override
    public void launchStudentMatchView() {
    Intent intent=new Intent(this,RoomPrefRecycViewAct.class);
        Bundle args=new Bundle();
        args.putString("userName",userName);
        intent.putExtras(args);
        startActivity(intent);
    }


    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        int count;

        public  MyFragmentPagerAdapter (FragmentManager fm, int size){
            super(fm);
            count=size;
        }
        @Override
        public Fragment getItem(int position)
        {
            Fragment frag=null;
                switch (position) {

                    case 0:
                        frag = RoomTypeFrag.newInstance(myPreferences);
                        break;
                    case 1:
                        frag = FoodPreferencesFrag.newInstance(myPreferences);
                        break;
                    case 2:
                        frag = CoEdHousing.newInstance(myPreferences);
                        break;
                    case 3:
                        frag = SmokingPrefFrag.newInstance(myPreferences);
                        break;
                    case 4:
                        frag = AlcoholPrefFrag.newInstance(myPreferences);
                        break;
                    case 5:
                        frag = TellMeAboutPrefFrag.newInstance(myPreferences,roommatePrefURL,userName);
                        break;
                }
            return frag;
        }
        @Override
        public int getCount()
        {
            return count;

        }

    }


}
