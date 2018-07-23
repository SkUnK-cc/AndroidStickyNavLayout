package com.example.hp.android_stickynavlayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.android_stickynavlayout.recyclerview.CommonAdapter;
import com.example.hp.android_stickynavlayout.recyclerview.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class ADetailSongFragment extends Fragment {

//    @Bind(R.id.ll_detail_song)
//    LinearLayout llSong;
    @Bind(R.id.rv_detail_song)
    RecyclerView mRecyclerView;
    private int height=0;
    private List<String> mDatas = new ArrayList<>();

    @SuppressLint("ValidFragment")
    public ADetailSongFragment(){
    }

    public static ADetailSongFragment newInstance() {
        ADetailSongFragment fragment = new ADetailSongFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(),container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
        initData();
    }

    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        for (int i = 0; i < 50; i++)
        {
            mDatas.add("mtitle" + " -> " + i);
        }
        mRecyclerView.setAdapter(new CommonAdapter<String>(getActivity(), R.layout.item, mDatas)
        {
            @Override
            public void convert(CommonViewHolder holder, String s) {
                holder.setText(R.id.id_info,s);
            }
        });
    }

    protected void initListener() {

    }

    protected void initView() {
    }

    protected int getContentView() {
        return R.layout.fragment_adetail_song;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

//    public void setData(Song_info song_info) {
//        List<Song> list  = song_info.getSong_list();
//        int count = list.size();
//        height = count * 165;
//        activity.setCheck(0);
//    }

}
