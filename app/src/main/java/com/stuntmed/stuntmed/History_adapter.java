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

import java.util.ArrayList;

public class History_adapter extends ArrayAdapter<ListData_History>{

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
        ImageView listpic = view.findViewById(R.id.photo);
        TextView listName = view.findViewById(R.id.NamaHistory);
        TextView listDesc = view.findViewById(R.id.desc);
        TextView listdate = view.findViewById(R.id.tangggal);

        listpic.setImageResource(listDataHistory.Photo);
        listName.setText(listDataHistory.NamaHistory);
        listDesc.setText(listDataHistory.desc);
        listdate.setText(listDataHistory.tanggal);

        return view;
    }
}
