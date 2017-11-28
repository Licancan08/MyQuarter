package licancan.com.myquarter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;

public class FollowActivity extends BaseActivity implements View.OnClickListener {

    private TextView follow_back;

    @Override
    public int getLayoutid() {
        return R.layout.activity_follow;
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
        follow_back = findViewById(R.id.follow_back);
        follow_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.follow_back:
                finish();
                break;
        }
    }
}
