package example.com.monidome.ShouYe;

import android.content.Context;
import java.util.List;
import example.com.monidome.ShouYe.bean.MyRvMiddleBean;
import example.com.monidome.ShouYe.bean.ShouYeBean;


/**
 * Created by lenovo on 2018/4/27.
 */

public interface ShouYeJieKou {

    interface iView {
        void shouBanner(ShouYeBean shouYeBean);
        void middleView(List<MyRvMiddleBean.DataBean> list);
        void shouYeRV(ShouYeBean shouYeBean);
    }

    interface IShouYeModel {
        void getBannerUrl(Context context, OnNetListener<ShouYeBean> onNetListener);
        void getMiddleViewUrl(Context context, OnNetListener<MyRvMiddleBean> onNetListener);
    }

    interface OnNetListener<T>{
        //成功回调
        void onSuccess(T t);
        //失败回调
        void onFailure(Exception e);

    }

}
