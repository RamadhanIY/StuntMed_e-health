package com.example.myapplication.Explorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class ParentAdapter3 extends RecyclerView.Adapter<ParentAdapter3.ViewHolder> {

    List<ParentModelClassPost> parentModelClassPostList;

    Context context;

    public ParentAdapter3(List<ParentModelClassPost> parentModelClassPostList,Context context) {
        this.parentModelClassPostList=parentModelClassPostList;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_layout_explorer_3,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentAdapter3.ViewHolder holder, int position) {
        holder.tv_parent_title_3.setText(parentModelClassPostList.get(position).title);

        ChildAdapter3 childAdapter3;
        childAdapter3 = new ChildAdapter3(parentModelClassPostList.get(position).childModelClassListPost,context);
        holder.rv_child_3.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        holder.rv_child_3.setAdapter(childAdapter3);
        childAdapter3.notifyDataSetChanged();
        
    }

    @Override
    public int getItemCount() {
        return parentModelClassPostList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{


        RecyclerView rv_child_3;
        TextView tv_parent_title_3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_child_3 = itemView.findViewById(R.id.rv_child_3);
            tv_parent_title_3 = itemView.findViewById(R.id.tv_parent_title_3);
        }
    }
}
