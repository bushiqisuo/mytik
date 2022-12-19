package com.example.mytik.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytik.R;
import com.example.mytik.activity.LoginActivity;
import com.example.mytik.adapter.HomeAdapter;
import com.example.mytik.api.Api;
import com.example.mytik.api.ApiCallback;
import com.example.mytik.api.ApiConfig;
import com.example.mytik.entity.CategoryEntity;
import com.example.mytik.entity.VideoCategoryResponse;
import com.example.mytik.entity.VideoEntity;
import com.example.mytik.entity.VideoListResponse;
import com.example.mytik.util.SpUtil;
import com.example.mytik.util.StringUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };

    private ViewPager viewPager;

    private SlidingTabLayout slidingTabLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        viewPager = mRootView.findViewById(R.id.slideViewPager);
        slidingTabLayout = mRootView.findViewById(R.id.slidingTL);
    }

    @Override
    protected void initData() {
        getVideoCategoryList();
    }

    private void getVideoCategoryList() {
        HashMap<String, Object> params = new HashMap<>();
        String token = SpUtil.getString(getActivity(), "token", "");
        if (StringUtils.isEmpty(token)) {
            navigateTo(LoginActivity.class);
            return;
        }
        params.put("token", token);
        Api.config(ApiConfig.VIDEO_CATEGORY_LIST,params).getRequest(new ApiCallback() {
            @Override
            public void onSuccess(String response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        VideoCategoryResponse videoCategoryResponse = new Gson().fromJson(response, VideoCategoryResponse.class);
                        if (videoCategoryResponse!=null && videoCategoryResponse.getCode()==0) {
                            List<CategoryEntity> list = videoCategoryResponse.getPage().getList();
                            if (list!=null && list.size()>0) {
                                mTitles = new String[list.size()];
                                for (int i = 0; i < list.size(); i++) {
                                    mTitles[i] = list.get(i).getCategoryName();
                                    mFragments.add(VideoFragment.newInstance(list.get(i).getCategoryId()));
                                }
                                List<String> titlesList = list.stream().map(CategoryEntity::getCategoryName).collect(Collectors.toList());

                                viewPager.setOffscreenPageLimit(mFragments.size());
                                viewPager.setAdapter(new HomeAdapter(getFragmentManager(), mTitles, mFragments));
                                slidingTabLayout.setViewPager(viewPager);
                            } else {
                                showToastSync("获取视频类别列表为空");
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "onFailure: " + e);
            }
        });
    }
}