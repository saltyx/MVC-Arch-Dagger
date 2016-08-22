package me.shiyan.mvc_arch_dagger.utils;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by shiyan on 2016/8/16.
 */
public class HttpUtils {

    @Singleton
    @Inject
    OkHttpClient client;

    public void getNews(Device device, Category catId, int pagesSize, String lastId, Callback callback) throws JSONException{

        JSONObject object = new JSONObject();
        object.put("device",device.toString().toLowerCase());
        int categoryId;
        switch (catId){
            case TOUR_NEWS:
                categoryId = 1;break;
            case TOP_RATED_NEWS:
                categoryId = 2;break;
            case APP_NEWS:
                categoryId = 3;break;
            case GAME_NEWS:
                categoryId = 4;break;
            default:
                categoryId = -1;break;
        }
        object.put("catid",categoryId);
        object.put("pagesSize",pagesSize);
        object.put("sid",lastId);

        Request request = new Request.Builder()
                .url("http://apis.baidu.com/cd_boco/chinanews/testnewsapi")
                .header("apikey","7792edd128c302dd40d5d056c5ab478a")
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void getImage(String url, Callback callback){
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
