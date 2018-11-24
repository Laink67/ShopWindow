package com.example.potap.shopwindow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.adapter.ViewPagerAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGES = "imagesLink";
    public static final String EXTRA_DESCRIPTION = "description";

    private ArrayList<String> imagesLink = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        }
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();

        imagesLink.addAll(intent.getStringArrayListExtra(EXTRA_IMAGES));

        ViewPager viewPager = findViewById(R.id.view_pager);
        DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);

        ViewPagerAdapter adapter = new ViewPagerAdapter(InfoActivity.this, imagesLink);
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);

        TextView textView = findViewById(R.id.text_view_info);
        textView.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
    }
}
