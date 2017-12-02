package licancan.com.myquarter.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import licancan.com.myquarter.R;
import licancan.com.myquarter.activity.FollowActivity;
import licancan.com.myquarter.activity.HideActivity;
import licancan.com.myquarter.activity.LoginStyleActivity;
import licancan.com.myquarter.activity.MessageActivity;
import licancan.com.myquarter.activity.SearchFriendActivity;
import licancan.com.myquarter.activity.SettingsActivity;
import licancan.com.myquarter.activity.UpdateActivity;
import licancan.com.myquarter.activity.WorksActivity;
import licancan.com.myquarter.base.BaseFragment;
import licancan.com.myquarter.entity.Login;
import licancan.com.myquarter.entity.User;
import licancan.com.myquarter.utils.NetWorkUtils;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private ImageView iv2_touxaing;
    private TextView tv_nickName;
    private String nickname;
    private String icon;

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

    /**
     * 获取用户信息
     */
    private void initData(String uid) {
        new NetWorkUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladapterFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getUserInfo(uid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        System.out.println("user=============="+user.getMsg());
                        String code = user.getCode();
                        User.DataBean data = user.getData();
                        nickname = data.getNickname();
                        tv_nickName.setText(nickname);
                        //得到图片
                        icon = data.getIcon();
                        System.out.println("icon========"+icon);
                        Uri url=Uri.parse(icon);
                        iv2_touxaing.setImageURI(url);
                        if("0".equals(code))
                        {
                            System.out.println("个人信息获取成功======="+user.getMsg());
                        }
                        else if("1".equals(code))
                        {
                            System.out.println("个人信息获取失败======="+user.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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

        iv2_touxaing = mView.findViewById(R.id.iv2_touxaing);
        iv2_touxaing.setOnClickListener(this);
        tv_nickName = mView.findViewById(R.id.tv_nickName);
        tv_nickName.setOnClickListener(this);
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
            case R.id.iv2_touxaing:
                startActivity(LoginStyleActivity.class);
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
            //修改昵称  头像
            case R.id.tv_nickName:
                Intent intent=new Intent(getActivity(),UpdateActivity.class);
                intent.putExtra("name",nickname);
                intent.putExtra("icon",icon);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences uisSp=getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        String uid = uisSp.getString("uid", "148");
        initData(uid);
    }
}
