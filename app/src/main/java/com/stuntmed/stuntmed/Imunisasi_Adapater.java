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

public class Imunisasi_Adapater extends ArrayAdapter<ListData_Imunisasi> {
    public Imunisasi_Adapater(@NonNull Context context, ArrayList<ListData_Imunisasi> dataImunisasiArrayList) {
        super(context, R.layout.list_imunisasi, dataImunisasiArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListData_Imunisasi listDataImunisasi = getItem(position);

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_imunisasi, parent, false);
        }
        TextView Listbulan = view.findViewById(R.id.bulan);
        TextView Listvaksin = view.findViewById(R.id.vaksin);
        TextView Listdosis = view.findViewById(R.id.dosis);

        Listbulan.setText(listDataImunisasi.bulan);
        Listvaksin.setText(listDataImunisasi.vaksin);
        Listdosis.setText(listDataImunisasi.dosis);
        return view;
    }
}
