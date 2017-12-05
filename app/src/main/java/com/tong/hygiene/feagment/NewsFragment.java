package com.tong.hygiene.feagment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tong.hygiene.BaseActivity;
import com.tong.hygiene.MainActivity;
import com.tong.hygiene.R;
import com.tong.hygiene.ShowNewsActivity;
import com.tong.hygiene.adapter.AdapterNews;
import com.tong.hygiene.model.ModelNews;
import com.tong.hygiene.okhttp.ApiClient;
import com.tong.hygiene.okhttp.CallServiceListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class NewsFragment extends Fragment {
    private AdapterNews adapter;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dummy_fragment, container, false);

       recyclerView = view.findViewById(R.id.dummyfrag_scrollableview);
        getNews();
        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return AnimationUtils.loadAnimation(getActivity(),
                enter ? android.R.anim.fade_in : android.R.anim.fade_out);
    }

    private void getNews() {
        ApiClient.GET get = new ApiClient.GET(getActivity());
        get.setURL(BaseActivity.BASE_URL+"news");
        get.execute();
        get.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                if (data.equals("fail")) {
                    ((MainActivity)getActivity()).hideProgressDialog();
                } else {
                    setList(data);
                }
            }

            @Override
            public void ResultError(String data) {
                ((MainActivity)getActivity()).hideProgressDialog();
                ((MainActivity)getActivity()).dialogResultError(data);
            }

            @Override
            public void ResultNull(String data) {
                ((MainActivity)getActivity()).hideProgressDialog();
                ((MainActivity)getActivity()).dialogResultNull();
            }
        });
    }

    private void setList(String json) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelNews>>() {
        }.getType();
        Collection<ModelNews> enums = gson.fromJson(json, collectionType);
        final ArrayList<ModelNews> posts = new ArrayList<ModelNews>(enums);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);


        adapter = new AdapterNews(getActivity(), posts);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterNews.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ShowNewsActivity.class);
                intent.putExtra("title", posts.get(position).getInformationName());
                intent.putExtra("detail", posts.get(position).getInformationDetail());
                intent.putExtra("image", posts.get(position).getInformationImage());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        ((MainActivity)getActivity()).hideProgressDialog();
    }
}
