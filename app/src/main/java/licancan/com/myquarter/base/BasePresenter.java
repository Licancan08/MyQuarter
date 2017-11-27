package licancan.com.myquarter.base;

/**
 * Created by robot on 2017/11/27.
 */

public class BasePresenter<V> {
    public V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
    }

    //解绑
    public void detach()
    {
       this.mView=null;
    }
}
