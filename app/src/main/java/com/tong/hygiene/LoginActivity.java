package com.tong.hygiene;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.tong.hygiene.model.ModelProfile;
import com.tong.hygiene.okhttp.ApiClient;
import com.tong.hygiene.okhttp.CallServiceListener;

public class LoginActivity extends BaseActivity {
    Button _loginButton;
    TextView _code;
    String TAG = "LoginActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _code = findViewById(R.id._code);
        _loginButton = findViewById(R.id.btn_login);

    }

    public void OnClickBtnLogin(View  view) {
        Log.d(TAG, "Login");

        _loginButton.setEnabled(false);

        showProgressDialog(AUTH);

        String hn = _code.getText().toString();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token:"+refreshedToken);
        ApiClient.GET get = new ApiClient.GET(this);
        get.setURL(BASE_URL+"checkHn/"+hn+"/"+refreshedToken);
        get.execute();
        get.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                if (data.equals("fail")) {
                    hideProgressDialog();
                    _loginButton.setEnabled(true);
                } else {
                    onLoginSuccess(data);
                }
            }

            @Override
            public void ResultError(String data) {
                hideProgressDialog();
                dialogResultError(data);
                _loginButton.setEnabled(true);
            }

            @Override
            public void ResultNull(String data) {
                hideProgressDialog();
                dialogResultNull();
                _loginButton.setEnabled(true);
            }
        });
    }

    public void onLoginSuccess(String json) {

        Gson gson = new Gson();
        ModelProfile profile = gson.fromJson(json, ModelProfile.class);

        SharedPreferences sp = getSharedPreferences("Preferences_tong", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("login", true);
        edit.putString("firstname", profile.getFirstname());
        edit.putString("lastname", profile.getLastname());
        edit.putInt("numberHN", profile.getNumberHN());
        edit.putString("phone", profile.getPhone());
        edit.putInt("age", profile.getAge());
        edit.putString("idCard", profile.getIdCard());
        edit.putString("image", profile.getImage());
        edit.putString("AppointmentToCheck", profile.getAppointmentToCheck());
        edit.commit();

        _loginButton.setEnabled(true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
