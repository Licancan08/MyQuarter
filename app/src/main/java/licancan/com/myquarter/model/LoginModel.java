package licancan.com.myquarter.model;

import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import licancan.com.myquarter.activity.MainActivity;
import licancan.com.myquarter.entity.Login;
import licancan.com.myquarter.service.ApiService;
import licancan.com.myquarter.utils.NetWorkUtils;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robot on 2017/11/27.
 */

public class LoginModel implements ILoginModel{

    private ilogin ilogin;
    @Override
    public void getLogin(String mobile, String password) {
        new NetWorkUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladapterFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().login(mobile,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Login>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Login login) {
                        String code = login.getCode();
                        if("0".equals(code))
                        {
                            ilogin.getLoginSuccess(login);
                            //打印登录成功
                            System.out.println("-------"+login.getMsg()+login.getData().getNickname());

                        }
                        else if("1".equals(code)){
                            ilogin.getLoginFailure(login);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void setIlogin(LoginModel.ilogin ilogin) {
        this.ilogin = ilogin;
    }

    public interface ilogin{
        void getLoginSuccess(Login login);
        void getLoginFailure(Login login);
    }
}
