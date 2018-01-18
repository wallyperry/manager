package ren.perry.manage.adapter;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import ren.perry.manage.R;
import ren.perry.manage.bean.PeanutUserListBean;
import ren.perry.manage.utils.DateUtils;

/**
 * @author perry
 * @date 2018/1/18
 * WeChat 917351143
 */

public class PeanutUserRvAdapter extends BaseQuickAdapter<PeanutUserListBean.DataBean, BaseViewHolder> {
    public PeanutUserRvAdapter() {
        super(R.layout.item_peanut_user_rv);
    }

    private CheckedListener listener;

    public void setOnCheckedChangeListener(CheckedListener listener) {
        this.listener = listener;
    }

    public interface CheckedListener {
        void onCheckedListener(PeanutUserListBean.DataBean item, CompoundButton cButton, boolean b, int position);
    }

    @Override
    protected void convert(BaseViewHolder helper, PeanutUserListBean.DataBean item) {

        String startTimeStr = "创建：" + DateUtils.stampToDate(item.getCreateDate());
        String endTimeStr = "到期：" + DateUtils.stampToDate(item.getEndDate());
        boolean enable = "1".equals(item.getState());

        helper.setText(R.id.tvModel, item.getDevice_model())
                .setText(R.id.tvDeviceId, item.getDevice_id())
                .setText(R.id.tvPhone, item.getPhone())
                .setText(R.id.tvStartTime, startTimeStr)
                .setText(R.id.tvEndTime, endTimeStr)
                .setText(R.id.tvInfo, item.getInfo())
                .setVisible(R.id.tvInfo, item.getInfo().length() >= 1)
                .setChecked(R.id.switchState, enable)
                .addOnClickListener(R.id.tvSetEnd)
                .setOnCheckedChangeListener(R.id.switchState, (compoundButton, b) -> {
                    if (listener != null) {
                        listener.onCheckedListener(item, compoundButton, b, helper.getAdapterPosition());
                    }
                });
    }
}
