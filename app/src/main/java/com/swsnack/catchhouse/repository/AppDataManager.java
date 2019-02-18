package com.swsnack.catchhouse.repository;

import android.graphics.Bitmap;
import android.net.Uri;

import com.skt.Tmap.TMapPOIItem;
import com.swsnack.catchhouse.data.entity.RoomEntity;
import com.swsnack.catchhouse.data.model.Address;
import com.swsnack.catchhouse.data.model.Filter;
import com.swsnack.catchhouse.data.model.Message;
import com.swsnack.catchhouse.data.model.Room;
import com.swsnack.catchhouse.data.model.User;
import com.swsnack.catchhouse.repository.chatting.ChattingManager;
import com.swsnack.catchhouse.repository.location.LocationDataManager;
import com.swsnack.catchhouse.repository.room.RoomRepository;
import com.swsnack.catchhouse.repository.room.local.FavoriteRoomManager;
import com.swsnack.catchhouse.repository.room.local.RecentRoomManager;
import com.swsnack.catchhouse.repository.room.remote.RoomDataManager;
import com.swsnack.catchhouse.repository.searching.SearchingDataManager;
import com.swsnack.catchhouse.repository.user.UserDataManager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Single;

public class AppDataManager implements DataManager {

    private UserDataManager mUserDataManager;
    private ChattingManager mRemoteChattingManager;
    private RoomDataManager mRoomDataManager;
    private FavoriteRoomManager mFavoriteRoomManager;
    private RecentRoomManager mRecentRoomDataManager;
    private LocationDataManager mLocationDataManager;
    private SearchingDataManager mSearchingDataManager;

    private AppDataManager(UserDataManager userDataManager,
                           ChattingManager remoteChattingManager,
                           RoomRepository roomRepository,
                           LocationDataManager locationDataManager,
                           SearchingDataManager searchingDataManager) {

        mUserDataManager = userDataManager;
        mRemoteChattingManager = remoteChattingManager;
        mRoomDataManager = roomRepository;
        mFavoriteRoomManager = roomRepository;
        mRecentRoomDataManager = roomRepository;
        mLocationDataManager = locationDataManager;
        mSearchingDataManager = searchingDataManager;
    }

    private static AppDataManager INSTANCE;

    public static synchronized AppDataManager getInstance(@NonNull UserDataManager userDataManager,
                                                          @NonNull ChattingManager remoteChattingManager,
                                                          @NonNull RoomRepository roomRepository,
                                                          @NonNull LocationDataManager locationDataManager,
                                                          @NonNull SearchingDataManager searchingDataManager) {
        if (INSTANCE == null) {
            INSTANCE = new AppDataManager(userDataManager,
                    remoteChattingManager,
                    roomRepository,
                    locationDataManager,
                    searchingDataManager);
        }
        return INSTANCE;
    }

    @Override
    public void updateProfile(@NonNull String uuid,
                              @NonNull Uri uri,
                              @NonNull User user,
                              @NonNull OnSuccessListener<Void> onSuccessListener,
                              @NonNull OnFailedListener onFailedListener) {

        mUserDataManager.updateProfile(uuid, uri, user, onSuccessListener, onFailedListener);
    }

    @Override
    public void deleteUser(@NonNull String uuid, @NonNull OnSuccessListener<Void> onSuccessListener, @NonNull OnFailedListener onFailedListener) {
        mUserDataManager.deleteUser(uuid, onSuccessListener, onFailedListener);
    }

    @Override
    public void getUserAndListeningForChanging(@NonNull String uuid,
                                               @NonNull OnSuccessListener<User> onSuccessListener,
                                               @NonNull OnFailedListener onFailedListener) {
        mUserDataManager.getUserAndListeningForChanging(uuid, onSuccessListener, onFailedListener);
    }

    @Override
    public void getUserFromSingleSnapShot(@NonNull String uuid,
                                          @NonNull OnSuccessListener<User> onSuccessListener,
                                          @NonNull OnFailedListener onFailedListener) {
        mUserDataManager.getUserFromSingleSnapShot(uuid, onSuccessListener, onFailedListener);
    }

    @Override
    public void setUser(@NonNull String uuid,
                        @NonNull User user,
                        @NonNull OnSuccessListener<Void> onSuccessListener,
                        @NonNull OnFailedListener onFailedListener) {

        mUserDataManager.setUser(uuid, user, onSuccessListener, onFailedListener);
    }

    @Override
    public void setUser(@NonNull String uuid,
                        @NonNull User user,
                        @NonNull Uri uri,
                        @NonNull OnSuccessListener<Void> onSuccessListener,
                        @NonNull OnFailedListener onFailedListener) {

        mUserDataManager.setUser(uuid, user, uri, onSuccessListener, onFailedListener);
    }

    @Override
    public void setUserNotAlreadySigned(@NonNull String uuid,
                                        @NonNull User user,
                                        @NonNull OnSuccessListener<Void> onSuccessListener,
                                        @NonNull OnFailedListener onFailedListener) {

        mUserDataManager.setUserNotAlreadySigned(uuid, user, onSuccessListener, onFailedListener);
    }

    @Override
    public void updateUser(@NonNull String uuid,
                           @NonNull User user,
                           @NonNull OnSuccessListener<Void> onSuccessListener,
                           @NonNull OnFailedListener onFailedListener) {

        mUserDataManager.updateUser(uuid, user, onSuccessListener, onFailedListener);
    }

    @Override
    public void getProfile(@NonNull Uri uri,
                           @NonNull OnSuccessListener<Bitmap> onSuccessListener,
                           @NonNull OnFailedListener onFailedListener) {

        mUserDataManager.getProfile(uri, onSuccessListener, onFailedListener);
    }

    @Override
    public void getChattingRoom(@NonNull String destinationUuid,
                                @NonNull OnSuccessListener onSuccessListener,
                                @NonNull OnFailedListener onFailedListener) {

        mRemoteChattingManager.getChattingRoom(destinationUuid, onSuccessListener, onFailedListener);
    }

    @Override
    public void listeningChattingListChanged(@NonNull OnSuccessListener onSuccessListener,
                                             @NonNull OnFailedListener onFailedListener) {

        mRemoteChattingManager.listeningChattingListChanged(onSuccessListener, onFailedListener);
    }

    @Override
    public void cancelObservingChattingList() {
        mRemoteChattingManager.cancelObservingChattingList();
    }

    @Override
    public void listeningChatMessageChanged(@NonNull String chatRoomId,
                                            @NonNull OnSuccessListener<List<Message>> onSuccessListener,
                                            OnFailedListener onFailedListener) {

        mRemoteChattingManager.listeningChatMessageChanged(chatRoomId, onSuccessListener, onFailedListener);

    }

    @Override
    public void cancelMessageModelObserving() {
        mRemoteChattingManager.cancelMessageModelObserving();
    }

    @Override
    public void setChattingRoom(@NonNull String destinationUuid,
                                @NonNull OnSuccessListener<String> onSuccessListener,
                                @NonNull OnFailedListener onFailedListener) {

        mRemoteChattingManager.setChattingRoom(destinationUuid, onSuccessListener, onFailedListener);
    }

    @Override
    public void setChatMessage(int messagesLength,
                               @Nullable String roomUid,
                               @NonNull String destinationUid,
                               @NonNull String content,
                               @NonNull OnSuccessListener<String> onSuccessListener,
                               @NonNull OnFailedListener onFailedListener) {

        mRemoteChattingManager.setChatMessage(messagesLength, roomUid, destinationUid, content, onSuccessListener, onFailedListener);

    }

    @Override
    public void createKey(@NonNull OnSuccessListener<String> onSuccessListener,
                          @NonNull OnFailedListener onFailedListener) {

        mRoomDataManager.createKey(onSuccessListener, onFailedListener);
    }

    @Override
    public void uploadRoomImage(@NonNull String uuid, @NonNull List<Uri> imageList,
                                @NonNull OnSuccessListener<List<String>> onSuccessListener,
                                @NonNull OnFailedListener onFailedListener) {

        mRoomDataManager.uploadRoomImage(uuid, imageList, onSuccessListener, onFailedListener);
    }

    @Override
    public void setRoom(@NonNull String key, @NonNull Room room,
                        @NonNull OnSuccessListener<Void> onSuccessListener,
                        @NonNull OnFailedListener onFailedListener) {

        mRoomDataManager.setRoom(key, room, onSuccessListener, onFailedListener);
    }


    @Override
    public void getRoom(@NonNull String key,
                        @NonNull OnSuccessListener<Room> onSuccessListener,
                        @NonNull OnFailedListener onFailedListener) {
        mRoomDataManager.getRoom(key, onSuccessListener, onFailedListener);
    }

    @Override
    public void uploadLocationData(@NonNull String uuid, @NonNull Address address,
                                   @NonNull OnSuccessListener<String> onSuccessListener,
                                   @NonNull OnFailedListener onFailedListener) {

        mLocationDataManager.uploadLocationData(uuid, address, onSuccessListener, onFailedListener);
    }

    @NonNull
    public Single<List<TMapPOIItem>> getPOIList(@NonNull String keyword) {
        return mSearchingDataManager.getPOIList(keyword);
    }

    @NonNull
    public Single<List<Room>> getNearRoomList(@NonNull Filter filter) {
        return mSearchingDataManager.getNearRoomList(filter);
    }

    @Override
    public void setFavoriteRoom(RoomEntity roomEntity) {
        mFavoriteRoomManager.setFavoriteRoom(roomEntity);
    }

    @Override
    public void deleteFavoriteRoom(RoomEntity roomEntity) {
        mFavoriteRoomManager.deleteFavoriteRoom(roomEntity);

    }

    @Override
    public void deleteFavoriteRoom() {
        mFavoriteRoomManager.deleteFavoriteRoom();
    }

    @Override
    public List<RoomEntity> getFavoriteRoomList() {
        return mFavoriteRoomManager.getFavoriteRoomList();
    }

    @Override
    public RoomEntity getFavoriteRoom(String key) {
        return mFavoriteRoomManager.getFavoriteRoom(key);
    }

    @Override
    public void updateRoom(RoomEntity roomEntity) {
        mFavoriteRoomManager.updateRoom(roomEntity);
    }

    @Override
    public void setRecentRoom(Room room) {
        mRecentRoomDataManager.setRecentRoom(room);
    }

    @Override
    public List<Room> getRecentRoom() {
        return mRecentRoomDataManager.getRecentRoom();
    }

    @Override
    public void deleteRecentRoomList() {
        mRecentRoomDataManager.deleteRecentRoomList();
    }
}