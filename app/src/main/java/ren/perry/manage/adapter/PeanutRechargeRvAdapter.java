package ren.perry.manage.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import ren.perry.manage.R;
import ren.perry.manage.bean.PeanutRechargeListBean;
import ren.perry.manage.utils.DateUtils;

/**
 * @author perry
 * @date 2018/1/19
 * WeChat 917351143
 */

public class PeanutRechargeRvAdapter extends BaseQuickAdapter<PeanutRechargeListBean.DataBean, BaseViewHolder> {
    public PeanutRechargeRvAdapter() {
        super(R.layout.item_peanut_recharge_rv);
    }

    @Override
    protected void convert(BaseViewHolder helper, PeanutRechargeListBean.DataBean item) {
        String orderIdStr = "订单号：" + item.getId();
        String stateStr = "1".equals(item.getState()) ? "充值成功" : "待确认";
        String infoStr = item.getInfo();
        String modelStr = item.getDevice_model();
        String deviceIdStr = item.getDevice_id();
        String phoneStr = item.getPhone();
        String commitTimeStr = "提交充值：" + DateUtils.stampToDate(item.getCreateTime());
        String endTimeStr = "服务到期：" + DateUtils.stampToDate(item.getEndDate());
        String confirmStr = "确认(" + item.getPrice() + "元)";

        helper.setText(R.id.tvOrderId, orderIdStr)
                .setText(R.id.tvState, stateStr)
                .setText(R.id.tvInfo, infoStr)
                .setText(R.id.tvModel, modelStr)
                .setText(R.id.tvDeviceId, deviceIdStr)
                .setText(R.id.tvPhone, phoneStr)
                .setText(R.id.tvCommitTime, commitTimeStr)
                .setText(R.id.tvEndTime, endTimeStr)
                .setText(R.id.tvConfirm, confirmStr)
                .addOnClickListener(R.id.tvConfirm);
    }
}
