package me.shiyan.mvc_arch_dagger.news;

import javax.inject.Inject;

import me.shiyan.mvc_arch_dagger.data.source.DataRepo;
import me.shiyan.mvc_arch_dagger.utils.Category;
import me.shiyan.mvc_arch_dagger.utils.Device;
import me.shiyan.mvc_arch_dagger.utils.HttpUtils;

/**
 * Created by shiyan on 2016/8/17.
 */

final class NewsPresenter implements NewsContract.Presenter {

    private final DataRepo mDataRepo;

    private final NewsContract.View mView;

    Device mDevice;

    @Inject
    NewsPresenter(Device device, DataRepo mDataRepo, NewsContract.View mView) {
        this.mDataRepo = mDataRepo;
        this.mView = mView;
        this.mDevice = device;
    }

    @Inject
    void setupListener(){
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadFromRemote() {

    }
}
