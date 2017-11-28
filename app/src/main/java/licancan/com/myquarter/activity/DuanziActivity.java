package licancan.com.myquarter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;

public class DuanziActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_duan_back;
    private TextView tv_duan_share;

    @Override
    public int getLayoutid() {
        return R.layout.activity_duanzi;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void Creat() {
        initView();
    }

    private void initView() {
        tv_duan_back = findViewById(R.id.tv_duan_back);
        tv_duan_back.setOnClickListener(this);
        tv_duan_share = findViewById(R.id.tv_duan_share);
        tv_duan_share.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tv_duan_back:
                finish();
                break;
            //发表
            case R.id.tv_duan_share:
                break;
        }
    }
}
