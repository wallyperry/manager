package ren.perry.manage.ui.peanut;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import ren.perry.manage.R;
import ren.perry.manage.base.BaseMvpActivity;
import ren.perry.mvplibrary.base.BasePresenter;

/**
 * @author perry
 * @date 2018/1/18
 * WeChat 917351143
 */

public class PeanutActivity extends BaseMvpActivity {
    @Bind(R.id.tvTitle)
    TextView tvTitle;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_peanut;
    }

    @Override
    protected void initView() {
        tvTitle.setText("花生果");
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @OnClick({R.id.tvBack, R.id.tvUser, R.id.tvRechargeConfirm, R.id.tvVersion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvBack:       //返回
                finish();
                break;
            case R.id.tvUser:       //用户管理
                startActivity(new Intent(this, PeanutUserActivity.class));
                break;
            case R.id.tvRechargeConfirm:    //充值确认
                Intent intent = new Intent(this, PeanutRechargeActivity.class);
                intent.putExtra(PeanutRechargeActivity.KEY_TYPE, PeanutRechargeActivity.TYPE_CONFIRM);
                startActivity(intent);
                break;
            case R.id.tvVersion:    //版本管理
                break;
        }
    }
}
