package ren.perry.manage.mvp.contract;

import ren.perry.manage.bean.PeanutRechargeListBean;
import ren.perry.mvplibrary.base.BaseModel;
import ren.perry.mvplibrary.base.BasePresenter;
import ren.perry.mvplibrary.base.BaseView;
import ren.perry.mvplibrary.net.ApiException;
import rx.Observable;

/**
 * @author perry
 * @date 2018/1/19
 * WeChat 917351143
 */

public interface PeanutRechargeContract {
    interface View extends BaseView {
        void onRechargeListSuccess(PeanutRechargeListBean bean);

        void onRechargeListError(ApiException.ResponseException e);
    }

    interface Model extends BaseModel {
        Observable<PeanutRechargeListBean> rechargeList(int type, String deviceId);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void rechargeList(int type, String deviceId);
    }
}
