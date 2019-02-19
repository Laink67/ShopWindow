package com.example.potap.shopwindow.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.potap.shopwindow.dbObjects.Sizes;
import com.example.potap.shopwindow.interfaces.SizesDAO;

import java.util.List;

public class SizesRepository {

    private static SizesRepository instance;
    private SizesDAO mSizesDao;
    private LiveData<List<Sizes>> mAllSizes;

    public static SizesRepository getInstance(Application application) {
        if (instance == null)
            instance = new SizesRepository(application);
        return instance;
    }

    private SizesRepository(Application application) {
        DataManager db = DataManager.getDatabase(application);
        mSizesDao = db.sizesDAO();
        mAllSizes = mSizesDao.getAll();
    }

    public LiveData<List<Sizes>> getAll() {
        mAllSizes = mSizesDao.getAll();
        return mAllSizes;
    }

    public LiveData<List<Sizes>> getBySneakersId(int sneakersId) {
        mAllSizes = mSizesDao.getSizesBySneakersId(sneakersId);
        return mAllSizes;
    }

    public void insert(Sizes sizes) {
        new SizesRepository.InsertAsyncTask(mSizesDao).execute(sizes);
    }

    private static class InsertAsyncTask extends AsyncTask<Sizes, Void, Void> {

        private SizesDAO mAsyncTaskDao;

        InsertAsyncTask(SizesDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Sizes... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


}
