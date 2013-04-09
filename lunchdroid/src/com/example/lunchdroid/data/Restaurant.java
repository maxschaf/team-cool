package com.example.lunchdroid.data;

import java.util.ArrayList;
import java.util.Date;

public class Restaurant {
    private final String restaurantName;
    private final String restaurantAddress;
    private final String restaurantTelefon;
    private final ArrayList<Lunch> restaurantLunch;

    public Restaurant(String restaurantName, String restaurantAddress, String restaurantTelefon, ArrayList<Lunch> restaurantLunch) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantTelefon = restaurantTelefon;
        this.restaurantLunch = restaurantLunch;
    }
}
