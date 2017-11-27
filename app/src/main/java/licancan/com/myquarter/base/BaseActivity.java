package licancan.com.myquarter.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by robot on 2017/11/15.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    public P presenter;
    public Toast toast;

    public abstract int getLayoutid();
    public abstract P initPresenter();
    public abstract void Creat();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutid());
        presenter=initPresenter();
        Creat();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null)
        {
            //解绑
            presenter.detach();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    //吐司
    public void ShowToast(String msg)
    {
        if(toast==null)
        {
            toast=Toast.makeText(this,msg,Toast.LENGTH_SHORT);
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
        Intent intent = new Intent(this,clz);
        startActivity(intent);
    }
    /**
     * 有参跳转
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz,Bundle bundle){
        Intent intent = new Intent(this,clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
