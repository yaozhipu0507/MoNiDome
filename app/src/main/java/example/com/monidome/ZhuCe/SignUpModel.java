package example.com.monidome.ZhuCe;

import java.util.HashMap;
import java.util.Map;
import example.com.monidome.Api.ApiServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/4/29.
 */

public class SignUpModel implements ZhuCeJieKou.ISignUpModel {
    @Override
    public void RequestData(String url, String username, String password, String password_confirm, final ZhuCeJieKou.OnRequestListener onRequestListener) {
        Map<String,String> map = new HashMap<>();
        map.put("mobile",username);
        map.put("password",password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<SignUpBean> getsup = apiServer.getsup("user/reg", map);
        getsup.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignUpBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(SignUpBean signUpBean) {
                        if(signUpBean.getCode()==""+0){
                            onRequestListener.OnSuccess();
                        }else{
                            onRequestListener.OnError(signUpBean.getMsg());
                        }
                    }

                });
    }
}
