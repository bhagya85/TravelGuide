/* 
 * Travelguide - An open source project for the Android platform, gives the tourist attractions, restaurants and hotels to visit for a given location within the given  * given radius in miles. The app supports English and Spanish languages for users.
 * Application written in Java
 * Application uses Google Places API.
 * the supported language texts are generated with the help of google translator.
 *
 * Copyright (C) 2012 Bhagya Yerabothula and Padmaja Matlaparti.
 *
 * Please see the file License in this distribution for license terms. Below is the link to the file License. 
 * https://github.com/bhagya85/TravelGuide/blob/master/License
 * 
 * Following is the link for the repository- https://github.com/bhagya85/TravelGuide
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation; with version 3 of the License.
 *  
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA 
 * 
 * Written by Bhagya Yerabothula bhagyalakshmi.y@gmail.com and Padmaja Matlaparti padmajamatlaparti@gmail.com
 *
 * Goolge Places API Reference - Tutorial on Google Places API and https://developers.google.com/places/documentation/ 
 * Android developers reference - https://developer.android.com/
 */
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
    	 int i = this.getName().size();
         if (this.getRating().size() == i)
         {
            // If rating size is equal to name size then we already added the default rating to this array element.
            this.rating.set(i-1,rating);
         }
         else
         {
         	this.rating.add(i-1,rating);
         }
         
         Log.i("Rating Index", ""+i);
         Log.i("This is the rating:", rating);
       
    }

}
