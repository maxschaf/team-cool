package com.example.lunchdroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

	final int PAGE_COUNT = 2;

	/** Constructor of the class */
	public TabFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(int arg0) {
		Bundle data = new Bundle();
		switch (arg0) {
		/** Android tab is selected */
		case 0:
			TabDistanceFragment androidFragment = new TabDistanceFragment();
			data.putInt("current_page", arg0 + 1);
			androidFragment.setArguments(data);
			return androidFragment;

			/** Apple tab is selected */
		case 1:
			TabFavoritFragment appleFragment = new TabFavoritFragment();
			data.putInt("current_page", arg0 + 1);
			appleFragment.setArguments(data);
			return appleFragment;
		}
		return null;
	}

	/** Returns the number of pages */
	@Override
	public int getCount() {
		return PAGE_COUNT;
	}
}
