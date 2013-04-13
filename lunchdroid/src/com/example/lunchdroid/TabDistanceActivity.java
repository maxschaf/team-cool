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
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Date;

import android.util.Log;

public class TabDistanceActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		TextView textview = new TextView(this);
		textview.setText("Keine Einträge vorhanden.");
		textview.setVisibility(View.GONE);
		((ViewGroup) getListView().getParent()).addView(textview);
		getListView().setEmptyView(textview);

		List<Restaurant> todaysRestaurants = RestaurantCollection.getInstance()
				.getRestaurantsByDay(
						LunchdroidHelper.getDateDayOfWeek("sunday"));
		Restaurant[] array = todaysRestaurants
				.toArray(new Restaurant[todaysRestaurants.size()]);
		setListAdapter(new ListAdapter(this, array));

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// get selected items
		Restaurant selectedValue = (Restaurant) getListAdapter().getItem(
				position);

		Log.w("Lunchdroid",
				"Distance: restaurantid:" + selectedValue.getRestaurantId());

		Intent intent = new Intent(this, ContactActivity.class);
		intent.putExtra("restaurantid", selectedValue.getRestaurantId());
		// Toast.makeText(this, selectedValue.getRestaurantName(),
		// Toast.LENGTH_SHORT).show();
		startActivity(intent);

	}
}
