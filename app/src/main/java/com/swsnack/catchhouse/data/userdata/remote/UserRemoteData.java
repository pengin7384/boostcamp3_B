package com.swsnack.catchhouse.data.userdata.remote;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.swsnack.catchhouse.constants.Constants;
import com.swsnack.catchhouse.data.userdata.UserDataManager;
import com.swsnack.catchhouse.data.userdata.pojo.User;

import io.reactivex.Completable;
import io.reactivex.Single;

public class UserRemoteData implements UserDataManager {

    private DatabaseReference db;
    private StorageReference fs;

    private static class UserRemoteDataHelper {
        private static final UserRemoteData INSTANCE = new UserRemoteData();
    }

    public static UserRemoteData getInstance() {
        return UserRemoteDataHelper.INSTANCE;

    }

    private UserRemoteData() {
        db = FirebaseDatabase.getInstance().getReference().child(Constants.FirebaseKey.DB_USER);
        fs = FirebaseStorage.getInstance().getReference().child(Constants.FirebaseKey.STORAGE_PROFILE);
    }

    @NonNull
    @Override
    public Single<User> getUser(String uuid) {
        return Single.defer(() -> Single.create(subscriber -> db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                subscriber.onSuccess(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                subscriber.onError(databaseError.toException());
            }
        })));
    }

    @NonNull
    @Override
    public Completable setUser(String uuid, User user) {
        return Completable.defer(() -> Completable.create(subscriber -> {
            db.child(uuid).setValue(user);
            subscriber.onComplete();
        }));
    }

    @NonNull
    @Override
    public Single<String> setProfile(String uuid, byte[] profile) {
        return Single.defer(() ->
                Single.create(subscriber -> {
                    StorageReference ref = fs.child(uuid);
                    UploadTask uploadTask = ref.putBytes(profile);
                    uploadTask.addOnSuccessListener(snapshot -> uploadTask.continueWithTask(task -> {
                        if (!task.isSuccessful()) {
                            subscriber.onError(task.getException());
                        }
                        return ref.getDownloadUrl();
                    }).addOnSuccessListener(uri -> subscriber.onSuccess(uri.toString()))
                            .addOnFailureListener(subscriber::onError));
                })
        );
    }
}
