package com.tong.hygiene;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IpActivity extends AppCompatActivity {
    EditText ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);

        ip = (EditText) findViewById(R.id.txtIp);
        Button ok = (Button) findViewById(R.id.btnOk);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("Preferences_tong", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("url", "http://"+ip.getText().toString());
                edit.commit();
                startActivity(new Intent(IpActivity.this, SplashActivity.class));
                finish();
            }
        });
    }
}
