package com.example.potap.shopwindow.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.potap.shopwindow.activities.mainTabs.CategoriesTab;
import com.example.potap.shopwindow.activities.mainTabs.NewsTab;
import com.example.potap.shopwindow.activities.mainTabs.PersonTab;

public class MainPageAdapter extends FragmentPagerAdapter {

    private NewsTab newsTab = new NewsTab();
    private CategoriesTab categoriesTab = new CategoriesTab();
    private PersonTab personTab = new PersonTab();

    public MainPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return newsTab;
            case 1:
                return categoriesTab;
            case 2:
                return personTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
