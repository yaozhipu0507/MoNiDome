package example.com.monidome.DengLu;


/**
 * Created by lenovo on 2018/4/29.
 */

public class LoginPresenter implements DengLuJieKou.ILoginPresenter{

    DengLuJieKou.ILoginView iLoginView;
    private final DengLuJieKou.ILoginModel iLoginModel;

    public LoginPresenter(DengLuJieKou.ILoginView iLoginView) {
        this.iLoginView=iLoginView;
        iLoginModel = new LoginMoudle();
    }

    @Override
    public void onSignUp(String url, String mobile, String password) {
        iLoginModel.RequestData(url, mobile, password, new DengLuJieKou.OnRequestListener() {
            @Override
            public void OnSuccess(LoginBean.DataBean db) {
                iLoginView.showLogin(db);
            }

            @Override
            public void OnError(String e) {
                iLoginView.showerroe(e);
            }
        });
    }
}
