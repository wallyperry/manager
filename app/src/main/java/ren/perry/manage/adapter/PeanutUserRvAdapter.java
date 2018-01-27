package ren.perry.manage.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Date;

import ren.perry.manage.R;
import ren.perry.manage.bean.PeanutUserListBean;
import ren.perry.manage.mvp.presenter.PeanutUserPresenter;
import ren.perry.manage.utils.DateUtils;

/**
 * @author perry
 * @date 2018/1/18
 * WeChat 917351143
 */

public class PeanutUserRvAdapter extends BaseQuickAdapter<PeanutUserListBean.DataBean, BaseViewHolder> {

    private PeanutUserPresenter mPresenter;
    private long mTime;

    public PeanutUserRvAdapter(PeanutUserPresenter mPresenter) {
        super(R.layout.item_peanut_user_rv);
        this.mPresenter = mPresenter;
        mTime = new Date().getTime() / 1000;
    }

    @Override
    protected void convert(BaseViewHolder helper, PeanutUserListBean.DataBean item) {

        String nameStr = item.getId() + "（" + item.getDevice_model() + ")";
        String startTimeStr = "创建：" + DateUtils.stampToDate(item.getCreateDate());
        String endTimeStr = "到期：" + DateUtils.stampToDate(item.getEndDate());
        boolean enable = "1".equals(item.getState());
        long endTime = Long.parseLong(item.getEndDate());
        String isEndStr = mTime > endTime ? "已到期" : "";

        helper.setText(R.id.tvModel, nameStr)
                .setText(R.id.tvDeviceId, item.getDevice_id())
                .setText(R.id.tvPhone, item.getPhone())
                .setText(R.id.tvStartTime, startTimeStr)
                .setText(R.id.tvEndTime, endTimeStr)
                .setText(R.id.tvInfo, item.getInfo())
                .setText(R.id.tvIsEnd, isEndStr)
                .setVisible(R.id.tvInfo, item.getInfo().length() >= 1)
                .addOnClickListener(R.id.tvSetEnd)
                .setChecked(R.id.switchState, enable)
                .setOnCheckedChangeListener(R.id.switchState, (cButton, b) -> {
                    if (enable != b) {
                        mPresenter.peanutUserEnable(item.getDevice_id(), b ? 1 : 0);
                    } else {
                        cButton.setChecked(enable);
                    }
                });
    }
}
