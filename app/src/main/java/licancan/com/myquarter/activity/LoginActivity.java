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

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView login_back;
    private TextView tv_regist;
    private TextView forget_pwd;
    private TextView login_in;
    private Button login_button;

    @Override
    public int getLayoutid() {
        return R.layout.activity_login;
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
        login_back = findViewById(R.id.login_back);
        login_back.setOnClickListener(this);
        tv_regist = findViewById(R.id.tv_regist);
        tv_regist.setOnClickListener(this);
        forget_pwd = findViewById(R.id.forget_pwd);
        forget_pwd.setOnClickListener(this);
        login_in = findViewById(R.id.login_in);
        login_in.setOnClickListener(this);
        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.login_back:
                //关闭此页面
                finish();
                break;
            case R.id.tv_regist:
                //进入注册界面
                startActivity(RegistActivity.class);
                break;
            case R.id.forget_pwd:
                //忘记密码进入修改界面
                startActivity(ForgetActivity.class);
                break;
            case R.id.login_in:
                //游客登录  直接进入主页
                startActivity(MainActivity.class);
                break;
            case R.id.login_button:
                startActivity(MainActivity.class);
                break;
        }

    }
}
