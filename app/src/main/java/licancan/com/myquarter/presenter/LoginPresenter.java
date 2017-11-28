package licancan.com.myquarter.presenter;

import android.content.Context;

import licancan.com.myquarter.base.BasePresenter;
import licancan.com.myquarter.entity.Login;
import licancan.com.myquarter.model.LoginModel;
import licancan.com.myquarter.view.LoginView;

/**
 * Created by robot on 2017/11/27.
 */

public class LoginPresenter extends BasePresenter<LoginView> implements LoginModel.ilogin{

    private final LoginModel loginModel;
    private Context context;
    public LoginPresenter(LoginView mView, Context context) {
        super(mView);
        this.context=context;
        loginModel = new LoginModel();
        loginModel.setIlogin(this);
    }

    public void login(String mobile,String password)
    {
        loginModel.getLogin(mobile,password);
    }

    @Override
    public void getLoginSuccess(Login login) {
        mView.RequestSuccess(login);
    }

    @Override
    public void getLoginFailure(Login login) {
        mView.RequestFailure(login);
    }
}
