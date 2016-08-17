package me.shiyan.mvc_arch_dagger.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import me.shiyan.mvc_arch_dagger.bean.BaseNews;

/**
 * Created by shiyan on 2016/8/16.
 */
public class News extends RealmObject {

    @PrimaryKey
    public String id;
    public String title;
    public String link;
    public String descr;
    public String refinfo;
    public String thumb;
    public String time;

    public BaseNews toBaseNews(){

        BaseNews news = new BaseNews();
        news.id = id;
        news.descr = descr;
        news.link = link;
        news.refinfo = refinfo;
        news.thumb = thumb;
        news.time = time;
        news.title = title;

        return news;
    }
}
