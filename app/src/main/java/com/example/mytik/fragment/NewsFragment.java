package com.example.mytik.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytik.R;
import com.example.mytik.adapter.NewsAdapter;
import com.example.mytik.entity.NewsEntity;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends BaseFragment {
    private RecyclerView newsRecyclerView;

    private RefreshLayout newsRefreshLayout;

    private LinearLayoutManager layoutManager;

    private NewsAdapter newsAdapter;

    private List<NewsEntity> datas = new ArrayList<>();

    public NewsFragment() {
    }

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
        for (int i = 0; i < 15; i++) {
            int type = i % 3 + 1;
            NewsEntity newsEntity = new NewsEntity();
            newsEntity.setType(type);
            datas.add(newsEntity);
        }
        newsAdapter.setData(datas);
        newsRecyclerView.setAdapter(newsAdapter);
    }
}