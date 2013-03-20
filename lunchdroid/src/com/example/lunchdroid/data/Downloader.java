package com.example.lunchdroid.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

	public void startDownload(String Url) {
		new DownloadToStringTask().execute(Url);
	}

	private class DownloadToStringTask extends AsyncTask<String, Void, String> {

		@Override
		// only the first parameter is used
		protected String doInBackground(String... arg0) {
			try {
				String s = downloadString(arg0[0]);
				// TextView txt = (TextView)
				// mParent.findViewById(R.id.idhelloworld);
				// txt.setText(s);

				Log.w("Lunchdroid", s);
				return s;
			} catch (IOException e) {
				return "CONNECTION ERROR";
			}
		}

		/// ToDo: we need a global download and parse loading symbol
		protected void onProgressUpdate(Integer... progress) {
			// setProgressPercent(progress[0]);
		}

		protected void onPostExecute(Long result) {
			// showDialog("Downloaded " + result + " bytes");

		}
	}

	private String downloadString(String Url) throws IOException {
		InputStream stream = null;
		try {
			URL url = new URL(Url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);

			conn.connect();
			stream = conn.getInputStream();
			BufferedReader r = new BufferedReader(new InputStreamReader(stream));
			StringBuilder total = new StringBuilder(stream.available());
			String line;
			while ((line = r.readLine()) != null) {
				total.append(line);
			}
			return total.toString();

		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

}
