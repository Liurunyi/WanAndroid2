package com.example.wanandroid.wechat.wechatviewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.wanandroid.R;
import com.example.wanandroid.data.entity.ArticleData;

import java.util.ArrayList;
import java.util.List;

public class WechatViewPagerAdapter extends RecyclerView.Adapter<WechatViewPagerAdapter.MyHolder> {

    private Context context;
    private ArrayList<ArticleData.Article> list = new ArrayList<>();

    public WechatViewPagerAdapter(Context context) {
        this.context = context;
    }

    public void addlist(List<ArticleData.Article> lists) {
        if (list != null) {
            list.addAll(lists);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.wechat_item, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        MyHolder vh = holder;
        vh.author.setText(list.get(position).getAuthor());
        vh.chapterName.setText(list.get(position).getChapterName().concat(" / ").concat(list.get(position).getSuperChapterName()));
        vh.niceDate.setText(list.get(position).getNiceDate());
        vh.title.setText(list.get(position).getTitle());

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OnItemClick != null) {
                    OnItemClick.setOnItemClick(list.get(position).getTitle(), list.get(position).getLink());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView author;
        private TextView niceDate;
        private TextView title;
        private TextView chapterName;
        private ImageView like;

        public MyHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            niceDate = itemView.findViewById(R.id.niceDate);
            title = itemView.findViewById(R.id.title);
            chapterName = itemView.findViewById(R.id.chapterName);
            like = itemView.findViewById(R.id.like);
        }
    }

    public interface OnItemClick {
        void setOnItemClick(String title, String link);
    }

    private WechatViewPagerAdapter.OnItemClick OnItemClick;

    public void setOnItemClick(WechatViewPagerAdapter.OnItemClick onItemClick) {
        OnItemClick = onItemClick;
    }
}
