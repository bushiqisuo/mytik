package com.example.mytik.fragment;

import android.content.pm.ActivityInfo;
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
import android.widget.FrameLayout;

import com.example.mytik.R;
import com.example.mytik.activity.LoginActivity;
import com.example.mytik.adapter.VideoAdapter;
import com.example.mytik.api.Api;
import com.example.mytik.api.ApiCallback;
import com.example.mytik.api.ApiConfig;
import com.example.mytik.entity.VideoEntity;
import com.example.mytik.entity.VideoListResponse;
import com.example.mytik.listener.OnItemChildClickListener;
import com.example.mytik.util.Constants;
import com.example.mytik.util.SpUtil;
import com.example.mytik.util.StringUtils;
import com.example.mytik.util.Tag;
import com.example.mytik.util.Utils;
import com.google.gson.Gson;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xyz.doikki.videocontroller.StandardVideoController;
import xyz.doikki.videocontroller.component.CompleteView;
import xyz.doikki.videocontroller.component.ErrorView;
import xyz.doikki.videocontroller.component.GestureView;
import xyz.doikki.videocontroller.component.TitleView;
import xyz.doikki.videocontroller.component.VodControlView;
import xyz.doikki.videoplayer.player.VideoView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends BaseFragment implements OnItemChildClickListener {
    private static final String TAG = "VideoFragment";

    private String title;

    private int page = 1;

    private RecyclerView videoRecyclerView;

    private RefreshLayout refreshLayout;

    private List<VideoEntity> datas = new ArrayList<>();

    private VideoAdapter videoAdapter;

    private LinearLayoutManager layoutManager;

    //视频播放相关view
    protected VideoView mVideoView;
    protected StandardVideoController mController;
    protected ErrorView mErrorView;
    protected CompleteView mCompleteView;
    protected TitleView mTitleView;

    /**
     * 当前播放的位置
     */
    protected int mCurPos = -1;
    /**
     * 上次播放的位置，用于页面切回来之后恢复播放
     */
    protected int mLastPos = mCurPos;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        initVideoView();

        videoRecyclerView = mRootView.findViewById(R.id.videoRecyclerView);
        refreshLayout= mRootView.findViewById(R.id.refreshLayout);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        videoRecyclerView.setLayoutManager(layoutManager);
        videoAdapter = new VideoAdapter(getActivity());
        videoAdapter.setOnItemChildClickListener(this);
        videoRecyclerView.setAdapter(videoAdapter);
        videoRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                FrameLayout playerContainer = view.findViewById(R.id.player_container);
                View v = playerContainer.getChildAt(0);
                if (v != null && v == mVideoView && !mVideoView.isFullScreen()) {
                    releaseVideoView();
                }
            }
        });

    }

    @Override
    protected void initData() {
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

    protected void initVideoView() {
        mVideoView = new VideoView(getActivity());
        mVideoView.setOnStateChangeListener(new VideoView.SimpleOnStateChangeListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                //监听VideoViewManager释放，重置状态
                if (playState == VideoView.STATE_IDLE) {
                    Utils.removeViewFormParent(mVideoView);
                    mLastPos = mCurPos;
                    mCurPos = -1;
                }
            }
        });
        mController = new StandardVideoController(getActivity());
        mErrorView = new ErrorView(getActivity());
        mController.addControlComponent(mErrorView);
        mCompleteView = new CompleteView(getActivity());
        mController.addControlComponent(mCompleteView);
        mTitleView = new TitleView(getActivity());
        mController.addControlComponent(mTitleView);
        mController.addControlComponent(new VodControlView(getActivity()));
        mController.addControlComponent(new GestureView(getActivity()));
        mController.setEnableOrientation(true);
        mVideoView.setVideoController(mController);
    }

    @Override
    public void onPause() {
        super.onPause();
        pause();
    }

    /**
     * 由于onPause必须调用super。故增加此方法，
     * 子类将会重写此方法，改变onPause的逻辑
     */
    protected void pause() {
        releaseVideoView();
    }

    @Override
    public void onResume() {
        super.onResume();
        resume();
    }

    /**
     * 由于onResume必须调用super。故增加此方法，
     * 子类将会重写此方法，改变onResume的逻辑
     */
    protected void resume() {
        if (mLastPos == -1)
            return;
        //恢复上次播放的位置
        startPlay(mLastPos);
    }

    /**
     * PrepareView被点击
     */
    @Override
    public void onItemChildClick(int position) {
        startPlay(position);
    }

    /**
     * 开始播放
     * @param position 列表位置
     */
    protected void startPlay(int position) {
        if (mCurPos == position) return;
        if (mCurPos != -1) {
            releaseVideoView();
        }
        VideoEntity videoEntity = datas.get(position);
        //边播边存
//        String proxyUrl = ProxyVideoCacheManager.getProxy(getActivity()).getProxyUrl(videoBean.getUrl());
//        mVideoView.setUrl(proxyUrl);

        mVideoView.setUrl(videoEntity.getPlayurl());
        mTitleView.setTitle(videoEntity.getVtitle());
        View itemView = layoutManager.findViewByPosition(position);
        if (itemView == null) return;
        VideoAdapter.ViewHolder viewHolder = (VideoAdapter.ViewHolder) itemView.getTag();
        //把列表中预置的PrepareView添加到控制器中，注意isDissociate此处只能为true, 请点进去看isDissociate的解释
        mController.addControlComponent(viewHolder.mPrepareView, true);
        Utils.removeViewFormParent(mVideoView);
        viewHolder.mPlayerContainer.addView(mVideoView, 0);
        //播放之前将VideoView添加到VideoViewManager以便在别的页面也能操作它
        getVideoViewManager().add(mVideoView, Tag.LIST);
        mVideoView.start();
        mCurPos = position;

    }

    private void releaseVideoView() {
        mVideoView.release();
        if (mVideoView.isFullScreen()) {
            mVideoView.stopFullScreen();
        }
        if(getActivity().getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mCurPos = -1;
    }
}