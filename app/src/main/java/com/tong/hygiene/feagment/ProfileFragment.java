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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tong.hygiene.BaseActivity;
import com.tong.hygiene.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView txtHn, txtIdcard, txtName, txtPhone;
    CircleImageView imageView;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        txtHn = (TextView) view.findViewById(R.id.txtHn);
        txtIdcard = (TextView) view.findViewById(R.id.txtIdcard);
        txtName = (TextView) view.findViewById(R.id.txtName);
        txtPhone = (TextView) view.findViewById(R.id.txtPhone);
        imageView = (CircleImageView) view.findViewById(R.id.imageView);

        SharedPreferences sp = getActivity().getSharedPreferences("Preferences_tong", Context.MODE_PRIVATE);
        String fullname = sp.getString("firstname", "")+" "+sp.getString("lastname", "");

        txtHn.setText(sp.getInt("numberHN",0)+"");
        txtIdcard.setText(sp.getString("idCard",""));
        txtName.setText(fullname);
        txtPhone.setText(sp.getString("phone",""));

        Glide.with(getActivity())
                .load(BaseActivity.BASE_URL_PICTURE + "/wombImage/" +sp.getString("image",""))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.nopic)
                .into(imageView);

        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return AnimationUtils.loadAnimation(getActivity(),
                enter ? android.R.anim.fade_in : android.R.anim.fade_out);
    }

}
