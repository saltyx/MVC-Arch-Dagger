package me.shiyan.mvc_arch_dagger.utils;

/**
 * Created by shiyan on 2016/8/17.
 */
public enum Category {

    TOUR_NEWS(1),
    TOP_RATED_NEWS(2),
    APP_NEWS(3),
    GAME_NEWS(4);

    private int action;
    Category(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }
}
