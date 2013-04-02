package com.example.lunchdroid.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

public class GetTodayTuGrazAt {
	private final Activity mParent;
	private final String mUrl = "http://rss.tugraz.at/menue.xml";
	private final Integer mDays = 1;
	private AsyncTask<String, Integer, List<Item>> mAt;

	public GetTodayTuGrazAt(Activity parent) {
		mParent = parent;

		mAt = startDownloadStream();
	}

	public boolean hasFinished() {
		if (AsyncTask.Status.FINISHED == mAt.getStatus()) {
			return true;
		} else {
			return false;
		}
	}

	private AsyncTask<String, Integer, List<Item>> startDownloadStream() {
		return new DownloadToStreamTask().execute(mUrl, mDays.toString());
	}

	private class DownloadToStreamTask extends
			AsyncTask<String, Integer, List<Item>> {
		@Override
		// only the first parameter is used
		protected List<Item> doInBackground(String... arg0) {
			InputStream in = null;
			try {
				in = getStream(arg0[0]);
				Log.w("Lunchdroid", "Stream ready.");

				List<Item> list = parse(in, Integer.parseInt(arg0[1]));
				ItemCollection.getInstance().addItems(list);
				return list;
			} catch (IOException e) {
				return null;
			} catch (XmlPullParserException e) {
				return null;
			}
		}

		// / ToDo: we need a global download and parse loading symbol
		protected void onProgressUpdate(Integer... progress) {
			// setProgressPercent(progress[0]);
		}

		protected void onPostExecute(List<Item> result) {
			Log.w("Lunchdroid", "Finished.");
		}
	}

	private InputStream getStream(String Url) throws IOException {
		InputStream stream = null;

		URL url = new URL(Url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);

		conn.connect();
		stream = conn.getInputStream();
		return stream;

	}

	private List<Item> parse(InputStream in, int days)
			throws XmlPullParserException, IOException {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in, null);
			parser.nextTag();
			return readFeed(parser, days);
		} finally {
//			if (in != null)
//				in.close();
		}
	}

	private List<Item> readFeed(XmlPullParser parser, int days)
			throws XmlPullParserException, IOException {
		List<Item> items = new ArrayList<Item>();

//		parser.require(XmlPullParser.START_TAG, "", "rss");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT && days > 0) {
			if (eventType == XmlPullParser.START_TAG) {
				String name = parser.getName();
				// Starts by looking for the item tag
				if (name.equals("item")) {
					days--;
					items.addAll(readEntry(parser));
				}
			}
			eventType = parser.next();
		}

		return items;
	}

	private List<Item> readEntry(XmlPullParser parser)
			throws XmlPullParserException, IOException {
//		parser.require(XmlPullParser.START_TAG, "", "item");
		String description = null;
		String pubDate = null;

		int eventType = parser.next();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {

				String name = parser.getName();
				if (name.equals("description")) {
					description = parser.nextText();
				} else if (name.equals("pubDate")) {
					pubDate = parser.nextText();
				}

				if (description != null && pubDate != null) {
					break; // while
				}
			}
			eventType = parser.next();
		}
		return extractItems(description, pubDate);
	}

	private List<Item> extractItems(String description, String pubDate) {
		List<Item> items = new ArrayList<Item>();

		// walk through html test of rss and parse out fields using html
		// markup to separate fields.
		while (description.length() > 0) {
			String name = description.substring(description.indexOf("<b>") + 3,
					description.indexOf("</b>")).trim();
			description = description.substring(
					description.indexOf("</b>") + 4, description.length());

			String adrTel = description
					.substring(0, description.indexOf("<br>"))
					.replace('(', ' ').replace(')', ' ').trim();
			description = description.substring(
					description.indexOf("<br>") + 4, description.length());

			int endMeals = description.indexOf("<b>") > -1 ? description
					.indexOf("<b>") : description.length();
			String meals = description.substring(0, endMeals);
			description = description.substring(endMeals, description.length());

			String[] meal = meals.split("<br>");
			ArrayList<String> mealslist = new ArrayList<String>();
			for (String m : meal) {
				if (m.trim().isEmpty())
					continue;

				mealslist.add(m.trim());
			}
			// Tue, 19 Mar 2013 07:25:00 +0100
			DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss Z",
					Locale.ENGLISH);
			Date date = null;
			try {
				date = df.parse(pubDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			items.add(new Item(name, adrTel, mealslist, date));

		}
		return items;
	}

}
