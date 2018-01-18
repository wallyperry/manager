package ren.perry.manage.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.OnClick;
import ren.perry.library.GlideMan;
import ren.perry.manage.R;
import ren.perry.manage.base.BaseMvpActivity;
import ren.perry.manage.ui.peanut.PeanutActivity;
import ren.perry.mvplibrary.base.BasePresenter;

public class MainActivity extends BaseMvpActivity {

    @Bind(R.id.imageView)
    ImageView imageView;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        new GlideMan.Builder()
                .load("http://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1516861773&di=85eb85a53173cd3eeea7dce51620e814&imgtype=jpg&src=http%3A%2F%2Fimg6.bdstatic.com%2Fimg%2Fimage%2Fxiaolu%2Fkeketuohaimaidiny.JPG")
                .into(imageView);

    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @OnClick({R.id.llPeanut, R.id.llXsp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llPeanut:     //花生果
                startActivity(new Intent(this, PeanutActivity.class));
                break;
            case R.id.llXsp:        //小水泡
                break;
        }
    }
}
