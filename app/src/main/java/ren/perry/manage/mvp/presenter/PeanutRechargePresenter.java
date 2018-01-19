package ren.perry.manage.mvp.presenter;

import ren.perry.manage.bean.PeanutRechargeListBean;
import ren.perry.manage.mvp.contract.PeanutRechargeContract;
import ren.perry.manage.mvp.model.PeanutRechargeModel;
import ren.perry.mvplibrary.net.ApiException;
import ren.perry.mvplibrary.rx.BaseSubscriber;

/**
 * @author perry
 * @date 2018/1/19
 * WeChat 917351143
 */

public class PeanutRechargePresenter extends PeanutRechargeContract.Presenter {

    public PeanutRechargePresenter(PeanutRechargeContract.View view) {
        mView = view;
        mModel = new PeanutRechargeModel();
    }

    @Override
    public void rechargeList(int type, String deviceId) {
        addSubscribe(mModel.rechargeList(type, type == 3 ? "" : deviceId)
                .subscribe(new BaseSubscriber<PeanutRechargeListBean>() {
                    @Override
                    public void onError(ApiException.ResponseException e) {
                        mView.onRechargeListError(e);
                    }

                    @Override
                    public void onNext(PeanutRechargeListBean bean) {
                        mView.onRechargeListSuccess(bean);
                    }
                }));
    }
}
