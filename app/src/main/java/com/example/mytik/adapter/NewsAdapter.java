package com.example.mytik.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytik.R;
import com.example.mytik.entity.NewsEntity;

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
        if (type == 1) {
            ViewHolderOne viewHolder = (ViewHolderOne) holder;
        } else if (type == 2) {
            ViewHolderTwo viewHolder = (ViewHolderTwo) holder;
        } else {
            ViewHolderThree viewHolder = (ViewHolderThree) holder;
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
        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(this);
        }
    }
    public class ViewHolderTwo extends RecyclerView.ViewHolder{
        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(this);
        }
    }
    public class ViewHolderThree extends RecyclerView.ViewHolder{
        public ViewHolderThree(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(this);
        }
    }
}
