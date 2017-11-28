package licancan.com.myquarter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;

public class VedioActivity extends BaseActivity implements View.OnClickListener {

    private TextView vedio_back;

    @Override
    public int getLayoutid() {
        return R.layout.activity_vedio;
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
        vedio_back = findViewById(R.id.vedio_back);
        vedio_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.vedio_back:
                finish();
                break;
        }
    }
}
