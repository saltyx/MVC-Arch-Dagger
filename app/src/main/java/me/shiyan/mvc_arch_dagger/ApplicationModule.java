package me.shiyan.mvc_arch_dagger;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shiyan on 2016/8/17.
 */
@Module
public class ApplicationModule {

    private final Application mApplication;

    ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    Application provideApplication(){
        return mApplication;
    }
}
