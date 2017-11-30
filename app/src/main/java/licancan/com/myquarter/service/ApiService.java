package licancan.com.myquarter.service;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import licancan.com.myquarter.entity.BaseBean;
import licancan.com.myquarter.entity.Duanzi;
import licancan.com.myquarter.entity.Login;
import licancan.com.myquarter.entity.Regist;
import licancan.com.myquarter.entity.User;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by robot on 2017/11/27.
 */

public interface ApiService {
    //注册
    @POST("user/reg")
    @FormUrlEncoded
    Observable<Regist> regist(@Field("mobile") String mobile, @Field("password") String password);
    //登录
    @POST("user/login")
    @FormUrlEncoded
    Observable<Login> login(@Field("mobile") String mobile, @Field("password") String password);

    //上传头像
    @POST("file/upload")
    @Multipart
    Observable<BaseBean> upload(@Part MultipartBody.Part uid, @Part MultipartBody.Part file);

    //修改昵称
    @POST("user/updateNickName")
    @FormUrlEncoded
    Observable<BaseBean> updateNickName(@Field("uid") String uid, @Field("nickname") String nickname);

    //获取用户信息
    @POST("user/getUserInfo")
    @FormUrlEncoded
    Observable<User> getUserInfo(@Field("uid") String uid);


    //发表段子
    @POST("quarter/publishJoke")
    @Multipart
    Call<BaseBean> publishJoke(@Part MultipartBody.Part uid, @Part MultipartBody.Part content, @Part MultipartBody.Part content1, @Part MultipartBody.Part content2, @Part MultipartBody.Part content3, @Part() List<MultipartBody.Part> jokeFiles);

    //段子
    @POST("quarter/getJokes")
    @FormUrlEncoded
    Observable<Duanzi> getJokes(@Field("page") String page);

}
