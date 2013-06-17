package com.example.lunchdroid;

 
import com.example.lunchdroid.data.GetTodayTuGrazAt;
import com.example.lunchdroid.data.PersistentPreferencesCollection;
import com.example.lunchdroid.data.RestaurantCollection;
import com.example.lunchdroid.geo.Locator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
 
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class LunchdroidActivity extends SherlockFragmentActivity {
	
	ActionBar mActionBar;
    ViewPager mPager;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        RestaurantCollection.getInstance().reset();
		Locator.getInstance(this).startLocationListener();
		PersistentPreferencesCollection.getInstance().addPersistentPreference(this, "FAV");
		GetTodayTuGrazAt dl = new GetTodayTuGrazAt(this);
		dl.startDownloadMenuByDayXml();
        
        
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
                
                //little hack to refresh the lists
                Fragment f = (Fragment) mPager.getAdapter().instantiateItem(mPager, tab.getPosition());
                if(f != null && f.getActivity() != null && f instanceof TabFavoritFragment)
                {
                	((TabFavoritFragment)f).onResume();
                }
                else if(f != null && f.getActivity() != null && f instanceof TabDistanceFragment)
                {
                	((TabDistanceFragment)f).onResume();
                }       
            }
 
            @Override
            public void onTabReselected(Tab tab, FragmentTransaction ft) {
            }
        };
 

        Tab tab = mActionBar.newTab()
                .setText("Distanz")
                .setTabListener(tabListener);
 
        mActionBar.addTab(tab);
 

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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(this, SettingsActivity.class);
		

		startActivity(intent);
		return super.onOptionsItemSelected(item);
	}
    

}