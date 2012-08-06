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

public class MainActivity extends Activity
{

public
	String locplace;
    Spinner radius;

       @Override
    public void onCreate(Bundle savedInstanceState) 
       {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   
        Button srch=(Button)findViewById(R.id.btnSearch);
        srch.setOnClickListener(new OnClickListener()
        {

          public void onClick(View arg0)
            {
             try
             {
              locplace=((EditText)findViewById(R.id.editText)).getText().toString();
              radius=((Spinner) findViewById(R.id.spinner1));
              float miles =Float.valueOf(radius.getSelectedItem().toString());
              float meters =  (float) (miles * 1609.344);
              String srchplace="https://maps.googleapis.com/maps/api/place/textsearch/xml?query="+"attractions"+"+in+"+
                                 locplace +"&radius=" + Float.toString(meters) +
                                 "&sensor=false&key=AIzaSyCIX1BytGb-CQiASpdE0et05e5SkJl7Q78";
              System.out.println("Search places " + srchplace);      

              InputMethodManager iMethodMgr =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
              iMethodMgr.hideSoftInputFromWindow(radius.getWindowToken(), 0);
                    
                      
               Intent i= new Intent(MainActivity.this,MainActivity2.class) ;
               i.putExtra("com.example.finalstep.link",srchplace);
               i.putExtra("com.example.finalstep.location", locplace);
               i.putExtra("com.example.finalstep.radius", radius.getSelectedItem().toString());
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

    

 

