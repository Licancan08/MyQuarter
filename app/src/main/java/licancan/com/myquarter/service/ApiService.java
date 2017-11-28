package licancan.com.myquarter.service;

import java.util.Map;

import io.reactivex.Observable;
import licancan.com.myquarter.entity.Login;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by robot on 2017/11/27.
 */

public interface ApiService {
    //注册
    @POST("user/reg")
    @FormUrlEncoded
    Observable<ResponseBody> regist(@FieldMap Map<String,String> map);
    //登录
    @POST("user/login")
    @FormUrlEncoded
    Observable<Login> login(@Field("mobile") String mobile, @Field("password") String password);
    //上传头像

    //获取用户信息
    @POST("user/getUserInfo")
    @FormUrlEncoded
    Observable<ResponseBody> getUserInfo(@FieldMap Map<String,String> map);
}
