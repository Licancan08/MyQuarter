package licancan.com.myquarter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginStyleActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView otherStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_style);
        initView();
    }

    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        otherStyle = findViewById(R.id.otherStyle);
        otherStyle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.otherStyle:
                Intent intent=new Intent(LoginStyleActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }

    }
}
