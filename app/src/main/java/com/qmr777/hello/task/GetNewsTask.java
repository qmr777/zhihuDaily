package com.qmr777.hello.task;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qmr777.hello.model.NewsModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by qmr777 on 16-4-9.
 */
public class GetNewsTask extends AsyncTask<String,String,String> {
    WebView webView;
    ImageView imageView;
    NewsModel model;
    TextView title,subtitle;


    public GetNewsTask(WebView webView, ImageView imageView,TextView title,TextView subtitle){
        this.imageView = imageView;
        this.webView = webView;
        this.title = title;
        this.subtitle = subtitle;
        //this.webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected String doInBackground(String... params) {
        String url = "http://news-at.zhihu.com/api/4/news/"+params[0];
        try {
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String read;
            StringBuilder builder = new StringBuilder();
            while ((read = reader.readLine())!= null){
                builder.append(read);
                builder.append("\n\r");
            }
            Gson gson = new Gson();
            model = gson.fromJson(builder.toString(),NewsModel.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //webView.loadData(model.getBody(),"text/html", "UTF-8");
        //webView.getSettings().
        title.setText(model.getTitle());
        subtitle.setText(model.getImage_source());
        String css = "<link rel=\"stylesheet\" href=\""+model.getCss().get(0)+"\" type=\"text/css\" />";
        webView.loadDataWithBaseURL("",css+model.getBody(),"text/html", "UTF-8","");
                //.loadDataWithBaseURL("",model.getCss().get(0),"text/css","UTF-8","");
        ImageLoader.getInstance().loadImage(model.getImage(),new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                //imageView.setBackground(new BitmapDrawable(loadedImage));
                imageView.setImageBitmap(loadedImage);
            }
        });
    }
}
