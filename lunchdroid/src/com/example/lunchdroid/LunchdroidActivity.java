package com.example.lunchdroid;




import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.*;

import com.example.lunchdroid.data.GetTodayTuGrazAt;
import com.example.lunchdroid.data.PersistentPreferencesCollection;
import com.example.lunchdroid.geo.Locator;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LunchdroidActivity extends SherlockFragmentActivity implements ActionBar.TabListener {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_main);
		
		Locator.getInstance(this).startLocationListener();
		PersistentPreferencesCollection.getInstance().addPersistentPreference(this, "FAV");
		GetTodayTuGrazAt dl = new GetTodayTuGrazAt(this);
		dl.startDownloadMenuByDayXml();
		
		final ActionBar actionbar = getSupportActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		Tab tab1 = actionbar.newTab().setText("Distance");
		tab1.setTabListener(this);
		
		Tab tab2 = actionbar.newTab().setText("Favorits");
		tab2.setTabListener(this);	
		
		/*TabHost tabs = getTabHost(); 

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
		tabs.addTab(spec);*/

	}
	
	@Override
    protected void onStart() {
        super.onStart();

		if(Locator.getInstance(this).getMyLocation() != null){
			Log.w("Lunchdroid", "Distanz: " + String.valueOf(Locator.getInstance().getDistance("Austraße 8 8480 Mureck")));
			
		}

    }

	@Override
	public void onTabSelected(Tab tab,
			android.support.v4.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		TabDistanceFragment fragment = new TabDistanceFragment();
		getSupportFragmentManager().beginTransaction().add(fragment, "Distance").commit();
		
	}

	@Override
	public void onTabUnselected(Tab tab,
			android.support.v4.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab,
			android.support.v4.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}