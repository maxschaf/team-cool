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

		// GetTodayTuGrazAt dl = new GetTodayTuGrazAt(this);


		Log.w("Lunchdroid",
				String.valueOf(ItemCollection.getInstance().getItems().size())
						+ " items in ItemsCollection.");


	
	}
	@Override
    protected void onStart() {
        super.onStart();

		Locator.getInstance(this).startLocationListener();
		if(Locator.getInstance(this).getMyLocation() != null){
			Log.w("Lunchdroid", "Distanz: " + String.valueOf(Locator.getInstance().getDistance("Austraﬂe 8 8480 Mureck")));
			
		}

    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
