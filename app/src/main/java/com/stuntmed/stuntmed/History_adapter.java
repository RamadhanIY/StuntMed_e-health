package com.stuntmed.stuntmed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        TextView listName = view.findViewById(R.id.NamaHistory);
        TextView listDesc = view.findViewById(R.id.desc);

        listName.setText(listDataHistory.NamaHistory);
        listDesc.setText(listDataHistory.desc);

        return view;
    }
}
