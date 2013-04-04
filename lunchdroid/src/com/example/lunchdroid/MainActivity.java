package com.example.lunchdroid;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.lunchdroid.data.Downloader;
import com.example.lunchdroid.data.GetTodayTuGrazAt;
import com.example.lunchdroid.data.Item;
import com.example.lunchdroid.data.ItemCollection;
import com.example.lunchdroid.geo.Locator;
import com.example.lunchdroid.geo.LunchLocationListener;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//GetTodayTuGrazAt dl = new GetTodayTuGrazAt(this);
		//Locator mLocator = Locator.getInstance();
		LocationManager mLocationManager = (LocationManager) 
				getSystemService(Context.LOCATION_SERVICE);
		
		boolean enabled = mLocationManager
				  .isProviderEnabled(LocationManager.GPS_PROVIDER);

				if (!enabled) {
					//todo: maybe dialge to inform that gps is needed
				  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				  startActivity(intent);
				} 
		
		LocationListener locationListener = new LunchLocationListener();  
		mLocationManager.requestLocationUpdates(  
		LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		
	    Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


		//while (true) {
			Log.w("Lunchdroid",
					String.valueOf(ItemCollection.getInstance().getItems()
							.size()) + " items in ItemsCollection.");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
