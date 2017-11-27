package licancan.com.myquarter.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kson.slidingmenu.SlidingMenu;

import licancan.com.myquarter.R;
import licancan.com.myquarter.fragment.Fragment1;
import licancan.com.myquarter.fragment.Fragment2;
import licancan.com.myquarter.fragment.Fragment3;
import licancan.com.myquarter.fragment.LeftFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;


   private final Handler mHandler=new Handler();
    private Fragment1 f1;
    private Fragment2 f2;
    private Fragment3 f3;
    private TextView tv_title;
    private LeftFragment leftFragment;
    private SlidingMenu slidingMenu;
    private ImageView iv_head;
    private ImageView write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //模拟内存泄漏
        /*mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },3*60*1000);
        finish();*/
        initView();
        initMenu();
    }


    private void initView() {
        write = findViewById(R.id.write);
        write.setOnClickListener(this);
        leftFragment = new LeftFragment();
        iv_head = findViewById(R.id.iv_head);
        iv_head.setOnClickListener(this);
        tv_title = findViewById(R.id.tv_title);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        f1 = new Fragment1();
        f2 = new Fragment2();
        f3 = new Fragment3();
        //添加Fragment
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, f1).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, f2).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, f3).commit();
        //设置默认展示
        button1.setChecked(true);
        button2.setChecked(false);
        button3.setChecked(false);
        button1.setTextColor(Color.parseColor("#03B1F6"));
        button2.setTextColor(Color.parseColor("#C5C5D5"));
        button3.setTextColor(Color.parseColor("#C5C5D5"));
        getSupportFragmentManager().beginTransaction().show(f1).commit();
        getSupportFragmentManager().beginTransaction().hide(f2).commit();
        getSupportFragmentManager().beginTransaction().hide(f3).commit();



    }

    /**
     * 侧拉菜单
     */
    private void initMenu() {
        slidingMenu = new SlidingMenu(MainActivity.this);
        slidingMenu.setMenu(R.layout.left_fragment);//添加左菜单
        getSupportFragmentManager().beginTransaction().replace(R.id.left_menu_id,leftFragment).commit();
        //设置属性
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu.setBehindOffsetRes(R.dimen.BehindOffsetRes);
        slidingMenu.attachToActivity(MainActivity.this,SlidingMenu.SLIDING_CONTENT);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button1:
                tv_title.setText("推荐");
                button1.setChecked(true);
                button2.setChecked(false);
                button3.setChecked(false);
                button1.setTextColor(Color.parseColor("#03B1F6"));
                button2.setTextColor(Color.parseColor("#C5C5D5"));
                button3.setTextColor(Color.parseColor("#C5C5D5"));
                getSupportFragmentManager().beginTransaction().show(f1).commit();
                getSupportFragmentManager().beginTransaction().hide(f2).commit();
                getSupportFragmentManager().beginTransaction().hide(f3).commit();

                break;
            case R.id.button2:
                tv_title.setText("段子");
                button2.setChecked(true);
                button1.setChecked(false);
                button3.setChecked(false);
                button2.setTextColor(Color.parseColor("#03B1F6"));
                button1.setTextColor(Color.parseColor("#C5C5D5"));
                button3.setTextColor(Color.parseColor("#C5C5D5"));
                getSupportFragmentManager().beginTransaction().show(f2).commit();
                getSupportFragmentManager().beginTransaction().hide(f1).commit();
                getSupportFragmentManager().beginTransaction().hide(f3).commit();
                break;
            case R.id.button3:
                tv_title.setText("视频");
                button3.setChecked(true);
                button2.setChecked(false);
                button1.setChecked(false);
                button3.setTextColor(Color.parseColor("#03B1F6"));
                button2.setTextColor(Color.parseColor("#C5C5D5"));
                button1.setTextColor(Color.parseColor("#C5C5D5"));
                getSupportFragmentManager().beginTransaction().show(f3).commit();
                getSupportFragmentManager().beginTransaction().hide(f2).commit();
                getSupportFragmentManager().beginTransaction().hide(f1).commit();
                break;
            case R.id.iv_head:
                slidingMenu.showMenu();
                break;
            case R.id.write:
                Intent intent=new Intent(MainActivity.this,WriteActivity.class);
                startActivity(intent);
                break;
        }
    }
}
