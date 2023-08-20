package com.stuntmed.stuntmed.Explorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuntmed.R;


import java.util.List;

public class ChildAdapter3 extends RecyclerView.Adapter<ChildAdapter3.ViewHolder> {

    List<ChildModelClassPost> childModelClassListPost;
    Context context;

    public ChildAdapter3(List<ChildModelClassPost> childModelClassListPost, Context context) {
        this.childModelClassListPost = childModelClassListPost;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_rv_layout_3,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildAdapter3.ViewHolder holder, int position) {
        holder.iv_child_image_3.setImageResource(childModelClassListPost.get(position).image);
        holder.title_trends.setText(childModelClassListPost.get(position).title_trends);
        holder.user_post.setText(childModelClassListPost.get(position).user_post);

    }

    @Override
    public int getItemCount() {
        return childModelClassListPost.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        ImageView iv_child_image_3;
        TextView title_trends;
        TextView user_post;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_child_image_3 = itemView.findViewById(R.id.iv_child_item_3);
            title_trends = itemView.findViewById(R.id.title_trends_3);
            user_post =  itemView.findViewById(R.id.post_user);
        }
    }
}
