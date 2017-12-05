package com.tong.hygiene;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tong.hygiene.okhttp.ApiClient;
import com.tong.hygiene.okhttp.CallServiceListener;

public class OpenActivity extends BaseActivity {
    Button btn_submit;
    TextView txtHn, txtDate;

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        txtHn = findViewById(R.id.txtHn);
        txtDate = findViewById(R.id.txtDate);
        SharedPreferences sp = getSharedPreferences("Preferences_tong", Context.MODE_PRIVATE);

        txtHn.setText(sp.getInt("numberHN", 0) + "");
        txtDate.setText(sp.getString("AppointmentToCheck", ""));

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(OpenActivity.this, R.style.AppTheme_Dark_Dialog)
                        .setTitle("แจ้งเตือน")
                        .setMessage("ยืนยัน รับทราบกำหนดการนัดหมาย")
                        .setNegativeButton("ยืนยัน", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sendSubmit();
                            }
                        })
                        .setPositiveButton("ยกเลิก",null)
                        .setCancelable(true)
                        .show();
            }
        });
    }

    private void sendSubmit() {
        btn_submit.setEnabled(false);
        showProgressDialog(VERIFY);
        ApiClient.GET get = new ApiClient.GET(this);
        get.setURL(BaseActivity.BASE_URL + "news");
        get.execute();
        get.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                if (data.equals("fail")) {
                    hideProgressDialog();
                    btn_submit.setEnabled(true);
                    dialogTM("แจ้งเตือน", "เกิดข้อผิดพลาดบางอย่าง กรุณาลองใหม่อีกครั้ง");
                } else {
                    dialogTM("สำเร็จ", "รับทราบกำหนดการนัดหมายเรียบร้อยแล้ว");
                    btn_submit.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void ResultError(String data) {
                hideProgressDialog();
                dialogResultError(data);
                btn_submit.setEnabled(true);
            }

            @Override
            public void ResultNull(String data) {
                hideProgressDialog();
                dialogResultNull();
                btn_submit.setEnabled(true);
            }
        });
    }

}
