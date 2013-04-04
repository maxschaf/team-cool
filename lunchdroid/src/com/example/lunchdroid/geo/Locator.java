package com.example.lunchdroid.geo;

import com.example.lunchdroid.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;


public class Locator extends MainActivity{
	
	public Locator(){
	LocationManager mLocationManager = (LocationManager) 
			getSystemService(Context.LOCATION_SERVICE);
	
	boolean enabled = mLocationManager
			  .isProviderEnabled(LocationManager.GPS_PROVIDER);

			if (!enabled) {
			  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			  startActivity(intent);
			} 
	
	LocationListener locationListener = new LunchLocationListener();  
	mLocationManager.requestLocationUpdates(  
	LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

	}
}
