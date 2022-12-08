package com.example.mytik.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mytik.R;
import com.example.mytik.adapter.VideoAdapter;
import com.example.mytik.entity.VideoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {

    private String title;

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
        RecyclerView videoRecyclerView = v.findViewById(R.id.videoRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        videoRecyclerView.setLayoutManager(layoutManager);
        List<VideoEntity> datas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            VideoEntity videoEntity = new VideoEntity();
            videoEntity.setTitle("韭菜盒子新做法，不发面");
            videoEntity.setName("大胃王");
            videoEntity.setDzCount(i * 3);
            videoEntity.setCollectCount(i * 2);
            videoEntity.setCommentCount(i);
            datas.add(videoEntity);
        }
        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), datas);
        videoRecyclerView.setAdapter(videoAdapter);
        return v;
    }
}