package com.example.lunchdroid.data;

import java.util.ArrayList;
import java.util.List;


//Speichert alle Items, Singleton!
public final class ItemCollection {
 
	    private static ItemCollection mInstance;
	    private List<Item> mItems;
	 
	    private ItemCollection() {
	    	 mItems = new ArrayList<Item>();
	    }
	 
	    //kritisch für nebenläufigkeit, sollte abstrakter gehalten werden
	    public List<Item> getItems() {
			return mItems;
		}
	    
	    public synchronized void addItems(List<Item> items){
	    	mItems.addAll(items);
	    }
	    
	    public synchronized void addItem(Item item){
	    	mItems.add(item);
	    }

		public synchronized static ItemCollection getInstance() 
	    {
	        if (mInstance == null) 
	        {
	            mInstance = new ItemCollection();
	        }
	        return mInstance;
	    }
	}
