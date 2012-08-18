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

import java.io.ByteArrayInputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.travelguide.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

public class GuideActivity extends Activity 
{
	
	private WebView webview;

	//***** Called when the activity is first created *****
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main2);
	    final String TAG = "Activity2";
	    
	    
	    String ln = getIntent().getExtras().getString("tag");
	    if((ln.equals("Español"))|| (ln.equals("Spanish")))
        {
	    	TextView t1 = (TextView) findViewById(R.id.thanks);        
	        t1.setText("Gracias por usar mi aplicación.");
        
	    
	        TextView t2 = (TextView) findViewById(R.id.link);
	         t2.setText("Las ubicaciones de visitantes en el interior " + getIntent().getExtras().getString("com.travelguide.radius") + " millas para " +getIntent().getExtras().getString("com.travelguide.location"));
              
        }
	    else
	    {
	    	TextView t1 = (TextView) findViewById(R.id.thanks);	        
		    t1.setText("Thanks for using my App.");
	        
		    
		    TextView t2 = (TextView) findViewById(R.id.link);
		    t2.setText("Visitor locations found within " + getIntent().getExtras().getString("com.travelguide.radius") + " miles for " +getIntent().getExtras().getString("com.travelguide.location"));
	        
	        
	    }
	    
	    String url = getIntent().getExtras().getString("com.travelguide.link");
        Log.i(TAG, url);
           
        try{        	
        //***** Parsing the xml file*****
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        XMLReader xr = sp.getXMLReader();
 
        tgparse myXML_parser = new tgparse();
        xr.setContentHandler(myXML_parser);
     
       
        HttpClient httpclient = new DefaultHttpClient();
       HttpGet httpget = new HttpGet(url.replace(" ", "%20"));
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = httpclient.execute(httpget, responseHandler);
        Log.i(TAG,"responseBody: " + responseBody);	
        ByteArrayInputStream is = new ByteArrayInputStream(responseBody.getBytes());
        xr.parse(new InputSource(is));
        
        
        Log.i(TAG, "parse complete");        
          

        TextView placename[];
        TextView placeaddress[];
        TextView placerating[];
       
        tgxml data;
        data = tgparse.getXMLData();
        placename = new TextView[data.getName().size()];
        placeaddress = new TextView[data.getName().size()];
        placerating = new TextView[data.getName().size()];
       
        webview = (WebView) findViewById(R.id.myWebView);
        
    //    webview.setBackgroundColor(0);
    //    webview.setBackgroundResource(R.drawable.openbook);
       
        String stg1 = new String();
        stg1 = "<html>";
        for (int i = 1; i < (data.getName().size()); i++) {
            Log.i(TAG, " "+i );
            Log.i(TAG, "Name= "+data.getName().get(i));
            Log.i(TAG, "Address= "+data.getAddress().get(i));
            Log.i(TAG, "Rating= "+data.getRating().get(i));
           
            placename[i] = new TextView(this);
            placename[i].setText("Name= "+data.getName().get(i));
            
            placeaddress[i] = new TextView(this);
            placeaddress[i].setText("Address= "+data.getAddress().get(i));
            
            placerating[i] = new TextView(this);
            placerating[i].setText("Rating= "+data.getRating().get(i));
            
           
            if((ln.equals("Español"))|| (ln.equals("Spanish")))
            {
        	   stg1 = stg1 + "Nombre: " + data.getName().get(i) + "<br>" + " Dirección: "+data.getAddress().get(i) + "<br>" + " clasificación= "+data.getRating().get(i) + "<br>" + "<br>";           
            }
    	    else
    	    {
    	    	stg1 = stg1 + "Name: " + data.getName().get(i) + "<br>" + " Address: "+data.getAddress().get(i) + "<br>" + " Rating= "+data.getRating().get(i) + "<br>" + "<br>";
    	    }           
          
        }
        stg1 = stg1 + "</html>";
        webview.loadDataWithBaseURL (null, stg1, "text/html", "utf-8","about:blank");
        
        }
        catch(Exception e)
        {
        	Log.i(TAG, "Exception caught", e);
          
        }
	
	   
	}

}