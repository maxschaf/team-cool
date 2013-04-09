package com.example.lunchdroid.geo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.example.lunchdroid.R;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

public final class Locator {

	private static Locator mInstance;
	private LocationManager mLocationManager;
	private LocationListener mLocationListener;
	private static Activity mContext;
	private boolean mGpsEnabled;
	private boolean mOrtungEnabled;
	private Location mMyLocation;
	private String mMyAddress;

	public String getMyAddress() {
		return mMyAddress;
	}

	protected synchronized void setMyAddress(String mMyAddress) {
		this.mMyAddress = mMyAddress;
	}

	public Location getMyLocation() {
		return mMyLocation;
	}

	protected synchronized void setMyLocation(Location mMyLocation) {
		this.mMyLocation = mMyLocation;
	}

	private Locator() {
		mLocationManager = (LocationManager) mContext
				.getSystemService(Context.LOCATION_SERVICE);

		mLocationListener = new LunchLocationListener();

	}

	private void checkAndSetLocalistionServicesEnabled() {
		// Wenn GPS aus dann sollte nur Toast Hinweis kommen und die Handynetz
		// Position verwendet werden
		mGpsEnabled = mLocationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);

		mOrtungEnabled = mLocationManager
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

		String toastText;
		if (!mGpsEnabled && mOrtungEnabled) {
			toastText = "F�r genauere Ortsbestimmung GPS einschalten.";
			Toast.makeText(mContext, toastText, Toast.LENGTH_LONG).show();
		} else if (!mGpsEnabled && !mOrtungEnabled) {
			toastText = "Keine Ortsbestimmung eingeschalten.";
			Toast.makeText(mContext, toastText, Toast.LENGTH_LONG).show();
			
			LocalisationNotification();
		}

		// TOdo in die Setting, Schnellverkn�pfung um GPS einzuschalten oder
		// Notification
		// Intent intent = new
		// Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		// mContext.startActivity(intent);

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
	private class ReverseGeocodingTask extends
			AsyncTask<Location, Void, String> {
		Context mContext;

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			setMyAddress(result);

			makeToast("Standort: " + result);

			new Thread() {
				@Override
				public void run() {
					try {
						sleep(1000 * 20);
						Locator.getInstance().stopLocationListener();

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}

		public ReverseGeocodingTask(Context context) {
			super();
			mContext = context;
		}

		@Override
		protected String doInBackground(Location... params) {
			Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

			Location loc = params[0];
			List<Address> addresses = null;
			try {
				// GeoCoder funktioniert nur wenn Google Play Services verf�gbar
				// sind
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD
						&& Geocoder.isPresent()) {
					addresses = geocoder.getFromLocation(loc.getLatitude(),
							loc.getLongitude(), 1);
				} else {
					makeToast("Eigener Standort nicht gefunden.");
				}
			} catch (IOException e) {
				e.printStackTrace();
				makeToast("Eigener Standort nicht gefunden.");
			}
			if (addresses != null && addresses.size() > 0) {
				Address address = addresses.get(0);
				// Format the first line of address (if available), city, and
				// country name.
				// todo refactor

				String addressText = String.format(
						"%s, %s",
						address.getMaxAddressLineIndex() > 0 ? address
								.getAddressLine(0) : "",
						address.getAddressLine(1) != null ? address.getAddressLine(1) : "");
				// Update address field on UI.
				Log.w("Lunchdroid", addressText);

				return addressText;
			}
			return null;
		}

		private void makeToast(final String message) {
			((Activity) mContext).runOnUiThread(new Runnable() {
				public void run() {

					Toast.makeText(mContext, message, Toast.LENGTH_SHORT)
							.show();
				}
			});

		}
	}
	
	public double getDistance(String addressStr){
		Geocoder coder = new Geocoder(mContext);
		List<Address> addressL;
		Location location = new Location("");
		try {
		    addressL = coder.getFromLocationName(addressStr,5);
		    if (addressL == null) {
		        return -1.0;
		    }
		    Address address = addressL.get(0);

		    
		    location.setLatitude(address.getLatitude());
		    location.setLongitude(address.getLongitude());
		    
		    
		     
		}catch(Exception e){
			e.getStackTrace();
			return -1.0;
		}
		
		
		return getDistance(location);
	}
	
	public double getDistance(Location location){
		return location.distanceTo(mMyLocation);		
	}

	public void startLocationListener() {
		checkAndSetLocalistionServicesEnabled();

//		// Sets the criteria for a fine and low power provider
//	    Criteria crit = new Criteria();
//	    crit.setAccuracy(Criteria.ACCURACY_COARSE);
//	    crit.setPowerRequirement(Criteria.POWER_LOW);
//
//	    // Gets the best matched provider, and only if it's on
//	    String provider = mLocationManager.getBestProvider(crit, true);

		
		if (mGpsEnabled) {
			mLocationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 5000, 100, mLocationListener);
			mMyLocation = mLocationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		} else if (mOrtungEnabled) {
			mLocationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 5000, 100,
					mLocationListener);
			mMyLocation = mLocationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		} else {
			mLocationManager.requestLocationUpdates(
					LocationManager.PASSIVE_PROVIDER, 5000, 100,
					mLocationListener);
			mMyLocation = mLocationManager
					.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
		}

		if (mMyLocation != null) {
			new ReverseGeocodingTask(mContext).execute(mMyLocation);
			new Thread() {
				@Override
				public void run() {
					try {
						sleep(1000 * 40);
						Locator.getInstance().stopLocationListener();

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		
		Log.i("Lunchdroid", "LocationListener started.");
	}

	public void stopLocationListener() {
		mLocationManager.removeUpdates(mLocationListener);
		Log.i("Lunchdroid", "LocationListener stopped.");
	}
	
	private void LocalisationNotification(){
		
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(mContext)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("Ortsbestimmung")
		        .setContentText("GPS oder Ortung einschalten!");
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);

		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(Activity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
		    (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mBuilder.setAutoCancel(true);
		
		mNotificationManager.notify(1, mBuilder.getNotification());
	}
	

	// TODO Das mit �bergebenem Context muss verbessert werden!
	public static Locator getInstance(Activity context) {
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
