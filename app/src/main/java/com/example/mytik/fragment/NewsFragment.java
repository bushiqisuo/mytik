package com.example.mytik.fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytik.R;
import com.example.mytik.activity.LoginActivity;
import com.example.mytik.adapter.NewsAdapter;
import com.example.mytik.api.Api;
import com.example.mytik.api.ApiCallback;
import com.example.mytik.api.ApiConfig;
import com.example.mytik.entity.NewsEntity;
import com.example.mytik.entity.NewsListResponse;
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
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends BaseFragment {
    private static final String TAG = "NewsFragment";

    private RecyclerView newsRecyclerView;

    private RefreshLayout newsRefreshLayout;

    private LinearLayoutManager layoutManager;

    private NewsAdapter newsAdapter;

    private List<NewsEntity> datas = new ArrayList<>();

    private int page = 1;

    public NewsFragment() {
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    newsAdapter.setData(datas);
                    newsAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        newsRecyclerView = mRootView.findViewById(R.id.newsRecyclerView);
        newsRefreshLayout = mRootView.findViewById(R.id.newsRefreshLayout);
    }

    @Override
    protected void initData() {
        newsRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        newsRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newsRecyclerView.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(getActivity());
        newsAdapter.setData(datas);
        newsRecyclerView.setAdapter(newsAdapter);

        newsRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                getNewsList(true);
            }
        });
        newsRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                getNewsList(false);
            }
        });
        getNewsList(true);
    }

    private void getNewsList(boolean isRefresh) {
        HashMap<String, Object> params = new HashMap<>();
        String token = SpUtil.getString(getActivity(), "token", "");
        if (StringUtils.isEmpty(token)) {
            navigateTo(LoginActivity.class);
            return;
        }
        params.put("token", token);
        params.put("page", page);
        params.put("limit", Constants.MAX_LIMIT);//此处暂查询所有，本地分类。后端接口有问题
        Api.config(ApiConfig.NEWS_LIST, params).getRequest(new ApiCallback() {
            @Override
            public void onSuccess(String response) {
                if (isRefresh) {
                    newsRefreshLayout.finishRefresh(true);
                }else {
                    newsRefreshLayout.finishLoadMore(true);
                }
                NewsListResponse newsListResponse = new Gson().fromJson(response, NewsListResponse.class);
                if (newsListResponse!=null && newsListResponse.getCode()==0) {
                    List<NewsEntity> list = newsListResponse.getPage().getList();
                    if (list!=null && list.size()>0) {
                        if (isRefresh) {
                            datas = list;
                        } else {
                            datas.addAll(list);
                        }
                        mHandler.sendEmptyMessage(0);
                    } else {
                        if (isRefresh) {
                            showToastSync("暂时加载无数据");
                        } else {
                            showToastSync("暂无更多数据");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "onFailure: " + e);
                if (isRefresh) {
                    newsRefreshLayout.finishRefresh(true);//传入false表示刷新失败
                }else {
                    newsRefreshLayout.finishLoadMore(true);//传入false表示加载失败
                }
            }
        });
    }
}