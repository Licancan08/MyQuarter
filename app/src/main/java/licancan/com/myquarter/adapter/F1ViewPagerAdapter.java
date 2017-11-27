package licancan.com.myquarter.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import licancan.com.myquarter.fragment.Fragment_1;
import licancan.com.myquarter.fragment.Fragment_2;

/**
 * Created by robot on 2017/11/25.
 */

public class F1ViewPagerAdapter extends FragmentPagerAdapter {

    String[] name={"热门","关注"};
    public F1ViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return name[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position)
        {
            case 0:
                fragment=new Fragment_1();
                break;
            case 1:
                fragment=new Fragment_2();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
