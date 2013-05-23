package com.example.lunchdroid;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;

//@SuppressLint("NewApi")
public class MenueActivity extends SherlockFragmentActivity {

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
		// bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

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

	/*
	 * TaBLISENER
	 * 
	 * @Override public void onTabReselected(Tab tab, FragmentTransaction ft) {
	 * // TODO Auto-generated method stub System.out.println("tab selected: " +
	 * tab.getText().toString()); }
	 * 
	 * @Override public void onTabSelected(Tab tab, FragmentTransaction ft) { //
	 * TODO Auto-generated method stub Fragment fragment = new Day_Fragment();
	 * Bundle args = new Bundle(); args.putInt(null, tab.getPosition());
	 * fragment.setArguments(args); // getFragmentManager().beginTransaction()
	 * // .replace(R.id.container, fragment).commit(); }
	 * 
	 * @Override public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	 * // TODO Auto-generated method stub
	 * 
	 * }
	 */

}