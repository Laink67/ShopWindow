package com.example.potap.shopwindow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.potap.shopwindow.dbObjects.News;
import com.example.potap.shopwindow.repositories.NewsRepository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository mRepository;

    public NewsViewModel(Application application) {
        super(application);
        mRepository = NewsRepository.getInstance(application);
    }

    public LiveData<List<News>> getAllNews() {
        return mRepository.getAllNews();
    }

    public void insert(News News) {
        mRepository.insert(News);
    }
}
