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

    public LiveData<List<Sneakers>> getAllSneakers() {
        mAllSneakers = mRepository.getAllSneakers();
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getMaleSneakers() {
        return mRepository.getMaleSneakers();
    }

    public LiveData<List<Sneakers>> getFemaleSneakers() {
        return mRepository.getFemaleSneakers();
    }

    public LiveData<List<Sneakers>> getChildrenSneakers() {
        return mRepository.getChildrenSneakers();
    }

    public void insert(Sneakers sneakers) {
        mRepository.insert(sneakers);
    }

    public LiveData<List<Sneakers>> getSorted(String column) {
        mAllSneakers = mRepository.sort(column);
        return mAllSneakers;
    }
}