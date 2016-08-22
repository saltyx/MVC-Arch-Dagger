package me.shiyan.mvc_arch_dagger.news;

import dagger.Module;
import dagger.Provides;
import me.shiyan.mvc_arch_dagger.utils.Category;
import me.shiyan.mvc_arch_dagger.utils.Device;

/**
 * Created by shiyan on 2016/8/18.
 */
@Module
public class NewsPresenterModule {

    private final NewsContract.View mView;

    private final Device mDevice;

    public NewsPresenterModule(NewsContract.View mView,
                               Device device) {
        this.mView = mView;
        this.mDevice = device;
    }

    @Provides
    Device provideDevice(){
        return mDevice;
    }

    @Provides
    NewsContract.View provideNewsContractView(){
        return mView;
    }
}
