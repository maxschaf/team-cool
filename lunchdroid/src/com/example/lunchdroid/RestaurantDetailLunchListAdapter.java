package com.example.lunchdroid;

import com.example.lunchdroid.data.Lunch;
import com.example.lunchdroid.data.Restaurant;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantDetailLunchListAdapter extends ArrayAdapter<Lunch> {

	public RestaurantDetailLunchListAdapter(Context context, Lunch[] objects) {
		super(context, R.layout.fragment_lunch_row, objects);
		this.context = context;
		this.values = objects;
	}

	private final Context context;
	private final Lunch[] values; 

	private class ViewHolder {
		TextView label;
		TextView price;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.fragment_lunch_row, null);

			holder = new ViewHolder();
			holder.label = (TextView) convertView.findViewById(R.id.label);
			holder.price = (TextView) convertView
					.findViewById(R.id.price);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Lunch lunch = values[position];
		holder.label.setText(lunch.getName());
		holder.price.setText(lunch.getPrice());
		return convertView;
	}
}
