package licancan.com.myquarter.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import java.util.ArrayList;
import licancan.com.myquarter.R;
import licancan.com.myquarter.adapter.F1XRecyclerViewAdapter;
import licancan.com.myquarter.base.BaseFragment;

/**
 * Created by robot on 2017/11/25.
 */

public class Fragment_1  extends BaseFragment{

    private XBanner xBanner;
    private ArrayList list_xbanner;
    private XRecyclerView f1_xRecyclerView;
    private LinearLayoutManager layoutManager;
    private F1XRecyclerViewAdapter f1Adapter;
    private View view;
//    private PlayerView play;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_1;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void Creat() {
        initView();
        initData();
        initXBanner();
    }


    private void initView() {
        //XRecyclerView 管理器
        f1_xRecyclerView = mView.findViewById(R.id.f1_xRecyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        f1_xRecyclerView.setLayoutManager(layoutManager);

        //添加头部
        view = View.inflate(getActivity(), R.layout.view,null);
    }
    private void initData() {
        //添加适配器
        f1Adapter = new F1XRecyclerViewAdapter(getActivity());
        f1_xRecyclerView.setAdapter(f1Adapter);

        f1_xRecyclerView.addHeaderView(view);
        xBanner = view.findViewById(R.id.xBanner);
        //允许刷新和加载
        f1_xRecyclerView.setPullRefreshEnabled(true);
        f1_xRecyclerView.setLoadingMoreEnabled(true);
        //刷新 加载
        f1_xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                f1_xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                f1_xRecyclerView.loadMoreComplete();
            }
        });

        /*//播放视频
        String url = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/local/adc.mp4";
        play = new PlayerView(getActivity())
                .setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource(url);
        play.startPlay();*/

    }

    /**
     * XBanner图片轮播
     */
    private void initXBanner() {
        list_xbanner = new ArrayList<>();
        list_xbanner.add(R.drawable.a);
        list_xbanner.add(R.drawable.e);
        list_xbanner.add(R.drawable.c);
        list_xbanner.add(R.drawable.d);

        xBanner.setData(list_xbanner,null);
        xBanner.setPoinstPosition(XBanner.RIGHT);
        xBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position)
            {
                Glide.with(getActivity()).load(list_xbanner.get(position)).into((ImageView) view);
            }
        });
    }

/*    @Override
    public void onStop() {
        super.onStop();
        play.stopPlay();
    }*/
}
