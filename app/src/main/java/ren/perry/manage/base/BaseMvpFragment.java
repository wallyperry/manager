package ren.perry.manage.base;

import android.content.Intent;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;

import ren.perry.mvplibrary.base.BaseFragment;
import ren.perry.mvplibrary.base.BasePresenter;

/**
 * Email: pl.w@outlook.com
 * Created by perry on 2018/1/10.
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment<P> {
    protected void showMsg(String title, String msg, MaterialDialog.SingleButtonCallback onPositive) {
        new MaterialDialog.Builder(activity)
                .title(title)
                .content(msg)
                .positiveText("好的")
                .onPositive(onPositive)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .show();
    }

    protected void showMsg(String title, String msg) {
        new MaterialDialog.Builder(activity)
                .title(title)
                .content(msg)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText("好的")
                .show();
    }

    protected void startActivity(Class<? extends BaseMvpActivity> cls) {
        startActivity(new Intent(activity, cls));
    }

    protected void startActivity(Class<? extends BaseMvpActivity> cls, int code) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("code", code);
        startActivity(intent);
    }

    protected void startActivity(Class<? extends BaseMvpActivity> cls, String msg) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("msg", msg);
        startActivity(intent);
    }

    protected void startActivity(Class<? extends BaseMvpActivity> cls, Bundle b) {
        Intent intent = new Intent(activity, cls);
        if (b != null) {
            intent.putExtras(b);
        }
        startActivity(intent);
    }
}
