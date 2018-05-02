package example.com.monidome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.monidome.Api.Api;
import example.com.monidome.DengLu.LoginActivity;
import example.com.monidome.ZhuCe.SignUpPresenter;
import example.com.monidome.ZhuCe.ZhuCeJieKou;
import example.com.monidome.utils.Toasts;

public class SignUpActivity extends AppCompatActivity implements ZhuCeJieKou.ISignUpView{

    @BindView(R.id.login_back)
    ImageView mLoginBack;
    @BindView(R.id.signup_user)
    EditText mSignupUser;
    @BindView(R.id.signup_pwd)
    EditText mSignupPwd;
    @BindView(R.id.signup_again_pwd)
    EditText mSignupAgainPwd;
    @BindView(R.id.signup_btn)
    Button mSignupBtn;
    private SignUpPresenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        signUpPresenter = new SignUpPresenter(this);
    }

    @OnClick({R.id.login_back, R.id.signup_user, R.id.signup_pwd, R.id.signup_again_pwd, R.id.signup_btn})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.login_back:
                finish();
                break;

            case R.id.signup_btn:
                String name = mSignupUser.getText().toString().trim();
                String pwd = mSignupPwd.getText().toString().trim();
                String againPwd = mSignupAgainPwd.getText().toString().trim();
                signUpPresenter.onSignUp(Api.UserURL,name,pwd,againPwd);

                Intent in = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(in);
                finish();
                break;
        }
    }
    @Override
    public void ShowSign() {

        Toasts.showLong(this,"注册成功");

    }

    @Override
    public void ShowError(String e) {
        Toasts.showLong(this,e);
        Log.e("哈哈哈哈啊哈哈哈哈哈哈哈",e);
    }
}
