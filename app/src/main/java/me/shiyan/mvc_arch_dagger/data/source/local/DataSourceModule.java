package me.shiyan.mvc_arch_dagger.data.source.local;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shiyan on 2016/8/18.
 */
@Module
public class DataSourceModule {

    @Singleton
    @Provides
    DataSource provideDataSource(Application context){
        return new DataSource(context);
    }
}
