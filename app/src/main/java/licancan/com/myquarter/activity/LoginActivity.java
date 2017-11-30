package licancan.com.myquarter.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;
import licancan.com.myquarter.entity.Login;
import licancan.com.myquarter.presenter.LoginPresenter;
import licancan.com.myquarter.view.LoginView;

public class LoginActivity extends BaseActivity<LoginPresenter>  implements LoginView,View.OnClickListener {

    private ImageView login_back;
    private TextView tv_regist;
    private TextView forget_pwd;
    private TextView login_in;
    private Button login_button;
    private EditText et_mobile;
    private EditText et_pwd;

    @Override
    public int getLayoutid() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(this,this);
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

        et_mobile = findViewById(R.id.et_mobile);
        et_pwd = findViewById(R.id.et_pwd);
        et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
                String mobile = et_mobile.getText().toString();
                String password = et_pwd.getText().toString();
                presenter.login(mobile,password);
                System.out.println("登录===============");
                break;
        }

    }

    @Override
    public void RequestSuccess(Login login) {
      System.out.println("成功===========================");
        ShowToast(login.getMsg());
        //存入token值
        SharedPreferences tokensp=getSharedPreferences("config",MODE_PRIVATE);
        SharedPreferences.Editor edit=tokensp.edit();
        edit.putString("token",login.getData().getToken());
        edit.commit();

        SharedPreferences uidsp=getSharedPreferences("config",MODE_PRIVATE);
        SharedPreferences.Editor edit1=uidsp.edit();
        edit1.putString("uid",login.getData().getUid()+"");
        edit1.commit();
        startActivity(MainActivity.class);
    }

    @Override
    public void RequestFailure(Login login) {
        ShowToast(login.getMsg());
        System.out.println("失败===========");
    }

    @Override
    public void Failure(Login login) {
        ShowToast(login.getMsg());
        System.out.println("错误===========");
    }
}
