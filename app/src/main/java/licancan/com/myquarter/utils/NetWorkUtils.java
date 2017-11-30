package licancan.com.myquarter.utils;

import android.os.Build;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import licancan.com.myquarter.api.Api;
import licancan.com.myquarter.service.ApiService;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robot on 2017/11/15.
 */

public class NetWorkUtils {
    private ApiService apiService;
    public NetWorkUtils(ApiService apiService) {
        this.apiService = apiService;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public static class Builder{
        private List<Converter.Factory> converterFactories=new ArrayList<>();
        private List<CallAdapter.Factory> calladapterFactories=new ArrayList<>();
        private NetWorkUtils netWorkUtils;

        public Builder addConverterFactory(Converter.Factory factory)
        {
            converterFactories.add(factory);
            return this;
        }
        public Builder addCalladapterFactory(CallAdapter.Factory factory)
        {
            calladapterFactories.add(factory);
            return this;
        }

        public NetWorkUtils build()
        {
            OkHttpClient client=new OkHttpClient.Builder().addInterceptor(new MyInterceptor()).build();
            Retrofit.Builder builder=new Retrofit.Builder().client(client).baseUrl(Api.URL);
            if(converterFactories.size()==0)
            {
                builder.addConverterFactory(GsonConverterFactory.create());
            }
            else{
                for (Converter.Factory converterFactory : converterFactories) {
                    builder.addConverterFactory(converterFactory);
                }
            }
            if(calladapterFactories.size()==0)
            {
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            }
            else{
                for (CallAdapter.Factory calladapterFactory : calladapterFactories) {
                    builder.addCallAdapterFactory(calladapterFactory);
                }
            }
            ApiService apiService=builder.build().create(ApiService.class);
            netWorkUtils = new NetWorkUtils(apiService);
            return netWorkUtils;
        }
    }
}
