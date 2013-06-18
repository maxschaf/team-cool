package com.example.lunchdroid.test.data;

import android.test.ActivityInstrumentationTestCase2;

import com.example.lunchdroid.LunchdroidActivity;
import com.example.lunchdroid.data.PersistentPreferences;


public class PersistentPreferenceTest extends
		ActivityInstrumentationTestCase2<LunchdroidActivity> {
	public PersistentPreferenceTest() {
		super(LunchdroidActivity.class);
		// TODO Auto-generated constructor stub
	}

	PersistentPreferences pr;

	public void testPersistentPreferences() {
		pr = new PersistentPreferences(getActivity(),
				"TEST");

		assertNotNull(pr);

		pr.setState("token1", true);

		assertTrue(pr.getState("token1"));

		pr.setState("token1", false);

		assertFalse(pr.getState("token1"));

	}
}
