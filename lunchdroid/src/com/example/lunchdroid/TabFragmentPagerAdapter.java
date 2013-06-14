package com.example.lunchdroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

	public TabFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(int arg0) {
		Bundle data = new Bundle();
		switch (arg0) {
		case 0:
			TabDistanceFragment distanceFragment = new TabDistanceFragment();
			data.putInt("current_page", arg0 + 1);
			distanceFragment.setArguments(data);			
			return distanceFragment;

		case 1:
			TabFavoritFragment favoritFragment = new TabFavoritFragment();
			data.putInt("current_page", arg0 + 1);
			favoritFragment.setArguments(data);
			return favoritFragment;
		}
		return null;
	}

	/** Returns the number of pages */
	@Override
	public int getCount() {
		return 2;
	}
}
