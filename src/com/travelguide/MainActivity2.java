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

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity2 extends Activity 
{
	
	private WebView webview;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main2);
	    final String TAG = "Activity2";
	    
	    TextView t1 = (TextView) findViewById(R.id.thanks);
	    t1.setText("Thanks for using my App. Please don't forget to leave a review");
        
	    
	    TextView t2 = (TextView) findViewById(R.id.link);
	    t2.setText("Visitor locations found within " + getIntent().getExtras().getString("com.example.finalstep.radius") + " miles for " +getIntent().getExtras().getString("com.example.finalstep.location"));
        String url = getIntent().getExtras().getString("com.example.finalstep.link");
        Log.i(TAG, url);
           
        try{        	
        /* Parsing the xml file*/
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
          

        TextView example1[];
        TextView example2[];
        TextView example3[];
       
        tgxml data;
        data = tgparse.getXMLData();
        example1 = new TextView[data.getName().size()];
        example2 = new TextView[data.getName().size()];
        example3 = new TextView[data.getName().size()];
       
        webview = (WebView) findViewById(R.id.myWebView);
       
        String stg1 = new String();
        stg1 = "<html>";
        for (int i = 1; i < (data.getName().size()-2); i++) {
            Log.i(TAG, " "+i );
            Log.i(TAG, "Name= "+data.getName().get(i));
            Log.i(TAG, "Address= "+data.getAddress().get(i));
            Log.i(TAG, "Rating= "+data.getRating().get(i));
           
            example1[i] = new TextView(this);
            example1[i].setText("Name= "+data.getName().get(i));
            
            example2[i] = new TextView(this);
            example2[i].setText("Name= "+data.getAddress().get(i));
            
            example3[i] = new TextView(this);
            example3[i].setText("Name= "+data.getRating().get(i));
            
           
            stg1 = stg1 + "Name: " + data.getName().get(i) + "<br>" + " Address= "+data.getAddress().get(i) + "<br>" + " Rating= "+data.getRating().get(i) + "<br>" + "<br>";           
          
        }
        stg1 = stg1 + "</html>";
        webview.loadData(stg1, "text/html", null);
        
        }
        catch(Exception e)
        {
        	Log.i(TAG, "Exception caught", e);
          
        }
	
	   
	}

}