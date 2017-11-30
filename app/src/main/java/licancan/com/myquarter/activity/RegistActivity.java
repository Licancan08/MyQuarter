package licancan.com.myquarter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.haha.perflib.Main;

import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;
import licancan.com.myquarter.entity.Regist;
import licancan.com.myquarter.presenter.RegistPresenter;
import licancan.com.myquarter.view.RegistView;

public class RegistActivity extends BaseActivity<RegistPresenter> implements View.OnClickListener, RegistView {

    private TextView hava_regist;
    private ImageView regist_back;
    private TextView regist_onlylogin;
    private EditText regist_name;
    private EditText regist_pwd;
    private Button regist_button;

    @Override
    public int getLayoutid() {
        return R.layout.activity_regist;
    }

    @Override
    public RegistPresenter initPresenter() {

        return new RegistPresenter(this,this);
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

        regist_name = findViewById(R.id.regist_name);
        regist_pwd = findViewById(R.id.regist_pwd);
        regist_button = findViewById(R.id.regist_button);
        regist_button.setOnClickListener(this);
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
            case R.id.regist_button:
                String mobile = regist_name.getText().toString();
                String password = regist_pwd.getText().toString();
                presenter.regist(mobile,password);
                break;
        }

    }

    @Override
    public void RequestSuccess(Regist regist) {
        ShowToast(regist.getMsg());
        startActivity(LoginActivity.class);
    }

    @Override
    public void RequestFailure(Regist regist) {
        ShowToast(regist.getMsg());
    }

    @Override
    public void Failure(Regist regist) {
        ShowToast(regist.getMsg());
    }
}
