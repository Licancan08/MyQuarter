package licancan.com.myquarter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import licancan.com.myquarter.R;
import licancan.com.myquarter.activity.FollowActivity;
import licancan.com.myquarter.activity.HideActivity;
import licancan.com.myquarter.activity.MessageActivity;
import licancan.com.myquarter.activity.SearchFriendActivity;
import licancan.com.myquarter.activity.SettingsActivity;
import licancan.com.myquarter.activity.WorksActivity;
import licancan.com.myquarter.base.BaseFragment;

/**
 * Created by robot on 2017/11/14.
 */

public class LeftFragment extends BaseFragment implements View.OnClickListener {

    private ImageView iv_left_shezhi;
    private TextView tv_setting;
    private ImageView iv_left_zuopin;
    private TextView mywork;
    private ImageView iv_left_select;
    private int num=0;
    private ImageView iv_day_night;
    private TextView tv_day_night;
    private RelativeLayout myfollow;
    private RelativeLayout myhide;
    private RelativeLayout searchfriend;
    private RelativeLayout mynews;

    @Override
    public int getLayoutResource() {
        return R.layout.left_menu;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void Creat() {
        initView();
    }

    private void initView() {
        iv_left_shezhi = mView.findViewById(R.id.iv_left_shezhi);
        iv_left_shezhi.setOnClickListener(this);
        tv_setting = mView.findViewById(R.id.tv_setting);
        tv_setting.setOnClickListener(this);
        iv_left_zuopin = mView.findViewById(R.id.iv_left_zuopin);
        iv_left_zuopin.setOnClickListener(this);
        mywork = mView.findViewById(R.id.mywork);
        mywork.setOnClickListener(this);
        iv_left_select = mView.findViewById(R.id.iv_left_select);
        iv_left_select.setOnClickListener(this);
        iv_day_night = mView.findViewById(R.id.iv_day_night);
        tv_day_night = mView.findViewById(R.id.tv_day_night);

        myfollow = mView.findViewById(R.id.myfollow);
        myfollow.setOnClickListener(this);
        myhide = mView.findViewById(R.id.myhide);
        myhide.setOnClickListener(this);
        searchfriend = mView.findViewById(R.id.searchfriend);
        searchfriend.setOnClickListener(this);
        mynews = mView.findViewById(R.id.mynews);
        mynews.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            //设置
            case R.id.iv_left_shezhi:
                startActivity(SettingsActivity.class);
                break;
            case R.id.tv_setting:
                startActivity(SettingsActivity.class);
                break;
            //我的作品
            case R.id.iv_left_zuopin:
                startActivity(WorksActivity.class);
                break;
            case R.id.mywork:
                startActivity(WorksActivity.class);
                break;
            case R.id.iv_left_select:
                num++;
                if(num%2==0)
                {
                    iv_left_select.setImageResource(R.drawable.none);
                    iv_day_night.setImageResource(R.drawable.yueliang1);
                    tv_day_night.setText("日间模式");
                }
                else if(num%2==1)
                {
                    iv_left_select.setImageResource(R.drawable.select);
                    iv_day_night.setImageResource(R.drawable.yueliang2);
                    tv_day_night.setText("夜间模式");
                }
                break;
            //我的关注
            case R.id.myfollow:
                startActivity(FollowActivity.class);
                break;
            //我的收藏
            case R.id.myhide:
                startActivity(HideActivity.class);
                break;
            //搜索好友
            case R.id.searchfriend:
                startActivity(SearchFriendActivity.class);
                break;
            //消息通知
            case R.id.mynews:
                startActivity(MessageActivity.class);
                break;

        }
    }
}
