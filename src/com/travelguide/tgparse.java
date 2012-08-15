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
