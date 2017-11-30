package licancan.com.myquarter.model;

import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import licancan.com.myquarter.activity.MainActivity;
import licancan.com.myquarter.api.Api;
import licancan.com.myquarter.entity.Duanzi;
import licancan.com.myquarter.entity.Login;
import licancan.com.myquarter.service.ApiService;
import licancan.com.myquarter.utils.MyInterceptor;
import licancan.com.myquarter.utils.NetWorkUtils;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robot on 2017/11/27.
 */

public class LoginModel implements ILoginModel{

    private ilogin ilogin;
    private iDuanzi iDuanzi;
    @Override
    public void getLogin(String mobile, String password) {
        System.out.println("model===========");



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
                            System.out.println(login.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("error============"+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getDuanzi(String page) {
        new NetWorkUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladapterFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getJokes(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Duanzi>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Duanzi duanzi) {
                        String code = duanzi.getCode();
                        if("0".equals(code))
                        {
                            iDuanzi.getDuanziSuccess(duanzi);
                            System.out.println(duanzi.getMsg() +"段子列表请求成功++++++++++++++");

                        }
                        else if("1".equals(code))
                        {
                            iDuanzi.getDuanziFailure(duanzi);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //登录暴露在外边的接口
    public void setIlogin(LoginModel.ilogin ilogin) {
        this.ilogin = ilogin;
    }
    public interface ilogin{
        void getLoginSuccess(Login login);
        void getLoginFailure(Login login);
    }
    //段子暴露在外边的接口
    public void setiDuanzi(LoginModel.iDuanzi iDuanzi) {
        this.iDuanzi = iDuanzi;
    }
    public interface iDuanzi{
        void getDuanziSuccess(Duanzi duanzi);
        void getDuanziFailure(Duanzi duanzi);
    }
}
