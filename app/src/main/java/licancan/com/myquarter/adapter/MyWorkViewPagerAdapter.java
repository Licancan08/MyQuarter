package licancan.com.myquarter.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import licancan.com.myquarter.fragment.Fragment_1;
import licancan.com.myquarter.fragment.Fragment_2;
import licancan.com.myquarter.fragment.Fragment_work1;
import licancan.com.myquarter.fragment.Fragment_work2;

/**
 * Created by robot on 2017/11/27.
 */

public class MyWorkViewPagerAdapter extends FragmentPagerAdapter {
    String[] name={"本地作品","已上传"};
    public MyWorkViewPagerAdapter(FragmentManager fm) {
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
                fragment=new Fragment_work1();
                break;
            case 1:
                fragment=new Fragment_work2();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
