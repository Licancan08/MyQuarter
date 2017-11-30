package licancan.com.myquarter.model;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import licancan.com.myquarter.entity.BaseBean;
import licancan.com.myquarter.utils.NetWorkUtils;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robot on 2017/11/29.
 */

public class UpdateNameModel implements IUpdateNameModel {

    private iUpdateName iUpdateName;
    @Override
    public void updateName(String uid, String nickname) {
        new NetWorkUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladapterFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().updateNickName(uid,nickname).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(BaseBean baseBean) {
                        String code = baseBean.code;
                        if("0".equals(code))
                        {
                            iUpdateName.UpdateNameSuccess(baseBean);
                            System.out.println("=======昵称修改成功======"+baseBean.msg);
                        }
                        else if("1".equals(code)){
                            iUpdateName.UpdateNameFailure(baseBean);
                            System.out.println("=======昵称修改失败======"+baseBean.msg);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.println("=======昵称修改请求错误======"+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //暴露在外边的接口
    public void setiUpdateName(UpdateNameModel.iUpdateName iUpdateName) {
        this.iUpdateName = iUpdateName;
    }

    public interface iUpdateName{
        void UpdateNameSuccess(BaseBean baseBean);
        void UpdateNameFailure(BaseBean baseBean);
    }
}
