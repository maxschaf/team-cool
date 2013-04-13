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
import java.util.Locale;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

public class GetTodayTuGrazAt {
	private final Activity mParent;
	private final String mUrlMenuXml = "http://rss.tugraz.at/menue.xml";
	private final String mUrlMenuByDayXml = "http://today.tugraz.at/menuesByDate.xml";
	private final Integer mDays = 1;
	private AsyncTask<String, Integer, Boolean> mDownloaderMenuXml;
	private AsyncTask<String, Integer, Boolean> mDownloaderMenuByDayXml;

	public GetTodayTuGrazAt(Activity parent) {
		mParent = parent;
	}

	public boolean hasFinishedMenuXml() {
		if (AsyncTask.Status.FINISHED == mDownloaderMenuXml.getStatus()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hasFinishedMenuByDayXml() {
		if (AsyncTask.Status.FINISHED == mDownloaderMenuByDayXml.getStatus()) {
			return true;
		} else {
			return false;
		}
	}

	public void startDownloadMenuXml() {
		mDownloaderMenuXml = new DownloadToStreamMenuXmlTask().execute(
				mUrlMenuXml, mDays.toString());
	}

	public void startDownloadMenuByDayXml() {
		mDownloaderMenuByDayXml = new DownloadToStreamMenuByDayXmlTask()
				.execute(mUrlMenuByDayXml);
	}

	private synchronized InputStream getStream(String Url) throws IOException {
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

	private class DownloadToStreamMenuByDayXmlTask extends
			AsyncTask<String, Integer, Boolean> {

		private GregorianCalendar calendar = new GregorianCalendar(
				Locale.GERMANY);
		private int CURRENT_YEAR = calendar.get(Calendar.YEAR);

		@Override
		// only the first parameter is used
		protected Boolean doInBackground(String... arg0) {
			InputStream in = null;
			try {
				in = getStream(arg0[0]);
				// Log.w("Lunchdroid", "Stream ready.");

				parse(in);
				// ItemCollection.getInstance().addItems(list);
				in.close();

				// todo da momentan nur eine datenquelle reicht das so.
				RestaurantCollection.getInstance().finishedAddingData();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} catch (XmlPullParserException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		// / ToDo: we need a global download and parse loading symbol
		@Override
		protected void onProgressUpdate(Integer... progress) {
			// setProgressPercent(progress[0]);
		}

		@Override
		protected void onPostExecute(Boolean v) {
			Log.w("Lunchdroid", "Finished.");
		}

		private void parse(InputStream in) throws XmlPullParserException,
				IOException {
			try {
				XmlPullParser parser = Xml.newPullParser();
				parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,
						false);
				parser.setInput(in, null);
				parser.nextTag();
				readFeed(parser);
			} finally {
				if (in != null)
					in.close();
			}
		}

		private void readFeed(XmlPullParser parser)
				throws XmlPullParserException, IOException {

			// parser.require(XmlPullParser.START_TAG, "", "rss");
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					String name = parser.getName();

					if (name.equals("week")) {
						String weekOfYear = parser.getAttributeValue("",
								"number");
						String[] temp = weekOfYear.split("_");
						weekOfYear = temp[temp.length - 1];
						calendar.clear();
						calendar.set(Calendar.YEAR, CURRENT_YEAR);
						calendar.set(Calendar.WEEK_OF_YEAR,
								Integer.parseInt(weekOfYear) + 1); // XML
																	// beginnt
																	// bei 0

						while (eventType != XmlPullParser.END_DOCUMENT) {
							if (eventType == XmlPullParser.START_TAG) {
								name = parser.getName();
								if (name.equals("day")) {
									String dayname = parser.getAttributeValue(
											"", "name");
									readEntry(parser, dayname);
									if (parser.getName() != null) {
										if (parser.getName().equals("day")) {
											continue;
										}
									}
								}
							}
							try { // Gingerbread throws Exception when
									// ENd_Document is reached, 4.x doesnt.
								eventType = parser.next();
							} catch (IllegalStateException e) {
								eventType = XmlPullParser.END_DOCUMENT;
							}
						}
					}

				}
				try { // Gingerbread throws Exception when ENd_Document is
						// reached, 4.x doesnt.
					eventType = parser.next();
				} catch (IllegalStateException e) {
					eventType = XmlPullParser.END_DOCUMENT;
				}
			}
		}

		private void readEntry(XmlPullParser parser, String dayname)
				throws XmlPullParserException, IOException {
			// parser.require(XmlPullParser.START_TAG, "", "item");
			String rname = "";
			String time = "";
			String address = "";
			String phone = "";

			int dayTarget = 0;

			if (dayname.equalsIgnoreCase("montag")) {
				dayTarget = Calendar.MONDAY;
			} else if (dayname.equalsIgnoreCase("dienstag")) {
				dayTarget = Calendar.TUESDAY;
			} else if (dayname.equalsIgnoreCase("mittwoch")) {
				dayTarget = Calendar.WEDNESDAY;
			} else if (dayname.equalsIgnoreCase("donnerstag")) {
				dayTarget = Calendar.THURSDAY;
			} else if (dayname.equalsIgnoreCase("freitag")) {
				dayTarget = Calendar.FRIDAY;
			}

			int day = calendar.get(Calendar.DAY_OF_WEEK);

			if (day != dayTarget) {
				calendar.set(Calendar.DAY_OF_WEEK, dayTarget);
			}
			Date today = calendar.getTime();

			int eventType = parser.next();
			String name = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					name = parser.getName();
					if (name.equals("restaurant")) {

						ArrayList<Lunch> lunches = new ArrayList<Lunch>();
						eventType = parser.next();
						while (eventType != XmlPullParser.END_DOCUMENT) {
							if (eventType == XmlPullParser.START_TAG) {

								name = parser.getName();
								if (name.equals("name")) {
									rname = parser.nextText();
								} else if (name.equals("address")) {
									address = parser.nextText();
								} else if (name.equals("phone")) {
									phone = parser.nextText();
								} else if (name.equals("menu")) {
									String price = parser.getAttributeValue("",
											"price");
									lunches.add(new Lunch(parser.nextText(),
											price));
								}

								if (name.equals("restaurant")
										|| name.equals("day")) {
									break;
								}
							}

							eventType = parser.next();
						}
						if (name.equals("restaurant")
								|| eventType == XmlPullParser.END_DOCUMENT) {
							RestaurantCollection.getInstance().addItem(
									today,
									new Restaurant(rname, address, phone,
											lunches));

							continue;
						}
					}
					if (name.equals("day")) {
						return;
					}
				}

				eventType = parser.next();
			}

			// extractItems(description, pubDate);
		}

	}

	private class DownloadToStreamMenuXmlTask extends
			AsyncTask<String, Integer, Boolean> {
		@Override
		// only the first parameter is used
		protected Boolean doInBackground(String... arg0) {
			InputStream in = null;

			try {
				in = getStream(arg0[0]);
				// Log.w("Lunchdroid", "Stream ready.");

				parse(in, Integer.parseInt(arg0[1]));
				in.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} catch (XmlPullParserException e) {
				e.printStackTrace();
				return false;
			}
		}

		// / ToDo: we need a global download and parse loading symbol
		@Override
		protected void onProgressUpdate(Integer... progress) {
			// setProgressPercent(progress[0]);
		}

		@Override
		protected void onPostExecute(Boolean v) {
			Log.w("Lunchdroid", "Finished.");
		}

		private void parse(InputStream in, int days)
				throws XmlPullParserException, IOException {
			try {
				XmlPullParser parser = Xml.newPullParser();
				parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,
						false);
				parser.setInput(in, null);
				parser.nextTag();
				readFeed(parser, days);
			} finally {
				if (in != null)
					in.close();
			}
		}

		private void readFeed(XmlPullParser parser, int days)
				throws XmlPullParserException, IOException {

			// parser.require(XmlPullParser.START_TAG, "", "rss");
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT && days > 0) {
				if (eventType == XmlPullParser.START_TAG) {
					String name = parser.getName();
					// Starts by looking for the item tag
					if (name.equals("item")) {
						days--;
						readEntry(parser);
					}
				}
				eventType = parser.next();
			}
		}

		private void readEntry(XmlPullParser parser)
				throws XmlPullParserException, IOException {
			// parser.require(XmlPullParser.START_TAG, "", "item");
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
			extractItems(description, pubDate);
		}

		private void extractItems(String description, String pubDate) {

			// walk through html test of rss and parse out fields using html
			// markup to separate fields.
			while (description.length() > 0) {
				String name = description.substring(
						description.indexOf("<b>") + 3,
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
				description = description.substring(endMeals,
						description.length());

				String[] meal = meals.split("<br>");
				ArrayList<Lunch> mealslist = new ArrayList<Lunch>();
				for (String m : meal) {
					if (m.trim().isEmpty())
						continue;

					mealslist.add(new Lunch(m.trim()));
				}
				// Tue, 19 Mar 2013 07:25:00 +0100
				DateFormat df = new SimpleDateFormat(
						"EEE, dd MMM yyyy kk:mm:ss Z", Locale.ENGLISH);
				Date date = null;
				try {
					date = df.parse(pubDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				RestaurantCollection.getInstance().addItem(date,
						new Restaurant(name, adrTel, "", mealslist));
			}
		}
	}

}
