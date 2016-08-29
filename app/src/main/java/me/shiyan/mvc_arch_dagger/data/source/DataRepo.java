package me.shiyan.mvc_arch_dagger.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.shiyan.mvc_arch_dagger.bean.BaseNews;
import me.shiyan.mvc_arch_dagger.data.source.local.DataSource;
import me.shiyan.mvc_arch_dagger.utils.Category;
import me.shiyan.mvc_arch_dagger.utils.Device;
import me.shiyan.mvc_arch_dagger.utils.HttpUtils;
import me.shiyan.mvc_arch_dagger.utils.Log;
import me.shiyan.mvc_arch_dagger.utils.SerializeUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by shiyan on 2016/8/16.
 */

@Singleton
public class DataRepo implements NewsDataSource {

    private final DataSource mDataSource;

    private final HttpUtils mHttpUtils;

    private final Log mLog = new Log(getClass().getName());

    @Inject
    DataRepo(DataSource newsDataSource,HttpUtils httpUtils){
        mDataSource = newsDataSource;
        mHttpUtils = httpUtils;
    }

    @Override
    public void saveNews(@Nullable JSONArray news, SaveDataCallback callback) {
        mDataSource.saveNews(news,callback);
    }

    @Override
    public void getNewsById(@NonNull String id, GetDataCallback<BaseNews> callback) {
        mDataSource.getNewsById(id,callback);
    }

    //加载本地所有的信息
    @Override
    public void loadAllNews(@NonNull LoadDataCallback<BaseNews> callback) {
        mDataSource.loadAllNews(callback);
    }

    /*从网络加载更多信息
    * 用于下拉刷新*/

    @Override
    public void loadMoreFromRemote(@NonNull Device device, @NonNull Category category, @NonNull String lastId,
                                   @NonNull final LoadDataCallback<BaseNews> callback){
        mHttpUtils.getNews(device, category, lastId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(null,e);
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    try{
                        final JSONArray results = new JSONObject(response.body().string()).getJSONArray("data");

                        saveNews(results, new SaveDataCallback(){
                            @Override
                            public void onSuccess() {
                                try {
                                    callback.onLoaded(SerializeUtils.serializeObject(results,BaseNews.class));
                                }catch (Exception e){
                                    callback.onFailure(null,e);
                                }
                            }

                            @Override
                            public void onFailure(@Nullable String error, @Nullable Exception e) {
                                callback.onFailure(error,e);
                            }
                        });
                        response.close();
                    }catch (JSONException e){
                        callback.onFailure("Json Error",e);
                    }

                }else {
                    callback.onFailure("response failed",null);
                }
            }
        });
    }
}
