package com.example.lunchdroid;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MenueActivity extends FragmentActivity {

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
		mTabsAdapter.addTab(bar.newTab().setText("Mo"), Fragment_Mo.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Di"), Fragment_Di.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Mi"), Fragment_Mi.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Do"), Fragment_Do.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Fr"), Fragment_Fr.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Sa"), Fragment_Sa.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("So"), Fragment_So.class, null);
	}
}