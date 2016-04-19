package com.qmr777.hello.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
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
import com.qmr777.hello.task.GetStoryImageTask;

import java.util.HashMap;
import java.util.List;

/**
 * Created by qmr777 on 16-4-9.
 */
public class ListViewAdapter extends ArrayAdapter<TopStoriesModel.StoriesBean> {
    int resourceID;
    HashMap<Integer,Drawable> map = null;
    //LruCache<Integer,Bitmap> map;

    public ListViewAdapter(Context context, int resource, List<TopStoriesModel.StoriesBean> objects) {

        super(context, resource, objects);
        resourceID = resource;
        //map = new LruCache<>(20);
        map = new HashMap<>();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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

        if(map.get(position) == null){
            viewHolder.imageView.setImageBitmap(null);
            new GetStoryImageTask(viewHolder.imageView).execute(storiesBean.getImages().get(0));
            map.put(position,viewHolder.imageView.getBackground());
            //map.put(position, new Bitmap())
/*            ImageLoader.getInstance().loadImage(storiesBean.getImages().get(0),new SimpleImageLoadingListener(){
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    viewHolder.imageView.setBackground(new BitmapDrawable(loadedImage));
                    map.put(position,loadedImage);
                }
            });*/
        }
        else {
            viewHolder.imageView.setBackground(map.get(position));
        }

        //return super.getView(position, convertView, parent);
        return view;
    }

    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}
