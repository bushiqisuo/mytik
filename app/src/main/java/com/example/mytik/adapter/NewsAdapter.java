package com.example.mytik.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytik.R;
import com.example.mytik.entity.NewsEntity;
import com.example.mytik.listener.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    private List<NewsEntity> data;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<NewsEntity> data) {
        this.data = data;
    }

    private OnItemClickListener mOnItemClickListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.news_item_one, parent, false);
            return new ViewHolderOne(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(context).inflate(R.layout.news_item_two, parent, false);
            return new ViewHolderTwo(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.news_item_three, parent, false);
            return new ViewHolderThree(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int type = getItemViewType(position);
        NewsEntity newsEntity = data.get(position);
        if (type == 1) {
            ViewHolderOne viewHolder = (ViewHolderOne) holder;
            viewHolder.newsEntity = newsEntity;
            viewHolder.title.setText(newsEntity.getNewsTitle());
            viewHolder.author.setText(newsEntity.getAuthorName());
            viewHolder.comment.setText(newsEntity.getCommentCount() + "评论.");
            viewHolder.time.setText(newsEntity.getReleaseDate());
            Picasso.with(context)
                    .load(newsEntity.getHeaderUrl())
                    .into(viewHolder.header);
            Picasso.with(context)
                    .load(newsEntity.getThumbEntities().get(0).getThumbUrl())
                    .into(viewHolder.thumb);
        } else if (type == 2) {
            ViewHolderTwo viewHolder = (ViewHolderTwo) holder;
            viewHolder.newsEntity = newsEntity;
            viewHolder.title.setText(newsEntity.getNewsTitle());
            viewHolder.author.setText(newsEntity.getAuthorName());
            viewHolder.comment.setText(newsEntity.getCommentCount() + "评论.");
            viewHolder.time.setText(newsEntity.getReleaseDate());
            Picasso.with(context)
                    .load(newsEntity.getHeaderUrl())
                    .into(viewHolder.header);
            Picasso.with(context)
                    .load(newsEntity.getThumbEntities().get(0).getThumbUrl())
                    .into(viewHolder.pic1);
            Picasso.with(context)
                    .load(newsEntity.getThumbEntities().get(1).getThumbUrl())
                    .into(viewHolder.pic2);
            Picasso.with(context)
                    .load(newsEntity.getThumbEntities().get(2).getThumbUrl())
                    .into(viewHolder.pic3);
        } else {
            ViewHolderThree viewHolder = (ViewHolderThree) holder;
            viewHolder.newsEntity = newsEntity;
            viewHolder.title.setText(newsEntity.getNewsTitle());
            viewHolder.author.setText(newsEntity.getAuthorName());
            viewHolder.comment.setText(newsEntity.getCommentCount() + "评论.");
            viewHolder.time.setText(newsEntity.getReleaseDate());
            Picasso.with(context)
                    .load(newsEntity.getHeaderUrl())
                    .into(viewHolder.header);
            Picasso.with(context)
                    .load(newsEntity.getThumbEntities().get(0).getThumbUrl())
                    .into(viewHolder.thumb);
        }
    }

    @Override
    public int getItemCount() {
        if (data!=null && data.size()>0) {
            return data.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        NewsEntity entity = data.get(position);
        return entity.getType();
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView thumb;
        private ImageView header;
        private TextView author;
        private TextView comment;
        private TextView time;
        private NewsEntity newsEntity;
        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            thumb = itemView.findViewById(R.id.thumb);
            header = itemView.findViewById(R.id.header);
            author = itemView.findViewById(R.id.author);
            comment = itemView.findViewById(R.id.comment);
            time = itemView.findViewById(R.id.time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(newsEntity);
                }
            });
        }
    }
    public class ViewHolderTwo extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView header;
        private TextView author;
        private TextView comment;
        private TextView time;
        private ImageView pic1, pic2, pic3;
        private NewsEntity newsEntity;

        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pic1 = itemView.findViewById(R.id.pic1);
            pic2 = itemView.findViewById(R.id.pic2);
            pic3 = itemView.findViewById(R.id.pic3);
            header = itemView.findViewById(R.id.header);
            author = itemView.findViewById(R.id.author);
            comment = itemView.findViewById(R.id.comment);
            time = itemView.findViewById(R.id.time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(newsEntity);
                }
            });
        }
    }
    public class ViewHolderThree extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView thumb;
        private ImageView header;
        private TextView author;
        private TextView comment;
        private TextView time;
        private NewsEntity newsEntity;
        public ViewHolderThree(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            thumb = itemView.findViewById(R.id.thumb);
            header = itemView.findViewById(R.id.header);
            author = itemView.findViewById(R.id.author);
            comment = itemView.findViewById(R.id.comment);
            time = itemView.findViewById(R.id.time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(newsEntity);
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Serializable obj);
    }
}
