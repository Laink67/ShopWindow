package com.example.potap.shopwindow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.adapter.ViewPagerAdapter;
import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    public static final String EXTRA_OBJECTS = "SneakersObjects";

    private ArrayList<String> imagesLink = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.info_toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Sneakers sneakers = (Sneakers) bundle.getSerializable(EXTRA_OBJECTS);

        imagesLink = sneakers.getImagesLinks();

//        imagesLink.addAll(arr);

        ViewPager viewPager = findViewById(R.id.view_pager);
        DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);

        ViewPagerAdapter adapter = new ViewPagerAdapter(InfoActivity.this, imagesLink);
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);

        TextView textView = findViewById(R.id.text_view_info);
        textView.setText(sneakers.getDescription());
    }
}
