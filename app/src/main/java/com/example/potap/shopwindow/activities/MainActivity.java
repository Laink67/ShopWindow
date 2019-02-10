package com.example.potap.shopwindow.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.adapter.MainPageAdapter;

public class MainActivity extends AppCompatActivity {

    private MainPageAdapter mainPageAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager_main);
        tabLayout = findViewById(R.id.tab_layout);

        setUpToolbar();
        mainPageAdapter = new MainPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPageAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            findViewById(R.id.tab_layout).setBackground(getDrawable(R.drawable.shop_background_shape));
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bascet:
                Intent intent = new Intent(this, BasketActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_find:
                Intent searchIntent = new Intent(this, SneakersActivity.class);
                searchIntent.putExtra("title","Поиск");
                startActivity(searchIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpToolbar() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);

        if (toolbar != null)
            setSupportActionBar(toolbar);

/*
        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(this, findViewById(R.id.product_grid)));
*/
    }
}

