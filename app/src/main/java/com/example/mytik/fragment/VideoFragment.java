package com.example.mytik.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mytik.R;
import com.example.mytik.activity.LoginActivity;
import com.example.mytik.adapter.VideoAdapter;
import com.example.mytik.api.Api;
import com.example.mytik.api.ApiCallback;
import com.example.mytik.api.ApiConfig;
import com.example.mytik.entity.VideoEntity;
import com.example.mytik.entity.VideoListResponse;
import com.example.mytik.util.SpUtil;
import com.example.mytik.util.StringUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends BaseFragment {
    private static final String TAG = "VideoFragment";

    private String title;

    private RecyclerView videoRecyclerView;

    private List<VideoEntity> datas;

    public VideoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment VideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(String param) {
        VideoFragment fragment = new VideoFragment();
        fragment.title = param;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        videoRecyclerView = v.findViewById(R.id.videoRecyclerView);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        videoRecyclerView.setLayoutManager(layoutManager);

        getVideoList();
    }

    private void getVideoList() {
        HashMap<String, Object> params = new HashMap<>();
        String token = SpUtil.getString(getActivity(), "token", "");
        if (StringUtils.isEmpty(token)) {
            navigateTo(LoginActivity.class);
            return;
        }
        params.put("token", token);
        Api.config(ApiConfig.VIDEO_LIST_URL, params).getRequest(new ApiCallback() {
            @Override
            public void onSuccess(String response) {
                Log.e(TAG, "onSuccess: " + response);
                VideoListResponse videoListResponse = new Gson().fromJson(response, VideoListResponse.class);
                if (videoListResponse!=null && videoListResponse.getCode()==0) {
                    datas = videoListResponse.getPage().getList();
                    VideoAdapter videoAdapter = new VideoAdapter(getActivity(), videoListResponse.getPage().getList());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            videoRecyclerView.setAdapter(videoAdapter);
                        }
                    });
                }
                showToastAsync(response);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "onFailure: " + e);
            }
        });
    }
}