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

    public static NewsRepository getInstance(Application application) {
        if (instance == null)
            instance = new NewsRepository(application);
        return instance;
    }

    private NewsRepository(Application application) {
        DataManager db = DataManager.getDatabase(application);
        mNewsDAO = db.newsDAO();
        mAllNews = mNewsDAO.getAll();
    }

    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<News>> getAllNews() {
        return mAllNews;
    }

    public void insert(News news) {
        new NewsRepository.InsertAsyncTask(mNewsDAO).execute(news);
    }

//    public LiveData<List<Categories>> getSorted(String column) {
//        mAllCategories = column.equals("name") ? mCategoriesDao.getSortedByName() : mCategoriesDao.getSortedByPrice();
//        return mAllCategories;
//    }

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
