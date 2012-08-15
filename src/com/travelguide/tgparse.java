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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.travelguide.tgxml;
import com.travelguide.tgparse;

public class tgparse extends DefaultHandler{
	String elementValue = null;
    Boolean elementOn = false;
    public static tgxml data = null;
 
    public static tgxml getXMLData() {
        return data;
    }
 
    public static void setXMLData(tgxml data) {
    	tgparse.data = data;
    }
 
    /**
     * This will be called when the tags of the XML starts.
     **/
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
 
        elementOn = true;
 
       // if (localName.equals("result"))
        if (localName.equals("PlaceSearchResponse"))
        {
            data = new tgxml();
        } 
    }
 
    /**
     * This will be called when the tags of the XML end.
     **/
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
 
        elementOn = false;
 
        /**
         * Sets the values after retrieving the values from the XML tags
         * */
        if (localName.equalsIgnoreCase("name"))
        {
            data.setName(elementValue);
            // Rating is not available to all places.
            // So default it to Rating not available and if we see a rating we will update it later.
            data.setRating("Rating Not Available");
        }
        else if (localName.equalsIgnoreCase("formatted_address"))
            data.setAddress(elementValue);
        else if (localName.equalsIgnoreCase("rating"))
            data.setRating(elementValue);
    }
 
    /**
     * This is called to get the tags value
     **/
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
 
        if (elementOn) {
            elementValue = new String(ch, start, length);
            elementOn = false;
        }
 
    }

}
