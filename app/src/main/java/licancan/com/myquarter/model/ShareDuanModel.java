package licancan.com.myquarter.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import licancan.com.myquarter.activity.ShareDuanziSuccessActivity;
import licancan.com.myquarter.entity.BaseBean;
import licancan.com.myquarter.entity.Duanzi;
import licancan.com.myquarter.entity.Login;
import licancan.com.myquarter.utils.NetWorkUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robot on 2017/11/28.
 */

public class ShareDuanModel implements IShareDuanModel{
    private iShareDuanzi iShareDuanzi;
    @Override
    public void shareDuanzi(final Context context, String uid, String content, List<File> files) {
        System.out.println(" = ++++++++++++++++" );
        List<MultipartBody.Part> partList=new ArrayList<>();
        for (File file : files) {
            RequestBody requestBody=RequestBody.create(MediaType.parse("image/*"),file);
            MultipartBody.Part part=MultipartBody.Part.createFormData("jokeFiles",file.getName(),requestBody);
            partList.add(part);
        }
        MultipartBody.Part uidPart=MultipartBody.Part.createFormData("uid",uid);
        MultipartBody.Part uidPart1=MultipartBody.Part.createFormData("source","android");
        MultipartBody.Part uidPart2=MultipartBody.Part.createFormData("appVersion","1");
        //得到token值
        SharedPreferences mytoken=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        String token=mytoken.getString("token","");
        MultipartBody.Part uidPart3=MultipartBody.Part.createFormData("token",token);

        MultipartBody.Part contentPart=MultipartBody.Part.createFormData("content",content);
        new NetWorkUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladapterFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().publishJoke(uidPart,contentPart,uidPart1,uidPart2,uidPart3,partList).enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                String code = response.body().code;
                if("0".equals(code))
                {
                    Intent intent=new Intent(context,ShareDuanziSuccessActivity.class);
                    context.startActivity(intent);
                    iShareDuanzi.ShareDuanziSuccess(response.body().msg);
                    Toast.makeText(context, "图片发表成功", Toast.LENGTH_SHORT).show();
                    System.out.println("=======段子发表成功duanzi======"+response.body().msg);
                }
                else if("1".equals(code)){
                    iShareDuanzi.ShareDuanziFailure(response.body().msg);
                    System.out.println("=======段子发表失败duanzi======"+response.body().msg);
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {
                System.out.println("=======段子发表失败duanzi======"+t.getMessage());
            }
        });
    }

    //段子暴露在外边的接口
    public void setiShareDuanzi(ShareDuanModel.iShareDuanzi iShareDuanzi) {
        this.iShareDuanzi = iShareDuanzi;
    }
    public interface iShareDuanzi{
        void ShareDuanziSuccess(String BaseBean);
        void ShareDuanziFailure(String BaseBean);
    }
}
