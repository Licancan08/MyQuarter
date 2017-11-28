package licancan.com.myquarter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;

public class MessageActivity extends BaseActivity implements View.OnClickListener {

    private TextView news_back;

    @Override
    public int getLayoutid() {
        return R.layout.activity_message;
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
        news_back = findViewById(R.id.news_back);
        news_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.news_back:
                finish();
                break;
        }
    }
}
