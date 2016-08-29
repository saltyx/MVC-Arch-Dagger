package me.shiyan.mvc_arch_dagger;

import android.app.Application;

import me.shiyan.mvc_arch_dagger.data.source.DaggerDataRepoComponent;
import me.shiyan.mvc_arch_dagger.data.source.DataRepoComponent;
import me.shiyan.mvc_arch_dagger.data.source.DataRepoModule;

/**
 * Created by shiyan on 2016/8/22.
 */
public class NewsApplication extends Application {

    private DataRepoComponent mDataRepoComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mDataRepoComponent = DaggerDataRepoComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataRepoModule(new DataRepoModule()).build();
    }

    public DataRepoComponent getTasksRepositoryComponent() {
        return mDataRepoComponent;
    }
}
