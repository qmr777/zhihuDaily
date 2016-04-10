package com.qmr777.hello.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qmr777.hello.R;
import com.qmr777.hello.adapter.ListViewAdapter;
import com.qmr777.hello.model.NewsModel;
import com.qmr777.hello.model.TopStoriesModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    ListView listView;
    TopStoriesModel model;
    FloatingActionButton fab;
    CoordinatorLayout coordinatorLayout;
    ListViewAdapter adapter;
    Toolbar toolbar;

    String date;


    Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.d("helllo","case1");
                    adapter = new ListViewAdapter(MainActivity.this,
                            R.layout.layout_lv_cell,model.getStories());
                    listView.setAdapter(adapter);
                    break;
                case 2:
                    Log.d("helllo","case2");
                    //adapter.notifyDataSetChanged();
                    adapter = new ListViewAdapter(MainActivity.this,
                            R.layout.layout_lv_cell,model.getStories());
                    listView.setAdapter(adapter);
                    toolbar.setTitle(model.getDate());
                    break;
            }

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        date = data.getStringExtra("date");
        new Thread(runnable1).start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.view_mainAty);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.lv_mainAty);
        fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"fab",Toast.LENGTH_SHORT).show();
                //Snackbar.make(coordinatorLayout,"hahahah",Snackbar.LENGTH_SHORT).show();
                startActivityForResult(new Intent(MainActivity.this,GetPreData.class),0);

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        new Thread(runnable).start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        model.getStories().get(position).getId()+"",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("id",model.getStories().get(position).getId()+"");
                intent.putExtra("title",model.getStories().get(position).getTitle());
                startActivity(intent);
            }
        });


    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try{
                Gson gson = new Gson();
                URL url = new URL("http://news-at.zhihu.com/api/4/news/latest");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(8000);
                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String read;
                StringBuilder builder = new StringBuilder();
                while ((read = reader.readLine())!= null){
                    builder.append(read);
                    builder.append("\n\r");
                }
                model = gson.fromJson(builder.toString(),TopStoriesModel.class);
             //   Log.d("helllo",model.getStories().size()+"");
                Log.d("helllo",model.getStories().get(0).getTitle());

                Message message = Message.obtain();
                message.what = 1;
                mhandler.sendMessage(message);


            } catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            try{
                Gson gson = new Gson();
                URL url = new URL("http://news.at.zhihu.com/api/4/news/before/"+date);
                Log.d("helllo",("http://news.at.zhihu.com/api/4/news/before/"+date));

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(8000);
                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String read;
                StringBuilder builder = new StringBuilder();
                while ((read = reader.readLine())!= null){
                    builder.append(read);
                    builder.append("\n\r");
                }
                model = gson.fromJson(builder.toString(),TopStoriesModel.class);
                //Log.d("helllo",model.getStories().size()+"");
                Log.d("helllo",model.getStories().get(0).getTitle());

                Message message = Message.obtain();
                message.what = 2;
                mhandler.sendMessage(message);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
