package com.example.potap.shopwindow.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.potap.shopwindow.R;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    private Activity activity;
    ArrayList<String> imagesLink;
    private LayoutInflater mInflater;

    public ViewPagerAdapter(Activity activity, ArrayList<String> imagesLink) {
        this.activity = activity;
        this.imagesLink = imagesLink;
    }

    @Override
    public int getCount() {
        return imagesLink.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mInflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = mInflater.inflate(R.layout.viewpager_item,container,false);
        ImageView imageView;
        imageView = itemView.findViewById(R.id.imageInfo);

        DisplayMetrics dis = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dis);
        imageView.setMinimumHeight(dis.heightPixels);
        imageView.setMinimumWidth(dis.widthPixels);

        try{
            Glide.with(activity.getApplicationContext())
                    .load(imagesLink.get(position))
                    .into(imageView);
        }
        catch (Exception ex){

        }
        container.addView(itemView);
        return itemView;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
