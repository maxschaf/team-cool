package com.example.lunchdroid;




import com.example.lunchdroid.data.GetTodayTuGrazAt;
import com.example.lunchdroid.geo.Locator;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class LunchdroidActivity extends TabActivity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_main);

		Locator.getInstance(this).startLocationListener();
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
}