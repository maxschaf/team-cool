package com.example.lunchdroid;

import java.util.List;

import com.example.lunchdroid.data.Restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<Restaurant>{
	
	public ListAdapter(Context context, Restaurant[] objects) {
		super(context, R.layout.activity_distancelist, objects);
		this.context = context;
		this.values = objects;		
	}
	
	private final Context context;
	private final Restaurant[] values;
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.activity_distancelist, parent, false);
		TextView textViewLabel = (TextView) rowView.findViewById(R.id.label);
		TextView textViewAddress = (TextView) rowView.findViewById(R.id.address);
		textViewLabel.setText(values[position].getRestaurantName());
		textViewAddress.setText(values[position].getRestaurantAddress());
 

 
		return rowView;
	}
}
