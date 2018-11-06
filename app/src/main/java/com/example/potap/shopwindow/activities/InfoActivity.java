package com.example.potap.shopwindow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.adapter.ViewPagerAdapter;
import com.example.potap.shopwindow.dbObjects.Sneakers;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGES = "imagesLink";

    private ArrayList<String> imagesLink = new ArrayList<String>();

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private ImageView image;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();

        imagesLink.addAll(intent.getStringArrayListExtra(EXTRA_IMAGES));

        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(InfoActivity.this, imagesLink);
        viewPager.setAdapter(adapter);
        textView = findViewById(R.id.textViewInfo);
        textView.setText(intent.getStringExtra("description"));
    }
}
