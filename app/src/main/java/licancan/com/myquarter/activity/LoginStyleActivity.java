package licancan.com.myquarter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;

public class LoginStyleActivity extends BaseActivity implements View.OnClickListener {

    private TextView otherStyle;
    private ImageView loginStyle_back;

    @Override
    public int getLayoutid() {
        return R.layout.activity_login_style;
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
        loginStyle_back = findViewById(R.id.loginStyle_back);
        loginStyle_back.setOnClickListener(this);
        otherStyle = findViewById(R.id.otherStyle);
        otherStyle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.loginStyle_back:
                finish();
                break;
            case R.id.otherStyle:
                startActivity(LoginActivity.class);
                break;
        }

    }
}
