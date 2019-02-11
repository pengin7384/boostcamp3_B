package com.swsnack.catchhouse.viewmodel.postviewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v4.app.Fragment;

import com.swsnack.catchhouse.data.DataManager;
import com.swsnack.catchhouse.view.activities.PostListener;

import io.reactivex.annotations.NonNull;

public class PostViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private DataManager mDataManager;
    private PostListener mListener;

    public PostViewModelFactory(DataManager dataManager, PostListener listener) {
        this.mDataManager = dataManager;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PostViewModel.class)) {
            return (T) new PostViewModel(mDataManager, mListener);
        }
        throw new Fragment.InstantiationException("not viewModel class", null);
    }
}