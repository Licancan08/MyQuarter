package licancan.com.myquarter.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import licancan.com.myquarter.utils.TUtil;

/**
 * Created by robot on 2017/11/28.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    public P presenter;
    public View mView;
    private Toast toast;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(getLayoutResource(),container,false);
        presenter= TUtil.getT(this,0);
        initPresenter();
        Creat();
        return mView;
    }

    public abstract int getLayoutResource();
    public abstract void initPresenter();
    public abstract void Creat();
    //吐司
    public void ShowToast(String msg)
    {
        if(toast==null)
        {
            toast = Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT);
        }else{
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 无参跳转
     * @param clz
     */
    public void startActivity(Class<?> clz){
        Intent intent = new Intent(getActivity(),clz);
        startActivity(intent);
    }
    /**
     * 有参跳转
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz,Bundle bundle){
        Intent intent = new Intent(getActivity(),clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
