package me.shiyan.mvc_arch_dagger.utils;

/**
 * Created by shiyan on 2016/8/22.
 */
public class Log {

    private String mClassName;
    public Log(String className){
        this.mClassName = className;
    }

    public void d(String debugInfo){
        android.util.Log.d(mClassName,debugInfo);
    }

    public void i(String info){
        android.util.Log.i(mClassName,info);
    }
}
