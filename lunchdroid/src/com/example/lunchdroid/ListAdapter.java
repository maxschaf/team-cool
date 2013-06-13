package com.example.lunchdroid;

import java.util.List;

import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.geo.Locator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<Restaurant>{
	
	public ListAdapter(Context context, Restaurant[] objects) {
		super(context, R.layout.fragment_tab_favoritlist, objects);
		this.context = context;
		this.values = objects;	
 
	}
	
	private final Context context;
	private final Restaurant[] values;
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.fragment_tab_favoritlist, parent, false);
		TextView textViewLabel = (TextView) rowView.findViewById(R.id.label);
		TextView textViewDistance = (TextView) rowView.findViewById(R.id.distance);
		textViewLabel.setText(values[position].getRestaurantName());
		
		textViewDistance.setText(values[position].getRestaurantDistance() + "m");
 

 
		return rowView;
	}
}
