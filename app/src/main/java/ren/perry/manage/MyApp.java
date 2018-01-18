package ren.perry.manage;

import ren.perry.mvplibrary.BaseMvpApplication;

/**
 * @author perry
 * @date 2018/1/18
 * WeChat 917351143
 */

public class MyApp extends BaseMvpApplication {
    private static MyApp mContext;

    @Override
    public void setTimeout(int connect, int read, int write) {
        super.setTimeout(15, 12, 12);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mContext == null) {
            mContext = this;
        }
    }

    public static MyApp getContext() {
        return mContext;
    }
}
