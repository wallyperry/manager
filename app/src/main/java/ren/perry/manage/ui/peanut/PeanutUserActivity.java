package ren.perry.manage.ui.peanut;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fastaccess.datetimepicker.DatePickerFragmentDialog;
import com.fastaccess.datetimepicker.DateTimeBuilder;
import com.fastaccess.datetimepicker.callback.DatePickerCallback;
import com.fastaccess.datetimepicker.callback.TimePickerCallback;

import butterknife.Bind;
import butterknife.OnClick;
import ren.perry.manage.R;
import ren.perry.manage.adapter.PeanutUserRvAdapter;
import ren.perry.manage.base.BaseMvpActivity;
import ren.perry.manage.bean.PeanutUserEnableBean;
import ren.perry.manage.bean.PeanutUserEndDateBean;
import ren.perry.manage.bean.PeanutUserListBean;
import ren.perry.manage.mvp.contract.PeanutUserContract;
import ren.perry.manage.mvp.presenter.PeanutUserPresenter;
import ren.perry.mvplibrary.net.ApiException;

/**
 * @author perry
 * @date 2018/1/18
 * WeChat 917351143
 */

public class PeanutUserActivity extends BaseMvpActivity<PeanutUserPresenter> implements
        PeanutUserContract.View, BaseQuickAdapter.OnItemChildClickListener,
        PeanutUserRvAdapter.CheckedListener, DatePickerCallback, TimePickerCallback {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private PeanutUserRvAdapter rvAdapter;
    private String currentDeviceId;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_peanut_user;
    }

    @Override
    protected void initView() {
        tvTitle.setText("花生果用户");

        refreshLayout.setColorSchemeResources(R.color.black, R.color.black, R.color.black);
        refreshLayout.setOnRefreshListener(this::fetchData);

        rvAdapter = new PeanutUserRvAdapter();
        rvAdapter.bindToRecyclerView(recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        rvAdapter.setOnItemChildClickListener(this);
        rvAdapter.setOnCheckedChangeListener(this);
        recyclerView.setAdapter(rvAdapter);

        refreshLayout.post(() -> {
            refreshLayout.setRefreshing(true);
            fetchData();
        });
    }

    private void fetchData() {
        mPresenter.peanutUserList();
    }

    @Override
    protected PeanutUserPresenter onCreatePresenter() {
        return new PeanutUserPresenter(this, this);
    }


    @OnClick(R.id.tvBack)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onUserListSuccess(PeanutUserListBean bean) {
        refreshLayout.setRefreshing(false);
        if (bean.getCode() == 1) {
            if (bean.getData() == null || bean.getData().size() < 1) {
                rvAdapter.setNewData(null);
                rvAdapter.setEmptyView(R.layout.view_rv_error);
                return;
            }
            rvAdapter.setNewData(bean.getData());
        } else {
            rvAdapter.setNewData(null);
            rvAdapter.setEmptyView(R.layout.view_rv_error);
        }
    }

    @Override
    public void onUserListError(ApiException.ResponseException e) {
        refreshLayout.setRefreshing(false);
        rvAdapter.setNewData(null);
        rvAdapter.setEmptyView(R.layout.view_rv_error);
    }

    @Override
    public void onEnableSuccess(PeanutUserEnableBean bean, int position) {
        mPresenter.peanutUserList();
    }

    @Override
    public void onEnableError(ApiException.ResponseException e, int position) {
        mPresenter.peanutUserList();
    }

    @Override
    public void onEndDateSuccess(PeanutUserEndDateBean bean) {
        toastShow(bean.getError());
        if (bean.getCode() == 1) {
            fetchData();
        }
    }

    @Override
    public void onEndDateError(ApiException.ResponseException e) {
        toastShow(e.message);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        PeanutUserListBean.DataBean item = (PeanutUserListBean.DataBean) adapter.getData().get(position);
        switch (view.getId()) {
            case R.id.tvSetEnd: //设置到期
                this.currentDeviceId = item.getDevice_id();
                DatePickerFragmentDialog.newInstance(
                        DateTimeBuilder.newInstance()
                                .withTime(true)
                                .with24Hours(true)
                                .withMinDate(Long.parseLong(item.getEndDate()) * 1000 + 86400000)
                ).show(getSupportFragmentManager(), "DatePickerFragmentDialog");
                break;
        }
    }

    @Override
    public void onCheckedListener(PeanutUserListBean.DataBean item, CompoundButton cButton, boolean b, int position) {
        switch (cButton.getId()) {
            case R.id.switchState:
                mPresenter.peanutUserEnable(item.getDevice_id(), b ? 1 : 0, position);
                break;
        }
    }

    @Override
    public void onDateSet(long date) {
    }

    @Override
    public void onTimeSet(long time, long d) {
        mPresenter.peanutUserEndDate(currentDeviceId, d / 1000 + "");
    }

}
