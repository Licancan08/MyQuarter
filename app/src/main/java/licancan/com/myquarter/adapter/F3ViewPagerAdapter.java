package licancan.com.myquarter.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import licancan.com.myquarter.fragment.Fragment_1;
import licancan.com.myquarter.fragment.Fragment_2;
import licancan.com.myquarter.fragment.Fragment_3;
import licancan.com.myquarter.fragment.Fragment_4;

/**
 * Created by robot on 2017/11/26.
 */

public class F3ViewPagerAdapter extends FragmentPagerAdapter{
    String[] name={"热门","附近"};
    public F3ViewPagerAdapter(FragmentManager fm) {
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
                fragment=new Fragment_3();
                break;
            case 1:
                fragment=new Fragment_4();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
