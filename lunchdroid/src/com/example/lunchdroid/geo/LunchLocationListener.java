package com.example.lunchdroid.geo;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;


public class LunchLocationListener implements LocationListener {

	
	@Override
	public void onLocationChanged(Location loc) {
		loc.getLatitude();

		loc.getLongitude();

		String text = "loc: " + loc.getLatitude() +" "+ loc.getLongitude();
				
		Log.w("Lunchdroid",text);
		System.out.println(text);
		
		Locator.getInstance().doReverseGeocoding(loc);
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
