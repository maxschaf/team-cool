package com.example.lunchdroid;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.lunchdroid.data.Downloader;
import com.example.lunchdroid.data.GetTodayTuGrazAt;
import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.data.RestaurantCollection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GetTodayTuGrazAt dl = new GetTodayTuGrazAt(this);
		dl.startDownloadMenuByDayXml();
 

		while (true) {
			Log.w("Lunchdroid",
					String.valueOf(RestaurantCollection.getInstance().size()) + " items in ItemsCollection.");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
