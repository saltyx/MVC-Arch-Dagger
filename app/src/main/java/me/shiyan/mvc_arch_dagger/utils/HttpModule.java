package me.shiyan.mvc_arch_dagger.utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by shiyan on 2016/8/17.
 */
@Module
public class HttpModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient();
    }


}
