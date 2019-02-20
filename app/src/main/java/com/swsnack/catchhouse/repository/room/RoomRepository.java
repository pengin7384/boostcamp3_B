package com.swsnack.catchhouse.repository.room;

import android.net.Uri;

import com.swsnack.catchhouse.data.entity.SellRoomEntity;
import com.swsnack.catchhouse.data.model.Room;
import com.swsnack.catchhouse.repository.OnFailedListener;
import com.swsnack.catchhouse.repository.OnSuccessListener;
import com.swsnack.catchhouse.repository.room.local.FavoriteRoomDataSource;
import com.swsnack.catchhouse.repository.room.local.LocalFavoriteRoomImpl;
import com.swsnack.catchhouse.repository.room.local.LocalRecentRoomImpl;
import com.swsnack.catchhouse.repository.room.local.LocalSellRoomDataSource;
import com.swsnack.catchhouse.repository.room.local.RecentRoomDataSource;
import com.swsnack.catchhouse.repository.room.local.SellRoomImpl;
import com.swsnack.catchhouse.repository.room.remote.RoomDataImpl;
import com.swsnack.catchhouse.repository.room.remote.RoomDataSource;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RoomRepository implements RoomDataSource, FavoriteRoomDataSource, RecentRoomDataSource, LocalSellRoomDataSource {

    private static RoomRepository INSTANCE;
    private FavoriteRoomDataSource mLocalRoomDataManager;
    private RoomDataSource mRemoteRoomDataSource;
    private RecentRoomDataSource mRecentRoomDataManager;
    private LocalSellRoomDataSource mLocalSellRoomManager;

    public static RoomRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (RoomRepository.class) {
                INSTANCE = new RoomRepository();
            }
        }
        return INSTANCE;
    }

    private RoomRepository() {

        mLocalRoomDataManager = LocalFavoriteRoomImpl.getInstance();
        mRemoteRoomDataSource = RoomDataImpl.getInstance();
        mRecentRoomDataManager = LocalRecentRoomImpl.getInstance();
        mLocalSellRoomManager = SellRoomImpl.getInstance();
    }

    @Override
    public String createKey() {
        return mRemoteRoomDataSource.createKey();
    }

    @Override
    public void uploadRoomImage(@NonNull String uuid, @NonNull List<Uri> imageList, @NonNull OnSuccessListener<List<String>> onSuccessListener, @NonNull OnFailedListener onFailedListener) {
        mRemoteRoomDataSource.uploadRoomImage(uuid, imageList, onSuccessListener, onFailedListener);
    }

    @Override
    public void setRoom(@NonNull String key, @NonNull Room room, @Nullable OnSuccessListener<Void> onSuccessListener, @Nullable OnFailedListener onFailedListener) {
        mRemoteRoomDataSource.setRoom(key, room, success -> {
                    setSellRoom(new SellRoomEntity(key,
                            room.getPrice(),
                            room.getFrom(),
                            room.getTo(),
                            room.getTitle(),
                            room.getContent(),
                            room.getImages(),
                            room.getUuid(),
                            room.getAddress(),
                            room.getAddressName(),
                            room.getSize(),
                            room.isOptionStandard(),
                            room.isOptionGender(),
                            room.isOptionPet(),
                            room.isOptionSmoking(),
                            room.getLatitude(),
                            room.getLongitude()));
                    onSuccessListener.onSuccess(success);
                }
                , onFailedListener);
    }

    @Override
    public void getRoom(@NonNull String key, @Nullable OnSuccessListener<Room> onSuccessListener, @Nullable OnFailedListener onFailedListener) {
        mRemoteRoomDataSource.getRoom(key, onSuccessListener, onFailedListener);
    }

    @Override
    public void setFavoriteRoom(Room room) {
        mLocalRoomDataManager.setFavoriteRoom(room);
    }

    @Override
    public void deleteFavoriteRoom(Room room) {
        mLocalRoomDataManager.deleteFavoriteRoom(room);
    }

    @Override
    public List<Room> getFavoriteRoomList() {
        return mLocalRoomDataManager.getFavoriteRoomList();
    }

    @Override
    public void deleteFavoriteRoom() {
        mLocalRoomDataManager.deleteFavoriteRoom();
    }

    @Override
    public Room getFavoriteRoom(String key) {
        return mLocalRoomDataManager.getFavoriteRoom(key);
    }

    @Override
    public void updateRoom(Room room) {
        mLocalRoomDataManager.updateRoom(room);
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
