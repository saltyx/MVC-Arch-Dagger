package me.shiyan.mvc_arch_dagger.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;

import java.util.ArrayList;

import javax.inject.Named;

import io.realm.RealmObject;
import me.shiyan.mvc_arch_dagger.bean.BaseNews;

/**
 * Created by shiyan on 2016/8/16.
 */
public interface NewsDataSource {

    interface LoadDataCallback <T>{

        void onLoaded(ArrayList<T> data);

        void onFailure(@Nullable String error,@Nullable Exception e);
    }

    interface GetDataCallback<T>{

        void onLoaded(T data);

        void onFailure(@Nullable String error,@Nullable Exception e);
    }

    interface SaveDataCallback {

        void onSuccess();

        void onFailure(@Nullable String error,@Nullable Exception e);
    }

    void saveNews(@Nullable JSONArray news, SaveDataCallback callback );

    void getNewsById(@NonNull String id, GetDataCallback<BaseNews> callback);

    void loadAllNews(@NonNull LoadDataCallback<BaseNews> callback);

}
