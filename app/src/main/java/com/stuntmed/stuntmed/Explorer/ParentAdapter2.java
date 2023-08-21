package com.stuntmed.stuntmed.Explorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stuntmed.stuntmed.R;


import java.util.List;

public class ParentAdapter2 extends RecyclerView.Adapter<ParentAdapter2.ViewHolder> {

    List<ParentModelClass> parentModelClassList;

    Context context;

    public ParentAdapter2(List<ParentModelClass> parentModelClassList, Context context) {
        this.parentModelClassList = parentModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_layout_explorer_2,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentAdapter2.ViewHolder holder, int position) {
        holder.tv_parent_title_2.setText(parentModelClassList.get(position).title);

        ChildAdapter2 childAdapter2;
        childAdapter2 = new ChildAdapter2(parentModelClassList.get(position).childModelClassList,context);
        holder.rv_child_2.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        holder.rv_child_2.setAdapter(childAdapter2);
        childAdapter2.notifyDataSetChanged();
        
    }

    @Override
    public int getItemCount() {
        return parentModelClassList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{


        RecyclerView rv_child_2;
        TextView tv_parent_title_2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_child_2 = itemView.findViewById(R.id.rv_child_2);
            tv_parent_title_2 = itemView.findViewById(R.id.tv_parent_title_2);
        }
    }
}
