package me.shiyan.mvc_arch_dagger;

/**
 * Created by shiyan on 2016/8/17.
 */
public interface BaseView<T> {

    void setPresenter(T presenter);
}
