package com.qmr777.hello.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmr777.hello.R;
import com.qmr777.hello.task.GetLoadingImage;

import java.util.Timer;
import java.util.TimerTask;

public class Loading extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_aty);
        imageView = (ImageView) findViewById(R.id.iv_loading);
        textView = (TextView) findViewById(R.id.tv_loading);
        /*new GetLoadingImage(imageView,textView).execute();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(Loading.this, MainActivity.class));
            }
        },5000);*/
        new GetLoadingImage(imageView,textView).execute();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(Loading.this,MainActivity.class));
            }
        },3000);



    }
}
