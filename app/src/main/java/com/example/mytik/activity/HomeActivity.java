package com.example.mytik.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.mytik.R;
import com.example.mytik.adapter.MyPagerAdapter;
import com.example.mytik.entity.TabEntity;
import com.example.mytik.fragment.CollectFragment;
import com.example.mytik.fragment.HomeFragment;
import com.example.mytik.fragment.MyFragment;
import com.example.mytik.fragment.NewsFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {
    private static final String TAG = "HomeActivity";

    private String[] mTitles = {"首页", "资讯", "我的"};

    private int[] mIconUnselectIds = {R.mipmap.home_unselect, R.mipmap.collect_unselect,
            R.mipmap.my_unselect};

    private int[] mIconSelectIds = {R.mipmap.home_selected, R.mipmap.collect_selected,
            R.mipmap.my_selected};

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    private ViewPager mViewPager;

    private CommonTabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.commonTabLayout);
    }

    @Override
    protected void initData() {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(NewsFragment.newInstance());
        mFragments.add(MyFragment.newInstance());
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mTabLayout.setTabData(mTabEntities);
        mViewPager.setOffscreenPageLimit(mFragments.size());
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mTitles, mFragments));
        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    //mTabLayout.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(1);
    }
}