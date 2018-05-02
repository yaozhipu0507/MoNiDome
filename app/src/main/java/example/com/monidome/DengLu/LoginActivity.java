package example.com.monidome.DengLu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.monidome.Api.Api;
import example.com.monidome.R;
import example.com.monidome.ShouYe.ShouYeActivity;
import example.com.monidome.SignUpActivity;
import example.com.monidome.utils.Toasts;

public class LoginActivity extends AppCompatActivity implements DengLuJieKou.ILoginView {

    @BindView(R.id.login_back)
    ImageView mLoginBack;
    @BindView(R.id.login_user)
    EditText mLoginUser;
    @BindView(R.id.login_pwd)
    EditText mLoginPwd;
    @BindView(R.id.wangjimima)
    TextView mWangjimima;
    @BindView(R.id.login_zhuce)
    TextView mLoginZhuce;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);

    }


    @butterknife.OnClick({R.id.login_back, R.id.login_user, R.id.login_pwd, R.id.wangjimima, R.id.login_zhuce, R.id.login_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            //销毁界面
            case R.id.login_back:
                finish();
                break;
            //跳转注册
            case R.id.login_zhuce:

                Intent in = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(in);
                break;
            //登录成功
            case R.id.login_btn:
                String mobile = mLoginUser.getText().toString().trim();
                String pwd = mLoginPwd.getText().toString().trim();
                loginPresenter.onSignUp(Api.UserURL, mobile, pwd);
                break;
        }
    }

    @Override
    public void showLogin(LoginBean.DataBean db) {
        SharedPreferences sp = getSharedPreferences("USER", MODE_PRIVATE);
        sp.edit().putInt("uid", db.getUid())
                .putString("name", db.getUsername())
                .putString("pwd", db.getPassword())
                .commit();

        Log.i("denglu","登录成功");
        Intent in = new Intent(LoginActivity.this, ShouYeActivity.class);
        startActivity(in);
        finish();
        Toasts.showLong(this, "登录成功");
    }

    @Override
    public void showerroe(String e) {
        Toasts.showLong(this, e);
    }
}
