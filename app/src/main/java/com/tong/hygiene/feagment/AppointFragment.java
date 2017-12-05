package com.tong.hygiene.feagment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.tong.hygiene.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppointFragment extends Fragment {
    Button btn_submit;
    TextView txtHn, txtDate;

    public AppointFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appoint, container, false);
        txtHn = view.findViewById(R.id.txtHn);
        txtDate = view.findViewById(R.id.txtDate);
        SharedPreferences sp = getActivity().getSharedPreferences("Preferences_tong", Context.MODE_PRIVATE);

        txtHn.setText(sp.getInt("numberHN",0)+"");
        txtDate.setText(sp.getString("AppointmentToCheck",""));

        btn_submit = view.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
//                        .setTitleText("ยืนยันการรับทราบหรือไม่")
//                        .setContentText("กด 'ตกลง' เพื่อยืนยันการรับทราบนัดหมาย")
//                        .setConfirmText("ตกลง")
//                        .setCancelText("ยกเลิก")
//                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                sDialog
//                                        .setTitleText("สำเร็จ!")
//                                        .setContentText("ยืนยันการรับทราบเรียบร้อย")
//                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                            @Override
//                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                                btn_submit.setVisibility(View.INVISIBLE);
//                                                sweetAlertDialog.dismiss();
//                                            }
//                                        })
//                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                            }
//                        })
//                        .show();
            }
        });
        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return AnimationUtils.loadAnimation(getActivity(),
                enter ? android.R.anim.fade_in : android.R.anim.fade_out);
    }

}
