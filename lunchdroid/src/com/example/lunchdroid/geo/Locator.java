package com.example.lunchdroid.geo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.example.lunchdroid.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public final class Locator {

	private static Locator mInstance;
	private LocationManager mLocationManager;
	private LocationListener mLocationListener;
	private static MainActivity mContext;
	private boolean mGpsEnabled;

	private Locator() {
		mLocationManager = (LocationManager) mContext
				.getSystemService(Context.LOCATION_SERVICE);

		// Wenn GPS aus dann sollte nur Toast Hinweis kommen und die Handynetz
		// Position verwendet werden
		mGpsEnabled = mLocationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);

		if (!mGpsEnabled) {
			Toast.makeText(mContext,
					"Für genauere Ortsbestimmung GPS aktivieren.",
					Toast.LENGTH_LONG).show();

			// Intent intent = new
			// Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			// mContext.startActivity(intent);
		}

		mLocationListener = new LunchLocationListener();

	}

	public void doReverseGeocoding(Location location) {
		// Since the geocoding API is synchronous and may take a while. You
		// don't want to lock
		// up the UI thread. Invoking reverse geocoding in an AsyncTask.
		(new ReverseGeocodingTask(mContext))
				.execute(new Location[] { location });
	}

	// AsyncTask encapsulating the reverse-geocoding API. Since the geocoder API
	// is blocked,
	// we do not want to invoke it from the UI thread.
	private class ReverseGeocodingTask extends AsyncTask<Location, Void, Void> {
		Context mContext;

		public ReverseGeocodingTask(Context context) {
			super();
			mContext = context;
		}

		@Override
		protected Void doInBackground(Location... params) {
			Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

			Location loc = params[0];
			List<Address> addresses = null;
			try {
                //GeoCoder funktioniert nur wenn Google Play Services verfügbar sind
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD
						&& Geocoder.isPresent())
					addresses = geocoder.getFromLocation(loc.getLatitude(),
							loc.getLongitude(), 1);
			} catch (IOException e) {
				e.printStackTrace();
				// Update address field with the exception.
				// Message.obtain(mHandler, UPDATE_ADDRESS, e.toString())
				// .sendToTarget();
			}
			if (addresses != null && addresses.size() > 0) {
				Address address = addresses.get(0);
				// Format the first line of address (if available), city, and
				// country name.
				String addressText = String.format(
						"%s, %s, %s",
						address.getMaxAddressLineIndex() > 0 ? address
								.getAddressLine(0) : "", address.getLocality(),
						address.getCountryName());
				// Update address field on UI.
				Log.w("Lunchdroid", addressText);
				System.out.println(addressText);
			}
			return null;
		}
	}

	public void startLocationListener() {
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				5000, 100, mLocationListener);
	}

	public void stopLocationListener() {
		mLocationManager.removeUpdates(mLocationListener);
	}

	public static Locator getInstance(MainActivity context) {
		mContext = context;
		if (mInstance == null) {
			mInstance = new Locator();
		}
		return mInstance;
	}

	public static Locator getInstance() {
		if (mInstance == null) {
			mInstance = new Locator();
		}
		return mInstance;
	}
}
