package me.shiyan.mvc_arch_dagger.news;

import me.shiyan.mvc_arch_dagger.BasePresenter;
import me.shiyan.mvc_arch_dagger.BaseView;
import me.shiyan.mvc_arch_dagger.utils.Category;
import me.shiyan.mvc_arch_dagger.utils.Device;

/**
 * Created by shiyan on 2016/8/17.
 */
public interface NewsContract  {

    interface View extends BaseView<Presenter> {

        void showByCategory(Category category);
        void showPopupWindow();
        void showNoData();

    }

    interface Presenter extends BasePresenter{

        void loadFromRemote();

    }
}
