package com.example.lunchdroid;

import com.example.lunchdroid.data.Restaurant;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ListAdapterFavorit extends ArrayAdapter<Restaurant> {

	public ListAdapterFavorit(Context context, Restaurant[] objects) {
		super(context, R.layout.activity_favoritlist, objects);
		this.context = context;
		this.values = objects;
	}

	private final Context context;
	private final Restaurant[] values;

	private class ViewHolder {
		TextView label;
		TextView distance;
		CheckBox favorit;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.activity_favoritlist, null);

			holder = new ViewHolder();
			holder.label = (TextView) convertView.findViewById(R.id.label);
			holder.distance = (TextView) convertView
					.findViewById(R.id.distance);
			holder.favorit = (CheckBox) convertView
					.findViewById(R.id.favoritstar);
			convertView.setTag(holder);

			holder.favorit.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					CheckBox cb = (CheckBox) v;
					Restaurant restaurant = (Restaurant) cb.getTag();
//					Toast.makeText(
//							context,
//							"Clicked on Checkbox: " + cb.getText() + " is "
//									+ cb.isChecked(), Toast.LENGTH_LONG).show();
					restaurant.setIsFavorit(cb.isChecked());
				}
			});
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Restaurant restaurant = values[position];
		holder.label.setText(values[position].getRestaurantName());
		holder.distance.setText(LunchdroidHelper.getDistanceText(values[position].getRestaurantDistance()));
		holder.favorit.setChecked(restaurant.getIsFavorit());
		holder.favorit.setTag(restaurant);

		return convertView;
	}
}
