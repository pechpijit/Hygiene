package com.tong.hygiene;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.tong.hygiene.ResideMenu.ResideMenu;
import com.tong.hygiene.ResideMenu.ResideMenuItem;
import com.tong.hygiene.feagment.AppointFragment;
import com.tong.hygiene.feagment.ContactFragment;
import com.tong.hygiene.feagment.NewsFragment;
import com.tong.hygiene.feagment.ProfileFragment;
import com.tong.hygiene.feagment.SetAlarmFragment;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ResideMenu resideMenu;
    boolean open = false;
    private MainActivity mContext;
    Fragment selectFragment;
    private ResideMenuItem itemProfile,itemCalader,itemDetail,itemSettingAlarm,itemContext,itemLogout;
    TextView txt_title;
    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        txt_title = (TextView) findViewById(R.id.txt_title);
        mContext = this;
        setUpMenu();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, ""+refreshedToken);

    }
    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setUse3D(true);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemProfile     = new ResideMenuItem(this, R.drawable.icon_home,"ข้อมูลส่วนตัว");
        itemCalader     = new ResideMenuItem(this, R.drawable.icon_home,"กำหนดการนัดหมาย");
        itemDetail     = new ResideMenuItem(this, R.drawable.icon_home,"ข้อมูลความรู้");
        itemSettingAlarm     = new ResideMenuItem(this, R.drawable.icon_home,"ตั้งค่าเวลาแจ้งเตือน");
        itemContext     = new ResideMenuItem(this, R.drawable.icon_home,"ติดต่ออนามัย");
        itemLogout     = new ResideMenuItem(this, R.drawable.icon_home,"ออกจาระบบ");



        itemProfile.setOnClickListener(this);
        itemCalader.setOnClickListener(this);
        itemDetail.setOnClickListener(this);
        itemSettingAlarm.setOnClickListener(this);
        itemContext.setOnClickListener(this);
        itemLogout.setOnClickListener(this);


        resideMenu.addMenuItem(itemDetail, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalader, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemContext, ResideMenu.DIRECTION_LEFT);

        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemSettingAlarm, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemLogout, ResideMenu.DIRECTION_RIGHT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        selectFragment = new NewsFragment();
        transaction.replace(R.id.content, selectFragment);
        transaction.commit();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(selectFragment);
        selectFragment = null;

        if (view == itemProfile){
            txt_title.setText("ข้อมูลส่วนตัว");
            selectFragment = new ProfileFragment();
            transaction.replace(R.id.content, selectFragment);
            transaction.commit();
        }else if (view == itemCalader){
            txt_title.setText("กำหนดการนัดพบหมด");
            selectFragment = new AppointFragment();
            transaction.replace(R.id.content, selectFragment);
            transaction.commit();
        }else if (view == itemDetail){
            txt_title.setText("ความรู้");
            selectFragment = new NewsFragment();
            transaction.replace(R.id.content, selectFragment);
            transaction.commit();
        }else if (view == itemSettingAlarm){
            txt_title.setText("ตั้งเวลาแจ้งเตือน");
            selectFragment = new SetAlarmFragment();
            transaction.replace(R.id.content, selectFragment);
            transaction.commit();
        }else if (view == itemContext) {
            txt_title.setText("รายละเอียดข้อมูลการติดต่อ");
            selectFragment = new ContactFragment();
            transaction.replace(R.id.content, selectFragment);
            transaction.commit();
        }else if (view == itemLogout) {
            SharedPreferences sp = getSharedPreferences("Preferences_tong", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putBoolean("login", false);
            edit.commit();
            startActivity(new Intent(MainActivity.this,SplashActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {

            int menu = resideMenu.tab;

            switch (menu) {
                case 0:
//                    Toast.makeText(mContext, "Menu is open!", Toast.LENGTH_SHORT).show();
                    open = true;
                    break;
                case 1:
//                    Toast.makeText(mContext, "Menu is open!", Toast.LENGTH_SHORT).show();
                    open = true;
                    break;
            }

        }

        @Override
        public void closeMenu() {
            open = false;
//            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }

    @Override
    public void onBackPressed() {
        if (open) {
            open = false;
            resideMenu.closeMenu();
        } else {
            finish();
        }

    }
}
