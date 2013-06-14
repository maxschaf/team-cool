package com.example.lunchdroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.actionbarsherlock.app.SherlockListFragment;
import com.example.lunchdroid.data.Lunch;
import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.data.RestaurantCollection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A dummy fragment representing a section of the app, but that simply displays
 * dummy text.
 */
public class RestaurantDetailFragment extends SherlockListFragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";

	List<Lunch> mLunches;

	public RestaurantDetailFragment() {
	}

	@SuppressLint("DefaultLocale")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Locale l = Locale.getDefault();

		View rootView = inflater.inflate(
				R.layout.fragment_restaurant_detail, container, false);
		TextView tvRestaurantName = (TextView) rootView
				.findViewById(R.id.restaurant_name);
		TextView tvRestaurant = (TextView) rootView
				.findViewById(R.id.restaurant);

		String restaurantname = ((RestaurantDetailActivity) getActivity()).mRestaurant
				.getRestaurantName();
		tvRestaurantName.setText(restaurantname.toUpperCase(l));

		ClickListenerRestaurantButton rcl = new ClickListenerRestaurantButton();
		tvRestaurantName.setOnClickListener(rcl);
		tvRestaurant.setOnClickListener(rcl);

		int daykey = getArguments().getInt(ARG_SECTION_NUMBER);
		Restaurant restaurant = RestaurantCollection.getInstance()
				.getRestaurantByDayAndName(
						LunchdroidHelper.getWeekDayByNumber(daykey),
						restaurantname);
		if (restaurant != null) {
			mLunches = restaurant.getRestaurantLunch();
		} else {
			mLunches = new ArrayList<Lunch>();
		}

		return rootView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		Lunch[] array = mLunches.toArray(new Lunch[mLunches.size()]);
		setListAdapter(new RestaurantDetailLunchListAdapter(getActivity()
				.getBaseContext(), array));
	}

	public class ClickListenerRestaurantButton implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(getActivity().getBaseContext(),
					ContactActivity.class);

			intent.putExtra("restaurantid",
					((RestaurantDetailActivity) getActivity()).mRestaurant
							.getRestaurantId());
			startActivity(intent);
		}

	}
}