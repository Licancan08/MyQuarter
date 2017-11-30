package licancan.com.myquarter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import licancan.com.myquarter.R;
import licancan.com.myquarter.adapter.XRecyclerDuanAdapter;
import licancan.com.myquarter.base.BaseFragment;
import licancan.com.myquarter.entity.Duanzi;
import licancan.com.myquarter.presenter.DuanziPresenter;
import licancan.com.myquarter.view.DuanziView;

/**
 * Created by robot on 2017/11/14.
 */

public class Fragment2 extends BaseFragment implements DuanziView {

    private XRecyclerView xRecyclerView;
    private DuanziPresenter presenter;
    private XRecyclerDuanAdapter duanAdapter;
    private LinearLayoutManager layoutManager;
    private int page=1;
    private List<Duanzi.DataBean> data;

    @Override
    public void initPresenter() {

    }

    @Override
    public void Creat() {
        initView();
        initData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        data=new ArrayList<>();
        xRecyclerView = mView.findViewById(R.id.xRecyclerView);
        /*layoutManager = new LinearLayoutManager(getActivity());
        xRecyclerView.setLayoutManager(layoutManager);*/
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        data.clear();
        presenter.duanzi("1");
    }

    /**
     * 初始化数据
     */
    private void initData() {
        presenter = new DuanziPresenter(this,getActivity());
        presenter.duanzi("1");

    }


    @Override
    public int getLayoutResource() {
        return R.layout.fragment2;
    }

    @Override
    public void RequestSuccess(Duanzi duanzi) {
        data.addAll(duanzi.getData());
        for (Duanzi.DataBean datum : data) {
            System.out.println("-----item-----"+datum.getContent()+"  "+datum.getCreateTime()+"  "+datum.getUser().getNickname());
        }

        //关联适配器
        if(duanAdapter==null)
        {
            duanAdapter = new XRecyclerDuanAdapter(getActivity(), data);
            xRecyclerView.setAdapter(duanAdapter);
            layoutManager = new LinearLayoutManager(getActivity());
            xRecyclerView.setLayoutManager(layoutManager);
        }
        else {
            duanAdapter.notifyDataSetChanged();
        }

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.duanzi(page+"");
                xRecyclerView.refreshComplete();
                ShowToast("刷新成功");
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.duanzi(page+"");
                xRecyclerView.loadMoreComplete();
                ShowToast("加载成功");
                System.out.println("page------------"+page);
            }
        });

    }

    @Override
    public void RequestFailure(Duanzi duanzi) {
        System.out.println("duanzi==========="+duanzi.getMsg());
    }

    @Override
    public void Failure(Duanzi duanzi) {
        System.out.println("duanzi==========="+duanzi.getMsg());
    }
}
