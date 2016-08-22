package me.shiyan.mvc_arch_dagger.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.shiyan.mvc_arch_dagger.bean.BaseNews;
import me.shiyan.mvc_arch_dagger.data.source.local.DataSource;

/**
 * Created by shiyan on 2016/8/16.
 */

@Singleton
public class DataRepo implements NewsDataSource {

    private final DataSource mDataSource;


    @Inject
    DataRepo(DataSource newsDataSource){
        mDataSource = newsDataSource;
    }

    @Override
    public void saveNews(@Nullable JSONArray news, SaveDataCallback callback) {
        mDataSource.saveNews(news,callback);
    }

    @Override
    public void getNewsById(@NonNull String id, GetDataCallback<BaseNews> callback) {
        mDataSource.getNewsById(id,callback);
    }

    @Override
    public void loadAllNews(@NonNull LoadDataCallback<BaseNews> callback) {
        mDataSource.loadAllNews(callback);
    }
}
