package example.com.monidome.gouwuche;

import example.com.monidome.gouwuche.Bean.MessageBean;

/**
 * Created by lenovo on 2018/4/19.
 */

public interface GouWuJieKou {

    interface Iview {
        void onSuccess(Object o);
        void onFailed(Exception e);
        void delSuccess(MessageBean listMessageBean);
    }

    interface BasePresenter {
        void getData(String uid, String pid);
    }

    interface IModel {
        void getData(String uid, String pid);
    }

}
