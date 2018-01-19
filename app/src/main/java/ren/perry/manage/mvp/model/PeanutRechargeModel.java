package ren.perry.manage.mvp.model;

import ren.perry.manage.api.ApiEngine;
import ren.perry.manage.bean.PeanutRechargeListBean;
import ren.perry.manage.mvp.contract.PeanutRechargeContract;
import ren.perry.mvplibrary.rx.RxSchedulers;
import rx.Observable;

/**
 * @author perry
 * @date 2018/1/19
 * WeChat 917351143
 */

public class PeanutRechargeModel implements PeanutRechargeContract.Model {
    @Override
    public Observable<PeanutRechargeListBean> rechargeList(int type, String deviceId) {
        return ApiEngine.getInstance()
                .getApiService()
                .peanutRechargeList(type, deviceId)
                .compose(RxSchedulers.switchThread());
    }
}
