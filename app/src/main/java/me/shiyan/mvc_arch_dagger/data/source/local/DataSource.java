package me.shiyan.mvc_arch_dagger.data.source.local;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import me.shiyan.mvc_arch_dagger.bean.BaseNews;
import me.shiyan.mvc_arch_dagger.data.model.News;
import me.shiyan.mvc_arch_dagger.data.source.NewsDataSource;

/**
 * Created by shiyan on 2016/8/16.
 */

@Singleton
public class DataSource implements NewsDataSource {

    private RealmHelper mRealmHelper;

    public DataSource(Application context) {
        this.mRealmHelper = new RealmHelper(context);
    }

    @Override
    public void saveNews(@Nullable JSONArray news, SaveDataCallback callback) {
        Realm realm = mRealmHelper.getRealm();

        realm.beginTransaction();
        try {
            realm.createOrUpdateAllFromJson(News.class,news);
            realm.commitTransaction();
            callback.onSuccess();
        }catch (Exception e){
            realm.cancelTransaction();
            callback.onFailure(null,e);
        }
        realm.close();
    }

    @Override
    public void getNewsById(@NonNull String id, GetDataCallback<BaseNews> callback) {
        Realm realm = mRealmHelper.getRealm();
        News news = realm.where(News.class)
                .equalTo("id",id)
                .findFirst();
        if (news != null && news.isLoaded()){
            callback.onLoaded(news.toBaseNews());
        }else {
            callback.onFailure("not found",null);
        }
    }

    @Override
    public void loadAllNews(@NonNull LoadDataCallback<BaseNews> callback) {

        Realm realm = mRealmHelper.getRealm();
        RealmResults<News> results = realm.where(News.class)
                .findAll();
        if (results != null && results.isEmpty()){
            callback.onLoaded(toArrayList(results));
        }
    }

    private ArrayList toArrayList(RealmResults<News> results){

        ArrayList array = new ArrayList();
        for (News obj: results) {
            array.add(obj.toBaseNews());
        }
        return array;
    }
}
