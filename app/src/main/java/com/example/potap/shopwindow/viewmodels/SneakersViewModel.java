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
        mAllSneakers = mRepository.getMaleSneakers();
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getFemaleSneakers() {
        mAllSneakers = mRepository.getFemaleSneakers();
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getChildrenSneakers() {
        mAllSneakers = mRepository.getChildrenSneakers();
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getSportSneakers(int sport){
        mAllSneakers = mRepository.getSportSneakers(sport);
        return mAllSneakers;
    }

    public void insert(Sneakers sneakers) {
        mRepository.insert(sneakers);
    }

    public LiveData<List<Sneakers>> getSorted(String column) {
        mAllSneakers = mRepository.sort(column);
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getSorted(int gender,boolean child,String column){
        mAllSneakers = mRepository.getSorted(gender,child,column);
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getSportSorted(int sport,String column){
        mAllSneakers = mRepository.getSportSorted(sport,column);
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> search(String searchString){
        mAllSneakers = mRepository.search(searchString);
        return mAllSneakers;
    }

}