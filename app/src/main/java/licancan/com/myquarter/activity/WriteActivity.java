package licancan.com.myquarter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;

public class WriteActivity extends BaseActivity implements View.OnClickListener {

    private TextView back;
    private ImageView iv_shipin;
    private ImageView iv_duanzi;

    @Override
    public int getLayoutid() {
        return R.layout.activity_write;
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
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        iv_shipin = findViewById(R.id.iv_shipin);
        iv_shipin.setOnClickListener(this);
        iv_duanzi = findViewById(R.id.iv_duanzi);
        iv_duanzi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.back:
                finish();
                break;
            //视频
            case R.id.iv_shipin:
                startActivity(VedioActivity.class);
                break;
            //段子
            case R.id.iv_duanzi:
                startActivity(DuanziActivity.class);
                break;
        }
    }
}
