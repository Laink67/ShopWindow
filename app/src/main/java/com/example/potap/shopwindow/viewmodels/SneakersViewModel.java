package com.example.potap.shopwindow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.example.potap.shopwindow.repositories.SneakersRepository;

import java.util.List;

public class SneakersViewModel extends AndroidViewModel {

    private SneakersRepository mRepository;
    private LiveData<List<Sneakers>> mAllSneakers;

    public SneakersViewModel(Application application) {
        super(application);
        mRepository = SneakersRepository.getInstance(application);
        mAllSneakers = mRepository.getAllSneakers();
    }

    public LiveData<List<Sneakers>> getSortedWithCategoriesId(int categoriesId, String column) {
        mAllSneakers = mRepository.getSortedWithCategoriesId(categoriesId,column);
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getSortedWithNewsId(int newsId, String column) {
        mAllSneakers = mRepository.getSortedWithNewsId(newsId,column);
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getByCategoriesId(int id) {
        mAllSneakers = mRepository.getByCategoriesId(id);
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getByNewsId(int id) {
        mAllSneakers = mRepository.getByNewsId(id);
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getAllSneakers() {
        mAllSneakers = mRepository.getAllSneakers();
        return mAllSneakers;
    }

    public void insert(Sneakers sneakers) {
        mRepository.insert(sneakers);
    }

    public LiveData<List<Sneakers>> getSorted(String column) {
        mAllSneakers = mRepository.sort(column);
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getSorted(int gender, boolean child, String column) {
        mAllSneakers = mRepository.getSorted(gender, child, column);
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> search(String searchString) {
        mAllSneakers = mRepository.search(searchString);
        return mAllSneakers;
    }

}