package licancan.com.myquarter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.haha.perflib.Main;

import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;

public class RegistActivity extends BaseActivity implements View.OnClickListener {

    private TextView hava_regist;
    private ImageView regist_back;
    private TextView regist_onlylogin;

    @Override
    public int getLayoutid() {
        return R.layout.activity_regist;
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
        hava_regist = findViewById(R.id.hava_regist);
        hava_regist.setOnClickListener(this);
        regist_back = findViewById(R.id.regist_back);
        regist_back.setOnClickListener(this);
        regist_onlylogin = findViewById(R.id.regist_onlylogin);
        regist_onlylogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.hava_regist:
                //已经有了账号   进入登录界面
                startActivity(LoginActivity.class) ;
                break;
            case R.id.regist_back:
                finish();
                break;
            case R.id.regist_onlylogin:
                //游客登录
                startActivity(MainActivity.class);
                break;
        }

    }
}
