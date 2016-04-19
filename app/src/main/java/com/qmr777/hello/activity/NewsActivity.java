package com.qmr777.hello.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qmr777.hello.R;
import com.qmr777.hello.task.GetNewsTask;

public class NewsActivity extends AppCompatActivity {
    ImageView imageView;
    WebView webView;
    TextView title,subtitle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        imageView = (ImageView) findViewById(R.id.iv_news);
        webView = (WebView) findViewById(R.id.wv_news);
        title = (TextView) findViewById(R.id.tv_title);
        subtitle = (TextView) findViewById(R.id.tv_sub_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        new GetNewsTask(webView,imageView,title,subtitle).execute(getIntent().getStringExtra("id"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case ActionBar.DISPLAY_HOME_AS_UP:
                finish();
                Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
