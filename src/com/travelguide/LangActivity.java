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

 /** Called when the activity is first created. */
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
          else if ((parent.getItemAtPosition(pos).toString()).equals("Espanol")||(parent.getItemAtPosition(pos).toString()).equals("Spanish")) {
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
          
           Toast.makeText(LangActivity.this, "Locale " +parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
         }
        
   public void onNothingSelected(AdapterView<?> arg0) {
    // TODO Auto-generated method stub
    
   }});
       
     }}