package licancan.com.myquarter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;

public class Forget_TwoActivity extends BaseActivity implements View.OnClickListener {

    private TextView fortwo_already_regist;
    private ImageView for_two_back;
    private Button success;
    private TextView fortwo_onlylogin;

    @Override
    public int getLayoutid() {
        return R.layout.activity_forget__two;
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
        for_two_back = findViewById(R.id.for_two_back);
        for_two_back.setOnClickListener(this);
        fortwo_already_regist = findViewById(R.id.fortwo_already_regist);
        fortwo_already_regist.setOnClickListener(this);
        success = findViewById(R.id.success);
        success.setOnClickListener(this);
        fortwo_onlylogin = findViewById(R.id.fortwo_onlylogin);
        fortwo_onlylogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.for_two_back:
                finish();
                break;
            case R.id.fortwo_already_regist:
                //已经有了账号   进入登录界面
                startActivity(LoginActivity.class);
                break;
            case R.id.success:
                //修改完成   进入登录界面
                startActivity(LoginActivity.class);
                break;
            case R.id.fortwo_onlylogin:
                //游客登录
                startActivity(MainActivity.class);
                break;
        }

    }
}
