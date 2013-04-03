package com.example.lunchdroid.data;

import java.util.ArrayList;
import java.util.Date;

public class Item {
    public final String restaurantName;
    public final String restaurantAddress;
    public final ArrayList<String> restaurantLunch;
    public final Date restaurantDate;

    public Item(String restaurantName, String restaurantAddress, ArrayList<String> restaurantLunch, Date restaurantDate) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantLunch = restaurantLunch;
        this.restaurantDate = restaurantDate;
    }
}
