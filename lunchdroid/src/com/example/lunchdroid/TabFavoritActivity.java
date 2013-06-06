package com.example.lunchdroid;

import java.util.Collections;
import java.util.Comparator;
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
						LunchdroidHelper.getDateDayOfWeek("monday"));

		calcDistances(todaysRestaurants);

		Restaurant[] array = todaysRestaurants
				.toArray(new Restaurant[todaysRestaurants.size()]);
		setListAdapter(new ListAdapterFavorit(this, array));

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

	public void onCheckboxClicked(View view) {
		// Is the view now checked?
		boolean checked = ((CheckBox) view).isChecked();

		
		// Check which checkbox was clicked
		switch (view.getId()) {
		case R.id.favoritstar:
			if (checked) {
			} else { 
			}
			break;
		}
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
}
