package com.swsnack.catchhouse.data.userdata;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.swsnack.catchhouse.data.userdata.pojo.User;
import com.swsnack.catchhouse.data.userdata.remote.UserRemoteData;
import com.facebook.AccessToken;

import io.reactivex.Completable;
import io.reactivex.Single;

public class UserRepository {

    private UserRemoteData mUserRemote;

    public static UserRepository getInstance() {
        return RepositoryHelper.INSTANCE;
    }

    private static class RepositoryHelper {
        private static final UserRepository INSTANCE = new UserRepository();
    }

    private UserRepository() {
        mUserRemote = UserRemoteData.getInstance();
    }

    @NonNull
    public Single<User> getUserFromRemote(String uuid) {
        return mUserRemote.getUser(uuid);
    }

    @NonNull
    public Single<User> getDetailInfoFromRemote(AccessToken token) {
        return mUserRemote.getNameAndGenderFromFB(token);
    }

    @NonNull
    public Completable setUserToRemote(String uuid, User user) {
        return mUserRemote.setUser(uuid, user);
    }

    @NonNull
    public Single<Uri> saveProfileAndGetUrl(String uuid, byte[] profileByreArray) {
        return mUserRemote.saveProfileAndGetUrl(uuid, profileByreArray);
    }
}