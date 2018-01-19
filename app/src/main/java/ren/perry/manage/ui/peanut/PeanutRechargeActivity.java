package ren.perry.manage.ui.peanut;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.Bind;
import ren.perry.manage.R;
import ren.perry.manage.adapter.PeanutRechargeRvAdapter;
import ren.perry.manage.base.BaseMvpActivity;
import ren.perry.manage.bean.PeanutRechargeListBean;
import ren.perry.manage.mvp.contract.PeanutRechargeContract;
import ren.perry.manage.mvp.presenter.PeanutRechargePresenter;
import ren.perry.mvplibrary.net.ApiException;

/**
 * @author perry
 * @date 2018/1/19
 * WeChat 917351143
 */

public class PeanutRechargeActivity extends BaseMvpActivity<PeanutRechargePresenter> implements PeanutRechargeContract.View, BaseQuickAdapter.OnItemChildClickListener {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    public static final String KEY_TYPE = "TYPE";
    public static final String KEY_DEVICE_ID = "DEVICE_ID";
    public static final String KEY_USER_ID = "USER_ID";

    private int cType;
    private String cDeviceId;
    private String cUserId;

    private PeanutRechargeRvAdapter rvAdapter;

    /**
     * 全部记录
     */
    public static final int TYPE_ALL = 1;
    /**
     * 待确认
     */
    public static final int TYPE_CONFIRM = 2;
    /**
     * 单用户
     */
    public static final int TYPE_USER = 3;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_peanut_recharge;
    }

    @Override
    protected void initView() {
        cType = getIntent().getIntExtra(KEY_TYPE, 0);
        switch (cType) {
            case TYPE_ALL:
                tvTitle.setText("所有充值记录");
                break;
            case TYPE_CONFIRM:
                tvTitle.setText("待确认充值单");
                break;
            case TYPE_USER:
                cDeviceId = getIntent().getStringExtra(KEY_DEVICE_ID);
                cUserId = getIntent().getStringExtra(KEY_USER_ID);
                String title = "用户ID:" + cUserId + "的充值订单";
                tvTitle.setText(title);
                break;
        }


        refreshLayout.setColorSchemeResources(R.color.black, R.color.black, R.color.black);
        refreshLayout.setOnRefreshListener(this::fetchData);

        rvAdapter = new PeanutRechargeRvAdapter();
        rvAdapter.bindToRecyclerView(recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        rvAdapter.setOnItemChildClickListener(this);
        recyclerView.setAdapter(rvAdapter);

        refreshLayout.post(() -> {
            refreshLayout.setRefreshing(true);
            fetchData();
        });

    }

    private void fetchData() {
        mPresenter.rechargeList(cType, cDeviceId);
    }

    @Override
    protected PeanutRechargePresenter onCreatePresenter() {
        return new PeanutRechargePresenter(this);
    }

    @Override
    public void onRechargeListSuccess(PeanutRechargeListBean bean) {
        refreshLayout.setRefreshing(false);
        switch (bean.getCode()) {
            case 200:
                if (bean.getData() == null || bean.getData().size() < 1) {
                    rvAdapter.setNewData(null);
                    rvAdapter.setEmptyView(R.layout.view_rv_error);
                    return;
                }
                rvAdapter.setNewData(bean.getData());
                break;
            case 201:
                rvAdapter.setNewData(null);
                rvAdapter.setEmptyView(R.layout.view_rv_error);
                break;
        }
    }

    @Override
    public void onRechargeListError(ApiException.ResponseException e) {
        refreshLayout.setRefreshing(false);
        rvAdapter.setNewData(null);
        rvAdapter.setEmptyView(R.layout.view_rv_error);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.tvConfirm:
                toastShow("正在开发中");
                break;
        }
    }
}
