package com.example.lunchdroid;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

@SuppressLint("NewApi")
public class MenueActivity extends FragmentActivity  implements
ActionBar.OnNavigationListener{

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
		
		mTabsAdapter = new TabsAdapter(this, mViewPager);
		mTabsAdapter.addTab(bar.newTab().setText("Mo"), Day_Fragment.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Di"), Day_Fragment.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Mi"), Day_Fragment.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Do"), Day_Fragment.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Fr"), Day_Fragment.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Sa"), Day_Fragment.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("So"), Day_Fragment.class, null);
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
}