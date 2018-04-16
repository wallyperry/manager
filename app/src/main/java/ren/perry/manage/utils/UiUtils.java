package ren.perry.manage.utils;

import android.view.LayoutInflater;
import android.view.View;

import ren.perry.manage.MyApp;

public class UiUtils {

    public static View getView(int res) {
        return LayoutInflater.from(MyApp.getContext()).inflate(res, null);
    }
}
