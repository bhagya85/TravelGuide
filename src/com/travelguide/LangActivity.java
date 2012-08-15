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
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
 

public class LangActivity extends Activity{
Bundle langitm;
Intent intent;
Locale locale;

 //***** Called when the activity is first created *****
 @Override
 public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_lang);
     final String TAG = "LangActivty";
     
     TextView t = (TextView) findViewById(R.id.lt);
     Spinner spin = (Spinner) findViewById(R.id.langspin);
       
     Log.i(TAG,"Status");
     ArrayAdapter<CharSequence> adp = ArrayAdapter.createFromResource(this,R.array.lang_arrays,android.R.layout.simple_list_item_1);
     adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     spin.setAdapter(adp);
     intent = new Intent(LangActivity.this, TravelActivity.class);
     spin.setOnItemSelectedListener(new OnItemSelectedListener(){
        
          public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)  {
          langitm = new Bundle();
          langitm.putInt("item", pos);
          langitm.putString("language", parent.getItemAtPosition(pos).toString());
          System.out.println("la" +parent.getItemAtPosition(pos).toString());
          Configuration conf = getResources().getConfiguration();
          System.out.println("cong" +conf);
          
          if ((parent.getItemAtPosition(pos).toString()).equals("Inglés")||(parent.getItemAtPosition(pos).toString()).equals("English")) {
           Locale locale = new Locale("en-US");
              Locale.setDefault(locale);
              Configuration config = new Configuration();
              config.locale = locale;
              getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
              intent.putExtra("lang",parent.getItemAtPosition(pos).toString());
               startActivity(intent);
             }
          else if ((parent.getItemAtPosition(pos).toString()).equals("Español")||(parent.getItemAtPosition(pos).toString()).equals("Spanish")) {
              Locale locale = new Locale("es");
              
              Locale.setDefault(locale);
              Resources res= getBaseContext().getResources();
              DisplayMetrics con = res.getDisplayMetrics();
              android.content.res.Configuration cnf = res.getConfiguration();
              cnf.locale = locale;
             
              res.updateConfiguration(cnf, con);
              intent.putExtra("lang",parent.getItemAtPosition(pos).toString());
              startActivity(intent);
              }
          Configuration con = getResources().getConfiguration();
          System.out.println("congr" +con);
          
        }
        
   public void onNothingSelected(AdapterView<?> arg0) {
    // TODO Auto-generated method stub
    
   }});
       
     }}