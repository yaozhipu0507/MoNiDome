package example.com.monidome.utils;

import example.com.monidome.Api.Api;
import example.com.monidome.Api.ApiServer;
import example.com.monidome.gouwuche.LoggingInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2018/4/19.
 */


public class RetrofitUtils {
    private static volatile RetrofitUtils instance;
    private ApiServer apiService;
    private OkHttpClient client = new OkHttpClient
            .Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();
    private RetrofitUtils(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.UserURL)
                .build();
        apiService = retrofit.create(ApiServer.class);
    }
    public static RetrofitUtils getInstance(){
        if (null==instance){
            synchronized (RetrofitUtils.class){
                if (instance==null){
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }
    public ApiServer getApiService(){
        return apiService;
    }
}
