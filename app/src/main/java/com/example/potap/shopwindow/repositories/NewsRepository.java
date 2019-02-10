package com.example.potap.shopwindow.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.potap.shopwindow.dbObjects.News;
import com.example.potap.shopwindow.interfaces.NewsDAO;

import java.util.List;

public class NewsRepository {
    private static NewsRepository instance;
    private NewsDAO mNewsDAO;
    private LiveData<List<News>> mAllNews;
    private LiveData<News> news;

    public static NewsRepository getInstance(Application application) {
        if (instance == null)
            instance = new NewsRepository(application);
        return instance;
    }

    private NewsRepository(Application application) {
        DataManager db = DataManager.getDatabase(application);
        mNewsDAO = db.newsDAO();
        mAllNews = mNewsDAO.getAll();
        news = mNewsDAO.getById(1);
    }

    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<News>> getAllNews() {
        return mAllNews;
    }

    public LiveData<News> getById(int id) {
        news = mNewsDAO.getById(id);
        return news;
    }

    public void insert(News news) {
        new NewsRepository.InsertAsyncTask(mNewsDAO).execute(news);
    }

    private static class InsertAsyncTask extends AsyncTask<News, Void, Void> {

        private NewsDAO mAsyncTaskDao;

        InsertAsyncTask(NewsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final News... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
