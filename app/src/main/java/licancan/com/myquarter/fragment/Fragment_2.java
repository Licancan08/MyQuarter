package licancan.com.myquarter.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import licancan.com.myquarter.R;
import licancan.com.myquarter.adapter.F1XRecyclerViewAdapter;
import licancan.com.myquarter.base.BaseFragment;

/**
 * Created by robot on 2017/11/25.
 */

public class Fragment_2 extends BaseFragment {

    private LinearLayoutManager layoutManager;
    private XRecyclerView f2_xRecyclerView;
    private F1XRecyclerViewAdapter f2Adapter;
    private View view2;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_2;
    }

    @Override
    public void initPresenter() {
        initView();
        initData();
    }


    private void initView() {
        //XRecyclerView 管理器
        f2_xRecyclerView = mView.findViewById(R.id.f2_xRecyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        f2_xRecyclerView.setLayoutManager(layoutManager);

        //添加头部
        view2 = View.inflate(getActivity(), R.layout.view2,null);
    }

    private void initData() {

        f2_xRecyclerView.addHeaderView(view2);
        ImageView img=view2.findViewById(R.id.img);
        //添加适配器
        f2Adapter = new F1XRecyclerViewAdapter(getActivity());
        f2_xRecyclerView.setAdapter(f2Adapter);

        //允许刷新和加载
        f2_xRecyclerView.setPullRefreshEnabled(true);
        f2_xRecyclerView.setLoadingMoreEnabled(true);
        //刷新 加载
        f2_xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                f2_xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                f2_xRecyclerView.loadMoreComplete();
            }
        });
    }


    @Override
    public void Creat() {

    }
}
