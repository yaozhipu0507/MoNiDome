package example.com.monidome.Api;

import java.util.List;
import java.util.Map;
import example.com.monidome.DengLu.LoginBean;
import example.com.monidome.ZhuCe.SignUpBean;
import example.com.monidome.gouwuche.Bean.DatasBean;
import example.com.monidome.gouwuche.Bean.MessageBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by lenovo on 2018/4/29.
 */

public interface ApiServer {
    @POST
    rx.Observable<SignUpBean> getsup(@Url String url, @QueryMap Map<String, String> map);

    @POST
    rx.Observable<LoginBean> getlg(@Url String url, @QueryMap Map<String, String> map);


    @GET("product/getCarts")
    Flowable<MessageBean<List<DatasBean>>> getDatas(@Query("uid") String uid);
    @GET("product/deleteCart")
    Flowable<MessageBean> deleteData(@Query("uid") String uid, @Query("pid") String pid);
}
