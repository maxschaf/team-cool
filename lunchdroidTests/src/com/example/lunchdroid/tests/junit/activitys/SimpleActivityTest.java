package com.example.lunchdroid.tests.junit.activitys;

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;

import com.example.lunchdroid.LunchdroidActivity;

//import de.vogella.android.test.target.SimpleActivity;
//import de.vogella.android.test.target.SimpleListActivity;

public class SimpleActivityTest extends
ActivityInstrumentationTestCase2<LunchdroidActivity> { 
	
	private Solo solo;

	public SimpleActivityTest() {
	    super(LunchdroidActivity.class);
    }

	public void setUp() throws Exception {
	   solo = new Solo(getInstrumentation(), getActivity());
    }
	public void testSmall () throws Exception {
		solo.assertCurrentActivity("wrong activiy", LunchdroidActivity.class);
	}
	

	@Override
    public void tearDown() throws Exception {
	   solo.finishOpenedActivities();
	}


}
