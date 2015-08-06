package com.example.coupang.secondexample;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;


public class WebViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadData("<a href=coupang://detail?coupangSrl=4444>aaa</a>","text/html","utf-8");
//        webView.loadUrl("http://google.com");
    }


    // 웹뷰 관련 메소드

    class MyWebViewClient extends android.webkit.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            // URL 을 검사하여 무언가 행동을 취할 수 있다!

            if( url.contains("naver")) {
                Toast.makeText(WebViewActivity.this, url, Toast.LENGTH_SHORT).show();
                return  true;
            }

            if( url.contains("coupang")){
                Toast.makeText(WebViewActivity.this, url, Toast.LENGTH_SHORT).show();
                return  true;
            }

            return super.shouldOverrideUrlLoading(view, url);
        }
    }

//    class MyWebViewClient extends android.webkit.WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            // url = "myscheme://showItem?itemId=100"
//            Uri uri = Uri.parse(url);
//
//            if( "myscheme".equals(uri.getScheme()) && "showItemId".equals(uri.getHost()) ) {
//                String itemId = uri.getQueryParameter("itemId");
//                // do something
//            }
//            return super.shouldOverrideUrlLoading(view, url);
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
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
