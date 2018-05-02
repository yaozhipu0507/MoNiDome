package example.com.monidome.ZhuCe;

/**
 * Created by lenovo on 2018/4/29.
 */

public class SignUpPresenter implements ZhuCeJieKou.ISignUpPresenter{

    ZhuCeJieKou.ISignUpView iSignUpView;
    ZhuCeJieKou.ISignUpModel iSignUpModel;

    public SignUpPresenter(ZhuCeJieKou.ISignUpView iSignUpView) {
        this.iSignUpView=iSignUpView;
        iSignUpModel=new SignUpModel();
    }

    @Override
    public void onSignUp(String url, String username, String password, String password_confirm) {
        iSignUpModel.RequestData(url, username, password, password_confirm, new ZhuCeJieKou.OnRequestListener() {
            @Override
            public void OnSuccess() {
                iSignUpView.ShowSign();
            }

            @Override
            public void OnError(String e) {
                iSignUpView.ShowError(e);
            }
        });
    }
}
