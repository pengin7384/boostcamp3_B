package com.swsnack.catchhouse.data;

import android.graphics.Bitmap;
import android.net.Uri;

import com.skt.Tmap.TMapPOIItem;
import com.swsnack.catchhouse.data.entity.SellRoomEntity;
import com.swsnack.catchhouse.data.model.Address;
import com.swsnack.catchhouse.data.model.Chatting;
import com.swsnack.catchhouse.data.model.Filter;
import com.swsnack.catchhouse.data.model.Message;
import com.swsnack.catchhouse.data.model.Room;
import com.swsnack.catchhouse.data.model.User;
import com.swsnack.catchhouse.repository.OnFailedListener;
import com.swsnack.catchhouse.repository.OnSuccessListener;
import com.swsnack.catchhouse.repository.chatting.ChattingDataSource;
import com.swsnack.catchhouse.repository.chatting.remote.RemoteChattingImpl;
import com.swsnack.catchhouse.repository.location.LocationDataSource;
import com.swsnack.catchhouse.repository.location.remote.RemoteLocationImpl;
import com.swsnack.catchhouse.repository.room.local.LocalFavoriteRoomImpl;
import com.swsnack.catchhouse.repository.room.local.LocalRecentRoomImpl;
import com.swsnack.catchhouse.repository.room.local.LocalSellRoomDataSource;
import com.swsnack.catchhouse.repository.room.local.RecentRoomDataSource;
import com.swsnack.catchhouse.repository.room.local.SellRoomImpl;
import com.swsnack.catchhouse.repository.room.remote.RoomDataImpl;
import com.swsnack.catchhouse.repository.room.remote.RoomDataSource;
import com.swsnack.catchhouse.repository.searching.SearchingDataSource;
import com.swsnack.catchhouse.repository.searching.remote.SearchingDataImpl;
import com.swsnack.catchhouse.repository.user.UserDataSource;
import com.swsnack.catchhouse.repository.user.remote.UserDataImpl;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Single;

public class AppDataManager implements DataManager {

    private UserDataSource mUserDataManager;
    private ChattingDataSource mRemoteChattingManager;
    private RoomDataSource mRoomDataManager;
    private LocalFavoriteRoomImpl mLocalRoomDataSource;
    private RecentRoomDataSource mRecentRoomDataManager;
    private LocationDataSource mLocationDataManager;
    private SearchingDataSource mSearchingDataManager;
    private LocalSellRoomDataSource mLocalSellRoomManager;

    private AppDataManager() {

        mUserDataManager = UserDataImpl.getInstance();
        mRemoteChattingManager = RemoteChattingImpl.getInstance();
        mRoomDataManager = RoomDataImpl.getInstance();
        mLocalRoomDataSource = LocalFavoriteRoomImpl.getInstance();
        mRecentRoomDataManager = LocalRecentRoomImpl.getInstance();
        mLocationDataManager = RemoteLocationImpl.getInstance();
        mSearchingDataManager = SearchingDataImpl.getInstance();
        mLocalSellRoomManager = SellRoomImpl.getInstance();
    }

    private static AppDataManager INSTANCE;

    public static synchronized AppDataManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppDataManager();
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
                                @NonNull OnSuccessListener<String> onSuccessListener,
                                @NonNull OnFailedListener onFailedListener) {

        mRemoteChattingManager.getChattingRoom(destinationUuid, onSuccessListener, onFailedListener);
    }

    @Override
    public void listeningChattingListChanged(@NonNull OnSuccessListener<List<Chatting>> onSuccessListener,
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
                                            @NonNull OnFailedListener onFailedListener) {

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
    public String createKey() {
        return mRoomDataManager.createKey();
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
    public void setFavoriteRoom(Room room) {
        mLocalRoomDataSource.setFavoriteRoom(room);
    }

    @Override
    public void deleteFavoriteRoom(Room room) {
        mLocalRoomDataSource.deleteFavoriteRoom(room);

    }

    @Override
    public void deleteFavoriteRoom() {
        mLocalRoomDataSource.deleteFavoriteRoom();
    }

    @Override
    public List<Room> getFavoriteRoomList() {
        return mLocalRoomDataSource.getFavoriteRoomList();
    }

    @Override
    public Room getFavoriteRoom(String key) {
        return mLocalRoomDataSource.getFavoriteRoom(key);
    }

    @Override
    public void updateRoom(Room room) {
        mLocalRoomDataSource.updateRoom(room);
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

    @Override
    public void setSellRoom(SellRoomEntity sellRoomEntity) {
        mLocalSellRoomManager.setSellRoom(sellRoomEntity);
    }

    @Override
    public void deleteSellRoom(SellRoomEntity sellRoomEntity) {
        mLocalSellRoomManager.deleteSellRoom(sellRoomEntity);
    }

    @Override
    public List<SellRoomEntity> getSellRoomList() {
        return mLocalSellRoomManager.getSellRoomList();
    }

    @Override
    public void deleteSellRoom() {
        mLocalSellRoomManager.deleteSellRoom();
    }

    @Override
    public SellRoomEntity getSellRoom(String key) {
        return mLocalSellRoomManager.getSellRoom(key);
    }

    @Override
    public void updateRoom(SellRoomEntity sellRoomEntity) {
        mLocalSellRoomManager.updateRoom(sellRoomEntity);
    }
}