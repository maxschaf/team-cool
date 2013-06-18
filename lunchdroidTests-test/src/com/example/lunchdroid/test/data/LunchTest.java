package com.example.lunchdroid.test.data;

import com.example.lunchdroid.data.Lunch;
import com.example.lunchdroid.data.RestaurantCollection;

import junit.framework.TestCase;

public class LunchTest extends TestCase {

	public void testLunchCreation() throws Exception {
		Lunch lunch = new Lunch("TestLunch1", "15");
		
		assertNotNull(lunch);
		assertTrue(lunch.getName().equals("TestLunch1"));
		assertTrue(lunch.getPrice().equals("15"));
	}

}
