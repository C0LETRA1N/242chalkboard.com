package com.christophercole.chalkboard;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomWebView webView = new CustomWebView(this);
        setContentView(webView);
    }

    class CustomWebView extends WebView {
        private boolean flinged;

        Context context;
        GestureDetector gd;

        public CustomWebView(Context context) {
            super(context);

            this.context = context;
            gd = new GestureDetector(context, sogl);
            setWebViewClient(new WebViewClient());
            getSettings().setJavaScriptEnabled(true);
            loadUrl("http://242chalkboard.com");
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            gd.onTouchEvent(event);
            if(flinged){
                flinged = false;
                return true;
            } else
            {
                return super.onTouchEvent(event);
            }
        }

        GestureDetector.SimpleOnGestureListener sogl = new GestureDetector.SimpleOnGestureListener() {
            public boolean onDown(MotionEvent event) {
                return true;
            }

            public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
                if (event1.getRawX() > event2.getRawX()) {
                    //show_toast("swipe left");
                    if(canGoForward()){
                        goForward();;
                    }
                    flinged = true;
                } else {
                    //show_toast("swipe right");
                    if(canGoBack()){
                        goBack();
                    }
                    flinged = true;
                }
                return true;
            }
        };

        void show_toast(final String text) {
            Toast t = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            t.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}




