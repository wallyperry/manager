package ren.perry.manage.base;

import android.content.Intent;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;

import ren.perry.mvplibrary.base.BaseActivity;
import ren.perry.mvplibrary.base.BasePresenter;

/**
 * @author perry
 * @date 2018/1/5
 * WeChat 917351143
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity<P> {

    protected void setFullScreen() {
        getWindow().setFlags(0x00000400, 0x00000400);
        requestWindowFeature(1);
    }

    protected void showMsg(String title, String msg, MaterialDialog.SingleButtonCallback onPositive) {
        new MaterialDialog.Builder(this)
                .title(title)
                .content(msg)
                .positiveText("好的")
                .onPositive(onPositive)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .show();
    }

    protected void showMsg(String title, String msg) {
        new MaterialDialog.Builder(this)
                .title(title)
                .content(msg)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText("好的")
                .show();
    }

    protected void showMsg(String content) {
        new MaterialDialog.Builder(this)
                .content(content)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText("好的")
                .show();
    }

    protected void showMsg(String content, MaterialDialog.SingleButtonCallback onPositive) {
        new MaterialDialog.Builder(this)
                .content(content)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText("好的")
                .onPositive(onPositive)
                .show();
    }

    protected void startActivity(Class<? extends BaseMvpActivity> cls) {
        startActivity(new Intent(this, cls));
    }

    protected void startActivity(Class<? extends BaseMvpActivity> cls, int code) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("code", code);
        startActivity(intent);
    }

    protected void startActivity(Class<? extends BaseMvpActivity> cls, String msg) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("msg", msg);
        startActivity(intent);
    }

    protected void startActivity(Class<? extends BaseMvpActivity> cls, Bundle b) {
        Intent intent = new Intent(this, cls);
        if (b != null) {
            intent.putExtras(b);
        }
        startActivity(intent);
    }

    protected String getBundleString(String key) {
        Bundle b = getIntent().getExtras();
        return b != null ? b.getString(key, "") : "";
    }
}
