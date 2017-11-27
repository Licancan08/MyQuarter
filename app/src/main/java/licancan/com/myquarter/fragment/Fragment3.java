package licancan.com.myquarter.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import licancan.com.myquarter.R;
import licancan.com.myquarter.adapter.F1ViewPagerAdapter;
import licancan.com.myquarter.adapter.F3ViewPagerAdapter;

/**
 * Created by robot on 2017/11/14.
 */

public class Fragment3 extends Fragment {

    private View view;
    private F3ViewPagerAdapter adapter;
    private TabLayout f3_tabLayout;
    private ViewPager f3_viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment3,null);
        initView();
        return view;
    }
    private void initView() {
        f3_tabLayout = view.findViewById(R.id.f3_tabLayout);
        f3_viewPager = view.findViewById(R.id.f3_viewPager);

        adapter = new F3ViewPagerAdapter(getActivity().getSupportFragmentManager());
        f3_tabLayout.setupWithViewPager(f3_viewPager);
        f3_viewPager.setAdapter(adapter);
        MysetIndicator(f3_tabLayout,50,50);
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
}
