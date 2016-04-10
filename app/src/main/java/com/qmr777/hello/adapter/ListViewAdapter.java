package com.qmr777.hello.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qmr777.hello.R;
import com.qmr777.hello.model.TopStoriesModel;

import java.util.List;

/**
 * Created by qmr777 on 16-4-9.
 */
public class ListViewAdapter extends ArrayAdapter<TopStoriesModel.StoriesBean> {
    int resourceID;

    public ListViewAdapter(Context context, int resource, List<TopStoriesModel.StoriesBean> objects) {

        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TopStoriesModel.StoriesBean storiesBean = getItem(position);
        final ViewHolder viewHolder;
        View view;
        if(convertView == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.iv_story);
            viewHolder.textView = (TextView) view.findViewById(R.id.tv_story);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textView.setText(storiesBean.getTitle());
        //viewHolder.imageView.setBackground(new BitmapDrawable(Bitmap.createBitmap()));
        ImageLoader.getInstance().loadImage(storiesBean.getImages().get(0),new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                viewHolder.imageView.setBackground(new BitmapDrawable(loadedImage));
            }
        });

        //return super.getView(position, convertView, parent);
        return view;
    }

    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}