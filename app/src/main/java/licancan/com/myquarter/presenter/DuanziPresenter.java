package licancan.com.myquarter.presenter;

import android.content.Context;

import licancan.com.myquarter.base.BasePresenter;
import licancan.com.myquarter.entity.Duanzi;
import licancan.com.myquarter.model.LoginModel;
import licancan.com.myquarter.view.DuanziView;
import licancan.com.myquarter.view.LoginView;

/**
 * Created by robot on 2017/11/28.
 */

public class DuanziPresenter extends BasePresenter<DuanziView> implements LoginModel.iDuanzi {

    Context context;
    private final LoginModel loginModel;

    public DuanziPresenter(DuanziView mView,Context context) {
        super(mView);
        this.context=context;
        loginModel = new LoginModel();
        loginModel.setiDuanzi(this);
    }

    public void duanzi(String page)
    {
        loginModel.getDuanzi(page);
    }

    @Override
    public void getDuanziSuccess(Duanzi duanzi) {
        mView.RequestSuccess(duanzi);
    }

    @Override
    public void getDuanziFailure(Duanzi duanzi) {
        mView.RequestFailure(duanzi);
    }
}
