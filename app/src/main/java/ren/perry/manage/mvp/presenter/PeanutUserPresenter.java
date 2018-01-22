package ren.perry.manage.mvp.presenter;

import android.app.Activity;

import ren.perry.manage.bean.PeanutUserEnableBean;
import ren.perry.manage.bean.PeanutUserEndDateBean;
import ren.perry.manage.bean.PeanutUserListBean;
import ren.perry.manage.mvp.contract.PeanutUserContract;
import ren.perry.manage.mvp.model.PeanutUserModel;
import ren.perry.mvplibrary.net.ApiException;
import ren.perry.mvplibrary.rx.BaseSubscriber;
import ren.perry.perry.LoadingDialog;

/**
 * @author perry
 * @date 2018/1/18
 * WeChat 917351143
 */

public class PeanutUserPresenter extends PeanutUserContract.Presenter {

    private Activity activity;

    public PeanutUserPresenter(PeanutUserContract.View view, Activity activity) {
        mView = view;
        mModel = new PeanutUserModel();
        this.activity = activity;
    }

    @Override
    public void peanutUserList() {
        addSubscribe(mModel.peanutUserList()
                .subscribe(new BaseSubscriber<PeanutUserListBean>() {
                    @Override
                    public void onError(ApiException.ResponseException e) {
                        mView.onUserListError(e);
                    }

                    @Override
                    public void onNext(PeanutUserListBean bean) {
                        mView.onUserListSuccess(bean);
                    }
                }));
    }

    @Override
    public void peanutUserEnable(String deviceId, int state) {
        addSubscribe(mModel.peanutUserEnable(deviceId, state)
                .subscribe(new BaseSubscriber<PeanutUserEnableBean>() {
                    @Override
                    public void onError(ApiException.ResponseException e) {
                        mView.onEnableError(e);
                    }

                    @Override
                    public void onNext(PeanutUserEnableBean bean) {
                        mView.onEnableSuccess(bean);
                    }
                }));
    }

    @Override
    public void peanutUserEndDate(String deviceId, String endDate) {
        LoadingDialog dialog = new LoadingDialog(activity, "正在更新到期时间...");
        dialog.setNotCancel();
        addSubscribe(mModel.peanutUserEndDate(deviceId, endDate)
                .doOnSubscribe(dialog::show)
                .doOnCompleted(dialog::dismiss)
                .subscribe(new BaseSubscriber<PeanutUserEndDateBean>() {
                    @Override
                    public void onError(ApiException.ResponseException e) {
                        dialog.dismiss();
                        mView.onEndDateError(e);
                    }

                    @Override
                    public void onNext(PeanutUserEndDateBean bean) {
                        mView.onEndDateSuccess(bean);
                    }
                }));
    }
}
