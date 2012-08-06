package com.travelguide;

import java.util.ArrayList;
import android.util.Log;

public class tgxml {
	private ArrayList<String> name = new ArrayList<String>();
    private  ArrayList<String> address = new ArrayList<String>();
    private  ArrayList<String> rating = new ArrayList<String>();
    public ArrayList<String> getName() {
        return name;
    }
    public ArrayList<String> getAddress() {
        return address;
    }
    public ArrayList<String> getRating() {
        return rating;
    }
    
    public void setName(String name) {
        this.name.add(name);
        Log.i("This is the name:", name);
    }
    public void setAddress(String address) {
        this.address.add(address);
        Log.i("This is the address:", address);
      
    }
    public void setRating(String rating) {
        this.rating.add(rating);
        Log.i("This is the rating:", rating);
       
    }

}
