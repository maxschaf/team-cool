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
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TabDistanceActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// TextView textview = new TextView(this);
		// textview.setText("Distance Tab is Selected");
		// setContentView(textview);

		List<Restaurant> todaysRestaurants = RestaurantCollection.getInstance()
				.getRestaurantsByDay(LunchdroidHelper.getDateTodayZeroTime());
		Restaurant[] array = todaysRestaurants
				.toArray(new Restaurant[todaysRestaurants.size()]);
		setListAdapter(new ListAdapter(this, array));

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// get selected items
		Restaurant selectedValue = (Restaurant)getListAdapter().getItem(position);
		Toast.makeText(this, selectedValue.getRestaurantName(), Toast.LENGTH_SHORT).show();

	}
}
