package licancan.com.myquarter.presenter;

import android.content.Context;

import licancan.com.myquarter.base.BasePresenter;
import licancan.com.myquarter.entity.Regist;
import licancan.com.myquarter.model.RegistModel;
import licancan.com.myquarter.view.RegistView;

/**
 * Created by robot on 2017/11/27.
 */

public class RegistPresenter extends BasePresenter<RegistView> implements RegistModel.iRegist{

    Context context;
    private RegistModel registModel;
    public RegistPresenter(RegistView mView, Context context) {
        super(mView);
        this.context=context;
        registModel=new RegistModel();
        registModel.setiRegist(this);
    }

    public void regist(String mobile,String password)
    {
        registModel.getRegist(mobile,password);
    }

    @Override
    public void getRegistSuccess(Regist regist) {
        mView.RequestSuccess(regist);
    }

    @Override
    public void getRegistFailure(Regist regist) {
        mView.RequestFailure(regist);
    }
}
