package ren.perry.manage.mvp.contract;

import ren.perry.manage.bean.PeanutUserEnableBean;
import ren.perry.manage.bean.PeanutUserEndDateBean;
import ren.perry.manage.bean.PeanutUserListBean;
import ren.perry.mvplibrary.base.BaseModel;
import ren.perry.mvplibrary.base.BasePresenter;
import ren.perry.mvplibrary.base.BaseView;
import ren.perry.mvplibrary.net.ApiException;
import rx.Observable;

/**
 * @author perry
 * @date 2018/1/18
 * WeChat 917351143
 */

public interface PeanutUserContract {

    interface View extends BaseView {
        void onUserListSuccess(PeanutUserListBean bean);

        void onUserListError(ApiException.ResponseException e);

        void onEnableSuccess(PeanutUserEnableBean bean);

        void onEnableError(ApiException.ResponseException e);

        void onEndDateSuccess(PeanutUserEndDateBean bean);

        void onEndDateError(ApiException.ResponseException e);
    }

    interface Model extends BaseModel {
        Observable<PeanutUserListBean> peanutUserList();

        Observable<PeanutUserEnableBean> peanutUserEnable(String deviceId, int state);

        Observable<PeanutUserEndDateBean> peanutUserEndDate(String deviceId, String endDate);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void peanutUserList();

        public abstract void peanutUserEnable(String deviceId, int state);

        public abstract void peanutUserEndDate(String deviceId, String endDate);
    }
}
