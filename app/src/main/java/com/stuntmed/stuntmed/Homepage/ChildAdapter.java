package com.stuntmed.stuntmed.Homepage;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stuntmed.stuntmed.Homepage.ChildModelClass;
import com.stuntmed.stuntmed.R;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    List<ChildModelClass> childModelClassList;
    Context context;

    public ChildAdapter(List<ChildModelClass> childModelClassList, Context context) {
        this.childModelClassList = childModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homepage_child_rv_layout,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pic_baby.setImageResource(childModelClassList.get(position).pic_baby);
        holder.nama_anak.setText(childModelClassList.get(position).nama_anak);
        holder.pic_gender.setImageResource(childModelClassList.get(position).pic_gender);
        holder.pic_category.setImageResource(childModelClassList.get(position).pic_category);
        holder.kategori.setText(childModelClassList.get(position).kategori);
        holder.berat.setText(childModelClassList.get(position).berat);
        holder.tinggi.setText(childModelClassList.get(position).tinggi);
        holder.lk.setText(childModelClassList.get(position).lk);


    }

    @Override
    public int getItemCount() {
        return childModelClassList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        CircleImageView pic_baby;
        TextView nama_anak,kategori,berat,tinggi,lk;
        ImageView pic_gender;
        ImageView pic_category;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic_baby = itemView.findViewById(R.id.pic_baby);
            nama_anak = itemView.findViewById(R.id.title_nama_anak);
            pic_gender = itemView.findViewById(R.id.gender_anak);
            pic_category = itemView.findViewById(R.id.pic_category);
            kategori = itemView.findViewById(R.id.kategori_stunting);
            berat = itemView.findViewById(R.id.info_berat);
            tinggi = itemView.findViewById(R.id.info_tinggi);
            lk =  itemView.findViewById(R.id.info_lk);
        }
    }
}
