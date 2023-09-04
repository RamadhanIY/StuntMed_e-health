package com.stuntmed.stuntmed.Homepage;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stuntmed.stuntmed.Databases.Baby;
import com.stuntmed.stuntmed.HasilStuntingActivity;

import com.stuntmed.stuntmed.R;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {


    List<Baby> babyList;
    Context context;

    public ChildAdapter(List<Baby> babyList, Context context) {
        this.babyList = babyList;
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


        Baby currentbaby = babyList.get(position);
        String firstName = currentbaby.name.split(" ")[0];

//        if(currentbaby != null) {
//        holder.pic_baby.setImageResource(currentbaby.name);
        holder.nama_anak.setText(firstName);
        holder.gender.setText(currentbaby.gender);
//        holder.pic_category.setImageResource(currentbaby.kategori);
        holder.kategori.setText(currentbaby.kategori);
        holder.berat.setText(currentbaby.berat);
        holder.tinggi.setText(currentbaby.tinggi);
        holder.lk.setText(currentbaby.lk);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HasilStuntingActivity.class);
                intent.putExtra("NIK", currentbaby.getNik());  // Asumsi ChildModelClass memiliki method getNik()
                context.startActivity(intent);
            }
        });
//        }else{
//            Intent intent = new Intent(context, HasilStuntingActivity.class);
//            context.startActivity(intent);
//        }


    }

    @Override
    public int getItemCount() {
        return babyList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

//        CircleImageView pic_baby;
        TextView nama_anak,kategori,berat,tinggi,lk,gender;
//        ImageView pic_gender;
//        ImageView pic_category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            pic_baby = itemView.findViewById(R.id.pic_baby);
            nama_anak = itemView.findViewById(R.id.title_nama_anak);
            gender = itemView.findViewById(R.id.gender_anak);
//            pic_category = itemView.findViewById(R.id.pic_category);
            kategori = itemView.findViewById(R.id.kategori_stunting);
            berat = itemView.findViewById(R.id.info_berat);
            tinggi = itemView.findViewById(R.id.info_tinggi);
            lk =  itemView.findViewById(R.id.info_lk);
        }
    }
}
