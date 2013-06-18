package com.example.lunchdroid.test.data;

import java.util.ArrayList;

import com.example.lunchdroid.data.Lunch;
import com.example.lunchdroid.data.Restaurant;

import junit.framework.TestCase;

public class RestaurantTest extends TestCase {
		
	public void testRestaurantCreation() throws Exception {
		
	    ArrayList<Lunch> testList = new ArrayList<Lunch>();
	    Lunch lunch1 = new Lunch("test1", "1");
	    Lunch lunch2 = new Lunch("test2", "2");
	    
	    testList.add(lunch1);
	    testList.add(lunch2);
		
		Restaurant restaurant = new Restaurant("testRestaurant", "testAddress", "testTel", testList);
		
		assertTrue(restaurant.getRestaurantName().equals("testRestaurant"));
		assertTrue(restaurant.getRestaurantAddress().equals("testAddress"));
		assertTrue(restaurant.getRestaurantTelefon().equals("testTel"));
		assertNotNull(restaurant.getRestaurantLunch());
		assertTrue(restaurant.getRestaurantTelefon().equals("testTel"));
	}

}
