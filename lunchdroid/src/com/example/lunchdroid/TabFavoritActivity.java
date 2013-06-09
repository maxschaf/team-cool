package com.example.lunchdroid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.data.RestaurantCollection;
import com.example.lunchdroid.geo.Locator;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class TabFavoritActivity extends ListActivity {
	private List<Restaurant> todaysRestaurants;
	
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		TextView textview = new TextView(this);
		textview.setText("Keine Einträge vorhanden.");
		textview.setVisibility(View.GONE);
		((ViewGroup) getListView().getParent()).addView(textview);
		getListView().setEmptyView(textview);

		todaysRestaurants = RestaurantCollection.getInstance()
				.getRestaurantsByDay(
						LunchdroidHelper.getDateDayOfWeek(LunchdroidHelper
								.getNextWorkdayDayname()));

		calcDistances(todaysRestaurants);

//		List<Restaurant> temp = new ArrayList<Restaurant>(todaysRestaurants);
//		removeNonFavorit(temp);
//
//		Restaurant[] array = temp
//				.toArray(new Restaurant[temp.size()]);
//		setListAdapter(new ListAdapterFavorit(this, array));
	}

	private void removeNonFavorit(List<Restaurant> todaysRestaurants) {
		Iterator<Restaurant> i = todaysRestaurants.iterator();
		while (i.hasNext()) {
			Restaurant r = i.next(); 
			if (!r.getIsFavorit()) {
				i.remove();
			}
		}
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

	// todo blockiert, bis location gefunden wurde... koennte ewig haengen
	// bleiben
	private List<Restaurant> calcDistances(List<Restaurant> todaysRestaurants) {
		double distance;
		for (Restaurant r : todaysRestaurants) {
			distance = Locator.getInstance().getDistance(
					r.getRestaurantAddress());
			r.setRestaurantDistance((int) (Math.ceil(distance / 50d) * 50d));
		}

		Collections.sort(todaysRestaurants, new Comparator<Restaurant>() {
			@Override
			public int compare(final Restaurant object1,
					final Restaurant object2) {
				return object1.getRestaurantDistance() < object2
						.getRestaurantDistance() ? -1 : object1
						.getRestaurantDistance() == object2
						.getRestaurantDistance() ? 0 : 1;
			}
		});

		return todaysRestaurants;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		List<Restaurant> temp = new ArrayList<Restaurant>(todaysRestaurants);
		removeNonFavorit(temp);

		Restaurant[] array = temp
				.toArray(new Restaurant[temp.size()]);
		setListAdapter(new ListAdapterFavorit(this, array));
	}
}
