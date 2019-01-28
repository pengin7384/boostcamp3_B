package com.boostcampa2.catchhouse.viewmodel.userviewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.boostcampa2.catchhouse.data.userdata.UserRepository;
import com.boostcampa2.catchhouse.viewmodel.ViewModelListener;

public class UserViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application mApplication;
    private UserRepository mRepository;
    private ViewModelListener mListener;

    public UserViewModelFactory(@NonNull Application application, UserRepository repository, ViewModelListener listener) {
        this.mApplication = application;
        this.mRepository = repository;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(mApplication, mRepository, mListener);
        }
        throw new Fragment.InstantiationException("not viewModel class", null);
    }
}