package licancan.com.myquarter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;

public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    private TextView setting_back;

    @Override
    public int getLayoutid() {
        return R.layout.activity_settings;
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
        setting_back = findViewById(R.id.setting_back);
        setting_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.setting_back:
                finish();
                break;
        }
    }
}
