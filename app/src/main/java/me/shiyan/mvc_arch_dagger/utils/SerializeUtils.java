package me.shiyan.mvc_arch_dagger.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

import me.shiyan.mvc_arch_dagger.bean.BaseNews;

/**
 * Created by shiyan on 2016/8/29.
 */

/*专用于序列化bean的class*/
public class SerializeUtils {

    public static BaseNews serializeObject(JSONObject jsonObject, Class<?> clss )throws InstantiationException
            ,IllegalAccessException
            ,JSONException {

        BaseNews obj = (BaseNews)clss.newInstance();
        Field[] fields = clss.getFields();
        for (Field f: fields) {
            switch (f.getType().getSimpleName()){
                case "String":
                    f.set(obj,jsonObject.get(f.getName()));
                    break;
                case "int":
                    f.set(obj,jsonObject.get(f.getName()));
                    break;
                default:
                    break;
            }
        }
        return obj;
    }

    public static ArrayList<BaseNews> serializeObject(JSONArray jsonArray, Class<?> clss )throws InstantiationException
            ,IllegalAccessException
            ,JSONException {

        ArrayList<BaseNews> data = new ArrayList<>();

        for (int i = 0 ; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            data.add(serializeObject(jsonObject,clss));
        }

        return data;
    }
}
