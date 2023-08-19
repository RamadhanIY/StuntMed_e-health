package com.example.myapplication.Explorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class ChildAdapter2 extends RecyclerView.Adapter<ChildAdapter2.ViewHolder> {

    List<ChildModelClass> childModelClassList;
    Context context;

    public ChildAdapter2(List<ChildModelClass> childModelClassList, Context context) {
        this.childModelClassList = childModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_rv_layout_2,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildAdapter2.ViewHolder holder, int position) {
        holder.iv_child_image.setImageResource(childModelClassList.get(position).image);
        holder.title_trends.setText(childModelClassList.get(position).title_trends);

    }

    @Override
    public int getItemCount() {
        return childModelClassList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        ImageView iv_child_image;
        TextView title_trends;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_child_image = itemView.findViewById(R.id.iv_child_item);
            title_trends = itemView.findViewById(R.id.title_trends);
        }
    }
}
