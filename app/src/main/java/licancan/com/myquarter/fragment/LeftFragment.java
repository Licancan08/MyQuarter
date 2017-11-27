package licancan.com.myquarter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import licancan.com.myquarter.R;
import licancan.com.myquarter.activity.SettingsActivity;
import licancan.com.myquarter.activity.WorksActivity;

/**
 * Created by robot on 2017/11/14.
 */

public class LeftFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView iv_left_shezhi;
    private TextView tv_setting;
    private ImageView iv_left_zuopin;
    private TextView mywork;
    private ImageView iv_left_select;
    private int num=0;
    private ImageView iv_day_night;
    private TextView tv_day_night;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.left_menu,null);
        initView();
        return view;
    }

    private void initView() {
        iv_left_shezhi = view.findViewById(R.id.iv_left_shezhi);
        iv_left_shezhi.setOnClickListener(this);
        tv_setting = view.findViewById(R.id.tv_setting);
        tv_setting.setOnClickListener(this);
        iv_left_zuopin = view.findViewById(R.id.iv_left_zuopin);
        iv_left_zuopin.setOnClickListener(this);
        mywork = view.findViewById(R.id.mywork);
        mywork.setOnClickListener(this);
        iv_left_select = view.findViewById(R.id.iv_left_select);
        iv_left_select.setOnClickListener(this);
        iv_day_night = view.findViewById(R.id.iv_day_night);
        tv_day_night = view.findViewById(R.id.tv_day_night);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            //设置
            case R.id.iv_left_shezhi:
                Intent intent=new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_setting:
                Intent intent1=new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent1);
                break;
            //我的作品
            case R.id.iv_left_zuopin:
                Intent intent2=new Intent(getActivity(), WorksActivity.class);
                startActivity(intent2);
                break;
            case R.id.mywork:
                Intent intent3=new Intent(getActivity(), WorksActivity.class);
                startActivity(intent3);
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

        }
    }
}
