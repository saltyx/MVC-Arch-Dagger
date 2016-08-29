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

    public void getNews(Device device, Category catId, String lastId, Callback callback) {

        JSONObject object = new JSONObject();
        try {
            object.put("device",device.toString().toLowerCase());
            object.put("catid",catId.getAction());
            object.put("pagesSize",AppConfig.PAGE_SIZE);
            object.put("sid",lastId);
        }catch (JSONException e){
            e.printStackTrace();
            return;
        }
        Request request = new Request.Builder()
                .url(AppConfig.URL)
                .header("apikey",AppConfig.APP_KEY)
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
