package me.shiyan.mvc_arch_dagger.data.source;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.shiyan.mvc_arch_dagger.data.source.local.DataSource;
import me.shiyan.mvc_arch_dagger.utils.HttpUtils;

/**
 * Created by shiyan on 2016/8/17.
 */
@Module
public class DataRepoModule {

    @Singleton
    @Provides
    DataSource provideDataSource(Application context){
        return new DataSource(context);
    }

    @Singleton
    @Provides
    HttpUtils provideHttpUtils(){
        return new HttpUtils();
    }
}
