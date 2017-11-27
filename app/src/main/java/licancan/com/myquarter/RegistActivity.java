package licancan.com.myquarter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView hava_regist;
    private ImageView regist_back;
    private TextView regist_onlylogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
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
                Intent intent=new Intent(RegistActivity.this,LoginActivity.class);
                startActivity(intent) ;
                break;
            case R.id.regist_back:
                finish();
                break;
            case R.id.regist_onlylogin:
                //游客登录
                Intent intent1=new Intent(RegistActivity.this,LoginActivity.class);
                startActivity(intent1);
                break;
        }

    }
}
