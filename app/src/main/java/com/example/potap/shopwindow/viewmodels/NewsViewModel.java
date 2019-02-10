package com.example.potap.shopwindow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.potap.shopwindow.dbObjects.News;
import com.example.potap.shopwindow.repositories.NewsRepository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository mRepository;
    private LiveData<List<News>> mAllNews;
    private LiveData<News> news;

    public NewsViewModel(Application application) {
        super(application);
        mRepository = NewsRepository.getInstance(application);
        mAllNews = mRepository.getAllNews();
        news = mRepository.getById(1);
    }

    public LiveData<List<News>> getAllNews() {
        mAllNews = mRepository.getAllNews();
        return mAllNews;
    }

    public LiveData<News> getById(int id) {
        news = mRepository.getById(id);
        return news;
    }

    public void insert(News News) {
        mRepository.insert(News);
    }
}
