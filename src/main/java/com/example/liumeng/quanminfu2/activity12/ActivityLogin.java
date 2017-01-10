package com.example.liumeng.quanminfu2.activity12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.Request;
import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.net.ApiClient;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityLogin extends AppCompatActivity {

    @Bind(R.id.login_et_username)
    EditText mLoginEtUsername;
    @Bind(R.id.login_et_password)
    EditText mLoginEtPassword;
    private String mUserName;
    private String mPasswrod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        mUserName = mLoginEtUsername.getText().toString().trim();
        mPasswrod = "YK9s4mc8z8kiFFeT9e6bVslUq5DUyUikMCoUIawxf1GnHQvZkNx9x/DBvFMU1Yv9riaR4pVsJaqd\n" +
                "Lyd42KNyLwvmASc9pz2hFCss2IW8BKqTfYU1HgkiRvw5zb9GdigEwtlfGbiwCS1NaFvT45R5M+6U\n" +
                "rTzotRDCwEztYSJNreon/qHRK5EWZdnJ1eKB0IUj+q8vY8cQ7F4WJu4rJJ9eQ+oSVczVio3+ZWXW\n" +
                "5E7cGENpzSgiOm5jGO9oaTjmk7Zs";

    }

    @OnClick(R.id.login_btn_certain)
    public void onClick() {
        requstLogin();
    }

    private void requstLogin() {
        ApiClient.init(this);
        String url ="https://mop.chinaums.com/api-portal-phone/v1/user/cp/login";
        Map<String, String> parmas = new HashMap<>();
        parmas.put("loginName",mUserName);
        parmas.put("password",mPasswrod);

        Map<String, String> headers = new HashMap<>();
        headers.put("riskClientId","b2be1e3f-7333-4ff9-a4e7-21235410d99b");

        ApiClient.request(url, Request.Method.POST, headers,parmas, new ApiClient.OnResultListener() {
            @Override
            public void onSuccess(String s) {
                Log.d("liummeng",s);
            }

            @Override
            public void onError(Throwable t) {
                try {
                    Log.d("liumengm",t.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 密码加密
     *
     * @param password
     * @return
     */
//    public static String encryptPassword(String password) {
//
//        String encryPwd = "";
//        if (TextUtils.isEmpty(encryPwd)) {
//            return encryPwd;
//        }
//        String nCer = OpenConfigManager
//                .getProperty(OpenConfigManager.USER_N_CER);
//        String eCer = OpenConfigManager
//                .getProperty(OpenConfigManager.USER_E_CER);
//        try {
//            byte[] encryptKeyData = OpenSDKCrypto.encryptKeyStr(password, nCer,
//                    eCer, nCer.length() * 4);
//            encryPwd = Base64.encodeToString(encryptKeyData, Base64.DEFAULT);
//        } catch (Exception e) {
//            //			MyLog.e("密码加密出错", e);
//        }
//
//        return encryPwd;
//    }
}
