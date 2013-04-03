package com.example.lunchdroid.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import com.example.lunchdroid.R;

import android.os.AsyncTask;
import android.widget.TextView;
import android.app.*;
import android.util.Log;

public class Downloader {
	private final Activity mParent;

	public Downloader(Activity parent) {
		mParent = parent;
	}

	public String startDownloadString(String Url) {
		AsyncTask<String, Integer, String> dl = new DownloadToStringTask()
				.execute(Url);
		try {
			return dl.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// / http://developer.android.com/reference/android/os/AsyncTask.html
	// -------------------------------------------------------------------------
	private class DownloadToStringTask extends
			AsyncTask<String, Integer, String> {
		// only the first parameter is used
		@Override
		protected String doInBackground(String... arg0) {
			String s;
			try {
				s = downloadString(arg0[0]);
				// TextView txt = (TextView)
				// mParent.findViewById(R.id.idhelloworld);
				// txt.setText(s);
				Log.w("Lunchdroid", s);

			} catch (IOException e) {
				return "CONNECTION ERROR";
			}
			return s;
		}

		// / ToDo: we need a global download and parse loading symbol
		@Override
		protected void onProgressUpdate(Integer... progress) {
			// setProgressPercent(progress[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			Log.w("Lunchdroid", result);
		}
	}

	private String downloadString(String Url) throws IOException {
		InputStream stream = null;
		BufferedReader r = null;
		StringBuilder total;
		InputStreamReader reader = null;
		try {
			URL url = new URL(Url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);

			conn.connect();
			stream = conn.getInputStream();
			reader = new InputStreamReader(stream);
			r = new BufferedReader(reader);
			total = new StringBuilder(stream.available());
			String line;
			while ((line = r.readLine()) != null) {
				total.append(line);
			}
			return total.toString();

		} finally {
			if(reader != null){
				reader.close();
			}
			if (r != null) {
				r.close();
			}
			if (stream != null) {
				stream.close();
			}
		}
	}

	// -------------------------------------------------------------------------

}
