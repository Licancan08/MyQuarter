package licancan.com.myquarter.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;

import licancan.com.myquarter.R;

/**
 * Created by robot on 2017/11/25.
 */

public class Fragment_1  extends Fragment{


    private View view;
    private XBanner xBanner;
    private ArrayList list_xbanner;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_1,null);
        initView();
        initXBanner();
        return view;
    }



    private void initView() {
        xBanner = view.findViewById(R.id.xBanner);
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
}
