package com.example.mytik.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytik.R;
import com.example.mytik.entity.VideoEntity;
import com.example.mytik.listener.OnItemChildClickListener;
import com.example.mytik.listener.OnItemClickListener;
import com.example.mytik.view.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

import xyz.doikki.videocontroller.component.PrepareView;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<VideoEntity> data;

    private OnItemChildClickListener mOnItemChildClickListener;

    private OnItemClickListener mOnItemClickListener;

    public VideoAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<VideoEntity> data) {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
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
        Picasso.with(context)
                .load(entity.getCoverurl())
                .into(viewHolder.mThumb);
        viewHolder.mPosition = position;
    }

    @Override
    public int getItemCount() {
        if (data!=null && data.size()>0) {
            return data.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title;
        private TextView author;
        private TextView comment;
        private TextView collect;
        private TextView dz;
        private ImageView imgHeader;
        public ImageView mThumb;
        public PrepareView mPrepareView;
        public FrameLayout mPlayerContainer;
        public int mPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.videoTitle);
            author = itemView.findViewById(R.id.author);
            comment = itemView.findViewById(R.id.comment);
            collect = itemView.findViewById(R.id.collect);
            dz = itemView.findViewById(R.id.dz);
            imgHeader = itemView.findViewById(R.id.img_header);
            mPrepareView = itemView.findViewById(R.id.prepare_view);
            mPlayerContainer = itemView.findViewById(R.id.player_container);
            mThumb = mPrepareView.findViewById(R.id.thumb);

            if (mOnItemChildClickListener != null) {
                mPlayerContainer.setOnClickListener(this);
            }
            if (mOnItemClickListener != null) {
                itemView.setOnClickListener(this);
            }
            itemView.setTag(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.player_container) {
                if (mOnItemChildClickListener != null) {
                    mOnItemChildClickListener.onItemChildClick(mPosition);
                }
            } else {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mPosition);
                }
            }
        }
    }

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
