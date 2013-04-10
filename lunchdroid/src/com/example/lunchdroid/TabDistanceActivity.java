package com.example.lunchdroid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.data.RestaurantCollection;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class TabDistanceActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

//		TextView textview = new TextView(this);
//		textview.setText("Distance Tab is Selected");
//		setContentView(textview);

		List<Restaurant> todaysRestaurants = RestaurantCollection.getInstance().getRestaurantsByDay(LunchdroidHelper.getDateTodayZeroTime());
		Restaurant[] array = todaysRestaurants.toArray(new Restaurant[todaysRestaurants.size()]);
		 setListAdapter(new ListAdapter(this, array));
		//
		// ListView listView = getListView();
		// listView.setTextFilterEnabled(true);
		//
		// listView.setOnItemClickListener(new OnItemClickListener() {
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// // When clicked, show a toast with the TextView text
		// Toast.makeText(getApplicationContext(),
		// ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
		// }
		// });
	}
}
