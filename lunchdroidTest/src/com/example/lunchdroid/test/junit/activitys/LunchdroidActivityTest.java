package com.example.lunchdroid.test.junit.activitys;


import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;

import com.example.lunchdroid.LunchdroidActivity;
import com.jayway.android.robotium.solo.Solo;

import junit.framework.TestCase;

public class LunchdroidActivityTest extends ActivityInstrumentationTestCase2<LunchdroidActivity>{
	
	public LunchdroidActivityTest() {
		super(LunchdroidActivity.class);
		// TODO Auto-generated constructor stub
	}

	protected static final int TIMEOUT = 10000;

	
	private Solo solo;
	
	protected void setUp() throws Exception {
		super.setUp();	
		solo = new Solo(getInstrumentation(), getActivity());
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		solo.finishOpenedActivities();
	}

	public void testLunchdroidActivity(){
		assertTrue("Waiting for Lunchdorid Application", solo.waitForActivity(LunchdroidActivity.class,TIMEOUT));
		
	}
	
}
