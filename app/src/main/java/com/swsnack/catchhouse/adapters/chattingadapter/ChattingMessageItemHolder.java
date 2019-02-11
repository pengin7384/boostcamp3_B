package com.swsnack.catchhouse.adapters.chattingadapter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.swsnack.catchhouse.databinding.ItemChattingMessageBinding;

public class ChattingMessageItemHolder extends RecyclerView.ViewHolder implements LifecycleOwner {

    private ItemChattingMessageBinding mBinding;
    private LifecycleRegistry mLifeCycle = new LifecycleRegistry(this);

    public ChattingMessageItemHolder(@NonNull ItemChattingMessageBinding binding) {
        super(binding.getRoot());
        this.mBinding = binding;
        this.mLifeCycle.markState(Lifecycle.State.INITIALIZED);
    }

    void onAttachHolder() {
        mLifeCycle.markState(Lifecycle.State.STARTED);
    }

    void onDetachHolder() {
        mLifeCycle.markState(Lifecycle.State.DESTROYED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return this.mLifeCycle;
    }

    @NonNull
    public ItemChattingMessageBinding getBinding() {
        return this.mBinding;
    }
}
