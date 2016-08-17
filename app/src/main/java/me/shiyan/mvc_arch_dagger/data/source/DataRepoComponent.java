package me.shiyan.mvc_arch_dagger.data.source;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import me.shiyan.mvc_arch_dagger.ApplicationModule;
import me.shiyan.mvc_arch_dagger.data.source.local.DataSource;

/**
 * Created by shiyan on 2016/8/17.
 */
@Singleton
@Component(modules = {DataRepoModule.class, ApplicationModule.class})
public interface DataRepoComponent {

    DataSource getDataRepo();
}
