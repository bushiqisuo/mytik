package com.example.mytik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytik.R;
import com.example.mytik.entity.VideoEntity;
import com.example.mytik.view.CircleTransform;
import com.squareup.picasso.Picasso;

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

        viewHolder.title.setText(entity.getVtitle());
        viewHolder.author.setText(entity.getAuthor());
        viewHolder.dz.setText(String.valueOf(entity.getLikeNum()));
        viewHolder.collect.setText(String.valueOf(entity.getCollectNum()));
        viewHolder.comment.setText(String.valueOf(entity.getCommentNum()));
        Picasso.with(context)
                .load(entity.getHeadurl())
                .transform(new CircleTransform())
                .into(viewHolder.imgHeader);
        Picasso.with(context).load(entity.getCoverurl()).into(viewHolder.imgCover);
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
        private ImageView imgHeader;
        private ImageView imgCover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.videoTitle);
            author = itemView.findViewById(R.id.author);
            comment = itemView.findViewById(R.id.comment);
            collect = itemView.findViewById(R.id.collect);
            dz = itemView.findViewById(R.id.dz);
            imgHeader = itemView.findViewById(R.id.img_header);
            imgCover = itemView.findViewById(R.id.img_cover);
        }
    }
}
