package com.technotalkative.loadwebimage;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class LoadImageWebActivity extends Activity {
    /** Called when the activity is first created. */
	
	ImageView imgLogo;
	ProgressBar progressbar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        imgLogo = (ImageView) findViewById(R.id.imageView1);
        progressbar = (ProgressBar) findViewById(R.id.loadingBar);
        
    } 

    public void btnLoadImageClick(View view)
    {
    	//imgLogo.setBackgroundDrawable(LoadImageFromWeb("http://www.android.com/media/wallpaper/gif/android_logo.gif"));
    	new loadImageTask().execute("http://www.android.com/media/wallpaper/gif/android_logo.gif");
    }
    
    public class loadImageTask extends AsyncTask<String, Void, Void>
    {
    	Drawable imgLoad;
    	
    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
    		super.onPreExecute();
    		
    		progressbar.setVisibility(View.VISIBLE);
    	}
    	
    	@Override
    	protected Void doInBackground(String... params) {
    		// TODO Auto-generated method stub
    		
    		imgLoad = LoadImageFromWeb(params[0]);
			return null;
    	}
    	
    	@Override
    	protected void onPostExecute(Void result) {
    		// TODO Auto-generated method stub
    		super.onPostExecute(result);
    		
    		if(progressbar.isShown())
    		{
    			progressbar.setVisibility(View.GONE);
    			imgLogo.setVisibility(View.VISIBLE);
    			imgLogo.setBackgroundDrawable(imgLoad);
    		}
    	}
    }
    
    public static Drawable LoadImageFromWeb(String url) 
    {
        try 
        {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}