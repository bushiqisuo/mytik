package com.example.mytik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytik.R;
import com.example.mytik.entity.VideoEntity;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<VideoEntity> data;

    public VideoAdapter(Context context, List<VideoEntity> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        VideoEntity entity = data.get(position);

        viewHolder.title.setText(entity.getTitle());
        viewHolder.author.setText(entity.getName());
        viewHolder.dz.setText(String.valueOf(entity.getDzCount()));
        viewHolder.collect.setText(String.valueOf(entity.getCollectCount()));
        viewHolder.comment.setText(String.valueOf(entity.getCommentCount()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView author;
        private TextView comment;
        private TextView collect;
        private TextView dz;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.videoTitle);
            author = itemView.findViewById(R.id.author);
            comment = itemView.findViewById(R.id.comment);
            collect = itemView.findViewById(R.id.collect);
            dz = itemView.findViewById(R.id.dz);
        }
    }
}
