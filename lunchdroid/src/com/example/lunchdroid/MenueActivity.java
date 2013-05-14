package com.example.lunchdroid;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

@SuppressLint("NewApi")
public class MenueActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager mViewPager;
	private TabsAdapter mTabsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.pager);
		setContentView(mViewPager);

		final ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

		mTabsAdapter = new TabsAdapter(this, mViewPager);
		mTabsAdapter.addTab(bar.newTab().setText("Mo"), Day_Fragment.class,
				null);
		mTabsAdapter.addTab(bar.newTab().setText("Di"), Day_Fragment.class,
				null);
		mTabsAdapter.addTab(bar.newTab().setText("Mi"), Day_Fragment.class,
				null);
		mTabsAdapter.addTab(bar.newTab().setText("Do"), Day_Fragment.class,
				null);
		mTabsAdapter.addTab(bar.newTab().setText("Fr"), Day_Fragment.class,
				null);
		mTabsAdapter.addTab(bar.newTab().setText("Sa"), Day_Fragment.class,
				null);
		mTabsAdapter.addTab(bar.newTab().setText("So"), Day_Fragment.class,
				null);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		System.out.println("tab selected: " + tab.getText().toString());
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		Fragment fragment = new Day_Fragment();
		Bundle args = new Bundle();
		args.putInt(null, tab.getPosition() + 1);
		fragment.setArguments(args);
		// getFragmentManager().beginTransaction()
		// .replace(R.id.container, fragment).commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}