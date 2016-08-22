package me.shiyan.mvc_arch_dagger.news;

import dagger.Component;
import me.shiyan.mvc_arch_dagger.data.source.DataRepoComponent;
import me.shiyan.mvc_arch_dagger.utils.FragmentScope;

/**
 * Created by shiyan on 2016/8/18.
 */
@FragmentScope
@Component(dependencies =DataRepoComponent.class, modules = NewsPresenterModule.class)
public interface NewsComponent {

    void inject(NewsActivity newsActivity);
}
