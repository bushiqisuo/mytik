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

import com.example.mytik.R;
import com.example.mytik.activity.LoginActivity;
import com.example.mytik.adapter.VideoAdapter;
import com.example.mytik.api.Api;
import com.example.mytik.api.ApiCallback;
import com.example.mytik.api.ApiConfig;
import com.example.mytik.entity.VideoEntity;
import com.example.mytik.entity.VideoListResponse;
import com.example.mytik.util.Constants;
import com.example.mytik.util.SpUtil;
import com.example.mytik.util.StringUtils;
import com.google.gson.Gson;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

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

    private int page = 1;

    private RecyclerView videoRecyclerView;

    private RefreshLayout refreshLayout;

    private List<VideoEntity> datas = new ArrayList<>();

    private VideoAdapter videoAdapter;

    public VideoFragment() {
    }

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
        refreshLayout= v.findViewById(R.id.refreshLayout);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        videoRecyclerView.setLayoutManager(layoutManager);
        videoAdapter = new VideoAdapter(getActivity());
        videoRecyclerView.setAdapter(videoAdapter);

        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                getVideoList(true);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                getVideoList(false);
            }
        });
        getVideoList(true);

    }

    private void getVideoList(boolean isRefresh) {
        HashMap<String, Object> params = new HashMap<>();
        String token = SpUtil.getString(getActivity(), "token", "");
        if (StringUtils.isEmpty(token)) {
            navigateTo(LoginActivity.class);
            return;
        }
        params.put("token", token);
        params.put("page", page);
        params.put("limit", Constants.PAGE_LIMIT);
        Api.config(ApiConfig.VIDEO_LIST_URL, params).getRequest(new ApiCallback() {
            @Override
            public void onSuccess(String response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isRefresh) {
                            refreshLayout.finishRefresh(true);
                        }else {
                            refreshLayout.finishLoadMore(true);
                        }
                        VideoListResponse videoListResponse = new Gson().fromJson(response, VideoListResponse.class);
                        if (videoListResponse!=null && videoListResponse.getCode()==0) {
                            List<VideoEntity> list = videoListResponse.getPage().getList();
                            if (list!=null && list.size()>0) {
                                if (isRefresh) {
                                    datas = list;
                                } else {
                                    datas.addAll(list);
                                }
                                videoAdapter.setData(datas);
                                videoAdapter.notifyDataSetChanged();
                            } else {
                                if (isRefresh) {
                                    showToastSync("暂时加载无数据");
                                } else {
                                    showToastSync("暂无更多数据");
                                }
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "onFailure: " + e);
                if (isRefresh) {
                    refreshLayout.finishRefresh(true);//传入false表示刷新失败
                }else {
                    refreshLayout.finishLoadMore(true);//传入false表示加载失败
                }
            }
        });
    }
}