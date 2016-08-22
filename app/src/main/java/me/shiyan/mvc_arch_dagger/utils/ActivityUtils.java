package me.shiyan.mvc_arch_dagger.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by shiyan on 2016/8/18.
 */
public class ActivityUtils {

    /*用于activity*/
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment,
                                             @NonNull int id){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(id, fragment);
        transaction.commit();
    }
}
