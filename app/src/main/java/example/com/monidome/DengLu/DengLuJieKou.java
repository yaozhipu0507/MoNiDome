package example.com.monidome.DengLu;

/**
 * Created by lenovo on 2018/4/29.
 */

public interface DengLuJieKou {
    interface ILoginView {
        void showLogin(LoginBean.DataBean db);
        void showerroe(String e);
    }

    interface ILoginModel {
        void RequestData(String url, String mobile, String password, OnRequestListener onRequestListener);
    }

    interface OnRequestListener{
        void OnSuccess(LoginBean.DataBean db);
        void OnError(String e);
    }

    interface ILoginPresenter {
        void onSignUp(String url, String mobile, String password);
    }
}
