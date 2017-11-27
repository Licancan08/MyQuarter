package licancan.com.myquarter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ForgetActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView forget_back;
    private TextView for_already_regist;
    private Button but_next;
    private TextView for_oneylogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initView();
    }

    private void initView() {
        forget_back = findViewById(R.id.forget_back);
        forget_back.setOnClickListener(this);
        for_already_regist = findViewById(R.id.for_already_regist);
        for_already_regist.setOnClickListener(this);
        but_next = findViewById(R.id.but_next);
        but_next.setOnClickListener(this);
        for_oneylogin = findViewById(R.id.for_oneylogin);
        for_oneylogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.forget_back:
                finish();
                break;
            case R.id.for_already_regist:
                //已经有了账号   进入登录界面
                Intent intent=new Intent(ForgetActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.but_next:
                //忘记密码  进行下一步的修改
                Intent intent1=new Intent(ForgetActivity.this,Forget_TwoActivity.class);
                startActivity(intent1);
                break;
            case R.id.for_oneylogin:
                Intent intent2=new Intent(ForgetActivity.this,MainActivity.class);
                startActivity(intent2);
                break;
        }

    }
}
