package com.stuntmed.stuntmed;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stuntmed.stuntmed.Databases.Baby;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class History_adapter extends ArrayAdapter<ListData_History>{

    String descrip;

    public History_adapter(@NonNull Context context, ArrayList<ListData_History> dataHistoryArrayList) {
        super(context, R.layout.list_history, dataHistoryArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListData_History listDataHistory = getItem(position);

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_history, parent, false);
        }
        CircleImageView listpic = view.findViewById(R.id.photo);
        TextView listName = view.findViewById(R.id.NamaHistory);
        TextView listdate = view.findViewById(R.id.tangggal);
        TextView listdesc = view.findViewById(R.id.desc);


        Baby.getBabyByNik(listDataHistory.nik, new Method.VolleyCallback() {
            @Override
            public void onSuccess(Object result) {
                Baby baby = (Baby)result;
                if(baby.label_stunting.equals("Tidak Stunting") ){
                    descrip = "Anak ibu sehat dan memiliki berat, tinggi, dan lingkar kepala yang normal";
                    listdesc.setText(descrip);
                    Method.loadImageBaby(listpic,listDataHistory.nik);
                    listName.setText(listDataHistory.NamaHistory);
                    listdate.setText(listDataHistory.tanggal);
                }
                else if(baby.label_stunting.equals("Stunting")){
                    descrip = "Sang anak mengalami Stunting! sebaiknya konsultasikan dengan dokter anak untuk memastikan potensi stunting dan mendapatkan saran perawatan yang tepat.";
                    listdesc.setText(descrip);
                    Method.loadImageBaby(listpic,listDataHistory.nik);
                    listName.setText(listDataHistory.NamaHistory);
                    listdate.setText(listDataHistory.tanggal);
                }
                else{
                    descrip = baby.label_stunting;
                    listdesc.setText(descrip);
                    Method.loadImageBaby(listpic,listDataHistory.nik);
                    listName.setText(listDataHistory.NamaHistory);
                    listdate.setText(listDataHistory.tanggal);
                }

            }

            @Override
            public void onError(Object error) {

            }
        });





        return view;
    }
}
