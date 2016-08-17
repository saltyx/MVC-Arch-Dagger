package me.shiyan.mvc_arch_dagger.data.source.local;

import android.app.Application;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by shiyan on 2016/8/4.
 */
/*单例*/

public class RealmHelper {

    @Inject
    public RealmHelper(@NonNull Application context){

        RealmConfiguration configuration = new RealmConfiguration.Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
        initialDB();//构造之后直接创建数据表
    }

    private void initialDB(){
        Realm realm = getRealm();
        realm.beginTransaction();
        try{
            realm.deleteAll();
            realm.commitTransaction();
        }catch (Exception e){
            e.printStackTrace();
            realm.cancelTransaction();
        }
    }

    public Realm getRealm(){
        return Realm.getDefaultInstance();
    }
}
