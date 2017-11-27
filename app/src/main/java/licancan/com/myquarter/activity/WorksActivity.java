package licancan.com.myquarter.activity;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

import licancan.com.myquarter.R;
import licancan.com.myquarter.adapter.F1ViewPagerAdapter;
import licancan.com.myquarter.adapter.MyWorkViewPagerAdapter;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;

public class WorksActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout work_tabLayout;
    private ViewPager work_viewPager;
    private MyWorkViewPagerAdapter adapter;
    private TextView work_back;

    @Override
    public int getLayoutid() {
        return R.layout.activity_works;
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
        work_tabLayout = findViewById(R.id.work_tabLayout);
        work_viewPager = findViewById(R.id.work_viewPager);
        adapter = new MyWorkViewPagerAdapter(getSupportFragmentManager());
        work_tabLayout.setupWithViewPager(work_viewPager);
        work_viewPager.setAdapter(adapter);
        MysetIndicator(work_tabLayout,50,50);

        work_back = findViewById(R.id.work_back);
        work_back.setOnClickListener(this);
    }

    //设置下划线的长度
    public void MysetIndicator(TabLayout tabs,int leftDip,int rightDip){
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.work_back:
                finish();
                break;
        }
    }
}
