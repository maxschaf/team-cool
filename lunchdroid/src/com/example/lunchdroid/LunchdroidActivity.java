package com.example.lunchdroid;

 
import com.example.lunchdroid.data.GetTodayTuGrazAt;
import com.example.lunchdroid.data.PersistentPreferencesCollection;
import com.example.lunchdroid.geo.Locator;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
 
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

public class LunchdroidActivity extends SherlockFragmentActivity {
	
	ActionBar mActionBar;
    ViewPager mPager;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        /** Getting a reference to action bar of this activity */
        mActionBar = getSupportActionBar();
 
        /** Set tab navigation mode */
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
        /** Getting a reference to ViewPager from the layout */
        mPager = (ViewPager) findViewById(R.id.pager);
 
        /** Getting a reference to FragmentManager */
        FragmentManager fm = getSupportFragmentManager();
 
        /** Defining a listener for pageChange */
        ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mActionBar.setSelectedNavigationItem(position);
            }
        };
 
        /** Setting the pageChange listner to the viewPager */
        mPager.setOnPageChangeListener(pageChangeListener);
 
        /** Creating an instance of FragmentPagerAdapter */
        TabFragmentPagerAdapter fragmentPagerAdapter = new TabFragmentPagerAdapter(fm);
 
        /** Setting the FragmentPagerAdapter object to the viewPager object */
        mPager.setAdapter(fragmentPagerAdapter);
 
        mActionBar.setDisplayShowTitleEnabled(true);
 
        /** Defining tab listener */
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
 
            @Override
            public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            }
 
            @Override
            public void onTabSelected(Tab tab, FragmentTransaction ft) {
                mPager.setCurrentItem(tab.getPosition());
            }
 
            @Override
            public void onTabReselected(Tab tab, FragmentTransaction ft) {
            }
        };
 
        /** Creating Android Tab */
        Tab tab = mActionBar.newTab()
                .setText("Distance")
                .setTabListener(tabListener);
 
        mActionBar.addTab(tab);
 
        /** Creating Apple Tab */
        tab = mActionBar.newTab()
                .setText("Favorit")
                .setTabListener(tabListener);
 
        mActionBar.addTab(tab);
 
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	
	
	
	/*
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_main);

		Locator.getInstance(this).startLocationListener();
		PersistentPreferencesCollection.getInstance().addPersistentPreference(this, "FAV");
		GetTodayTuGrazAt dl = new GetTodayTuGrazAt(this);
		dl.startDownloadMenuByDayXml();
		
		
		TabHost tabs = getTabHost(); 

		tabs.setup();

		TabHost.TabSpec spec = tabs.newTabSpec("tabdistance");

		Intent intent = new Intent(this, TabDistanceActivity.class);
		spec.setContent(intent);
		spec.setIndicator("Distance");
		tabs.addTab(spec);

		
		spec = tabs.newTabSpec("tabfavorit");
		intent = new Intent(this, TabFavoritActivity.class);
		spec.setContent(intent);
		spec.setIndicator("Favorit");
		tabs.addTab(spec);

	}
	
	@Override
    protected void onStart() {
        super.onStart();

		if(Locator.getInstance(this).getMyLocation() != null){
			Log.w("Lunchdroid", "Distanz: " + String.valueOf(Locator.getInstance().getDistance("Austraße 8 8480 Mureck")));
			
		}

    }
    
    */
}