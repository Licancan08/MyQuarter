package licancan.com.myquarter.model;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import licancan.com.myquarter.entity.Regist;
import licancan.com.myquarter.utils.NetWorkUtils;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robot on 2017/11/27.
 */

public class RegistModel implements IRegistModel{

    private iRegist iRegist;
    @Override
    public void getRegist(String mobile, String password) {
        new NetWorkUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladapterFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().regist(mobile,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Regist>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Regist regist) {
                        String code = regist.getCode();
                        if("0".equals(code))
                        {
                            iRegist.getRegistSuccess(regist);
                            System.out.println(regist.getMsg() +"注册成功++++++++++++++");
                        }else if("1".equals(code))
                        {
                            iRegist.getRegistFailure(regist);
                            System.out.println(regist.getMsg() +"注册失败++++++++++++++");
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

    public void setiRegist(RegistModel.iRegist iRegist) {
        this.iRegist = iRegist;
    }

    public interface iRegist{
        void getRegistSuccess(Regist regist);
        void getRegistFailure(Regist regist);

    }

}
