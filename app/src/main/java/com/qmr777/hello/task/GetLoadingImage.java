package com.qmr777.hello.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by qmr777 on 16-4-9.
 */
public class GetLoadingImage extends AsyncTask<Void,Void,Bitmap> {
    ImageView imageView;
    TextView textView;
    String author;
    public GetLoadingImage(ImageView imageView,TextView textView){
        this.imageView = imageView;
        this.textView = textView;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try{
            URL url = new URL("http://news-at.zhihu.com/api/4/start-image/1080*1776");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String read;
            StringBuilder builder = new StringBuilder();
            while ((read = bufferedReader.readLine())!= null){
                builder.append(read);
                builder.append("\n\r");
            }
            //return builder.toString();
            //获取图片
            JSONObject jsonObject = new JSONObject(builder.toString());
            author = jsonObject.getString("text");
            URL imageUrl = new URL(jsonObject.getString("img"));
            HttpURLConnection imageconnection = (HttpURLConnection) imageUrl.openConnection();
            is = imageconnection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        textView.setText(author);
        if(bitmap!= null){
            imageView.setBackground(new BitmapDrawable(bitmap));
        }
    }
}
