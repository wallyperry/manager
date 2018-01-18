package ren.perry.manage.mvp.model;

import ren.perry.manage.api.ApiEngine;
import ren.perry.manage.bean.PeanutUserEnableBean;
import ren.perry.manage.bean.PeanutUserEndDateBean;
import ren.perry.manage.bean.PeanutUserListBean;
import ren.perry.manage.mvp.contract.PeanutUserContract;
import ren.perry.mvplibrary.rx.RxSchedulers;
import rx.Observable;

/**
 * @author perry
 * @date 2018/1/18
 * WeChat 917351143
 */

public class PeanutUserModel implements PeanutUserContract.Model {
    @Override
    public Observable<PeanutUserListBean> peanutUserList() {
        return ApiEngine.getInstance()
                .getApiService()
                .peanutUserList()
                .compose(RxSchedulers.switchThread());
    }

    @Override
    public Observable<PeanutUserEnableBean> peanutUserEnable(String deviceId, int state) {
        return ApiEngine.getInstance()
                .getApiService()
                .peanutUserEnable(deviceId, state)
                .compose(RxSchedulers.switchThread());
    }

    @Override
    public Observable<PeanutUserEndDateBean> peanutUserEndDate(String deviceId, String endDate) {
        return ApiEngine.getInstance()
                .getApiService()
                .peanutUserEndDate(deviceId, endDate)
                .compose(RxSchedulers.switchThread());
    }
}
