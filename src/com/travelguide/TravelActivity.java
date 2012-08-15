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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TravelActivity extends Activity
{

public
	String locplace;
    Spinner radius;
    String srchplace;

       @Override
    public void onCreate(Bundle savedInstanceState) 
       {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   
        Button srch=(Button)findViewById(R.id.btnSearch);
        srch.setOnClickListener(new OnClickListener()
        {
       //***** This method called when search button is clicked *****

          public void onClick(View arg0)
            {
             try
             {
              locplace=((EditText)findViewById(R.id.editText)).getText().toString();
              radius=((Spinner) findViewById(R.id.spinner1));
              float miles =Float.valueOf(radius.getSelectedItem().toString());
              float meters =  (float) (miles * 1609.344);
              String lan= getIntent().getExtras().getString("lang");
              if((lan.equals("Español"))|| (lan.equals("Spanish")))
              {

            	  srchplace="https://maps.googleapis.com/maps/api/place/textsearch/xml?query="+"atracciones"+"+en+"+
                          locplace +"&radius=" + Float.toString(meters) +
                          "&sensor=false&key=AIzaSyAtt_UclSXdh99C8e0vFzh09edEENCJrXk";
               }
              else
              {

            		srchplace="https://maps.googleapis.com/maps/api/place/textsearch/xml?query="+"attractions"+"+in+"+
                                             locplace +"&radius=" + Float.toString(meters) +
                                             "&sensor=false&key=AIzaSyAtt_UclSXdh99C8e0vFzh09edEENCJrXk";
              }
              System.out.println("Search places " + srchplace);      

              InputMethodManager iMethodMgr =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
              iMethodMgr.hideSoftInputFromWindow(radius.getWindowToken(), 0);
                    
                      
               Intent i= new Intent(TravelActivity.this,GuideActivity.class) ;
               i.putExtra("com.travelguide.link",srchplace);
               i.putExtra("com.travelguide.location", locplace);
               i.putExtra("com.travelguide.radius", radius.getSelectedItem().toString());
               i.putExtra("tag", lan);
               startActivity(i);
        
             }
             catch(Throwable e)
             {
             System.out.println("error"+e);
             } 
              
            }
         });
       }
}

    

 

