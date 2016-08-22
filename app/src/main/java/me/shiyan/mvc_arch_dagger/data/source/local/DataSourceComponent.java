package me.shiyan.mvc_arch_dagger.data.source.local;

import android.app.Application;

import dagger.Component;
import me.shiyan.mvc_arch_dagger.ApplicationModule;

/**
 * Created by shiyan on 2016/8/22.
 */
@Component(modules = ApplicationModule.class)
public interface DataSourceComponent {

    Application getApplication();
}
