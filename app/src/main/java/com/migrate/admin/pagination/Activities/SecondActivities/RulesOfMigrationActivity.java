package com.migrate.admin.pagination.Activities.SecondActivities;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.migrate.admin.pagination.Adapters.RVMigSecondAdapter;
import com.migrate.admin.pagination.R;
import com.migrate.admin.pagination.Serializables.Mig2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RulesOfMigrationActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    List<Mig2> list;
    WebView webView;
    RVMigSecondAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_of_migration2);
        String name=getIntent().getStringExtra("name");
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(name);
        webView=(WebView) findViewById(R.id.web_view);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        list=new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);
        webView.getSettings().setBuiltInZoomControls(true);

        String ss="<?xml version=\"1.0\"encoding=\"UTF-8\"?>"+"<style>img{display: block;max-height: 100%;max-width: 100%;}</style>";

        String s ;
        s= '"' + getIntent().getStringExtra("text") + '"';
        StringBuilder st=new StringBuilder(s);
        st.deleteCharAt(st.length()-1);
        st.deleteCharAt(0);
        s=ss+st.toString();
        //String s1=s.replaceAll("src=\"","src=\"http://176.126.167.249/");
        //Log.e("TAG_KKxxKK",s1);
      //  String pattern = "src=.*%3E/";
      //  Pattern p = Pattern.compile(pattern);
      //  Matcher m = p.matcher(s);
       // String s1 = m.replaceAll("src=file:///android_res/raw/");
              //  %3Cfunction%20image_upload_to%20at%200x2b44f1c647d0%3E
               // %3Cfunction%20image_upload_to%20at%200x2b3b91c63488%3E
                //%3Cfunction%20image_upload_to%20at%200x2b44f1c647d0%3E
        Mig2 mig2;
//        webView.loadDataWithBaseURL(null,s,"text/html", "ru-RU",null);
        webView.loadDataWithBaseURL(null, "<style>img{display: inline;height: auto;max-width: 100%;}</style>" +s, "text/html", "UTF-8", null);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        Log.e("TAG_WE",webView+"");
        webView.setFocusableInTouchMode(false);
        webView.setFocusable(false);
        webView.setClickable(false);
        webView.setWebViewClient(new MyWebViewClient());
        WebView web = new WebView(this);
        web.setPadding(0, 0, 0, 0);
        web.setInitialScale(getScale());
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
    }

    private int getScale(){
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width)/new Double(100);
        val = val * 100d;
        return val.intValue();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //  view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }
}