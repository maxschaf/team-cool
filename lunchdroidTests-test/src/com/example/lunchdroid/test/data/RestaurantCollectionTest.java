package com.example.lunchdroid.test.data;

import java.util.ArrayList;
import java.util.Date;

import android.util.Log;

import com.example.lunchdroid.LunchdroidHelper;
import com.example.lunchdroid.data.Lunch;
import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.data.RestaurantCollection;

import junit.framework.TestCase;

public class RestaurantCollectionTest extends TestCase {

	public void testRestaurantCollectionCreation() throws Exception {
		
		RestaurantCollection testcollection = RestaurantCollection.getInstance();
		
	    ArrayList<Lunch> testList = new ArrayList<Lunch>();
	    Lunch lunch1 = new Lunch("test1", "1");
	    Lunch lunch2 = new Lunch("test2", "2");	    
	    testList.add(lunch1);
	    testList.add(lunch2);
		
		Restaurant restaurant1 = new Restaurant("testRestaurant1", "testAddress1", "testTel1", testList);
		Restaurant restaurant2 = new Restaurant("testRestaurant2", "testAddress2", "testTel2", testList);
		Restaurant restaurant3 = new Restaurant("testRestaurant3", "testAddress3", "testTel3", testList);
		Restaurant restaurant4 = new Restaurant("testRestaurant4", "testAddress4", "testTel4", testList);
		Restaurant restaurant5 = new Restaurant("testRestaurant5", "testAddress5", "testTel5", testList);
		
		Date testdate = LunchdroidHelper.getDateTodayZeroTime();
		
		testcollection.addItem(testdate, restaurant1);
		testcollection.addItem(testdate, restaurant2);
		testcollection.addItem(testdate, restaurant3);
		testcollection.addItem(testdate, restaurant4);
		testcollection.addItem(testdate, restaurant5);
		
		assertNotNull(testcollection);
		assertTrue(testcollection.getNumberOfRestaurants() == 5);
				
	}
	
	public void testRestaurantNumberLimit() throws Exception {
		
		RestaurantCollection testcollection = RestaurantCollection.getInstance();

		
	    ArrayList<Lunch> testList = new ArrayList<Lunch>();
	    Lunch lunch1 = new Lunch("test1", "1");
	    testList.add(lunch1);
	    Date testdate = LunchdroidHelper.getDateTodayZeroTime();
	    
	    int size = 0;
	    size = testcollection.getNumberOfRestaurants();
	    
	    for(int i = 0; i < 500; i++) {
	    
		size = testcollection.getNumberOfRestaurants();
	    	
		Restaurant restaurant1 = new Restaurant("testRestaurant" + i, "testAddress1", "testTel1", testList);
		testcollection.addItem(testdate, restaurant1);
		
		Log.w("Lunchdroid",
				String.valueOf(size) + " items in RestaurantCollection.");
		
		assertTrue(testcollection.getNumberOfRestaurants() == (size + 1));
		
	    }
	    
	}
	
}
