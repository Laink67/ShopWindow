package com.example.potap.shopwindow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.potap.shopwindow.dbObjects.Categories;
import com.example.potap.shopwindow.repositories.CategoriesRepository;

import java.util.List;

public class CategoriesViewModel extends AndroidViewModel {

    private CategoriesRepository mRepository;
    private LiveData<List<Categories>> mAllCategories;

    public CategoriesViewModel(Application application) {
        super(application);
        mRepository = CategoriesRepository.getInstance(application);
        mAllCategories = mRepository.getAllCategories();
    }

    public LiveData<List<Categories>> getAllCategories() {
//        mAllCategories = mRepository.getAllCategories();
        return mAllCategories;
    }

    public void insert(Categories Categories) {
        mRepository.insert(Categories);
    }
}
