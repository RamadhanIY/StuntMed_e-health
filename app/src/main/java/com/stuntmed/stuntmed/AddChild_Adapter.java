package com.stuntmed.stuntmed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AddChild_Adapter extends ArrayAdapter<DataAddChild> {

    public AddChild_Adapter(@NonNull Context context, ArrayList<DataAddChild>dataAddChildArrayList) {
        super(context, R.layout.list_child, dataAddChildArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        DataAddChild dataAddChild = getItem(position);

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_child, parent, false);
        }

        ImageView photo = view.findViewById(R.id.photo);
        TextView NameChild = view.findViewById(R.id.NameChild);
        TextView umur = view.findViewById(R.id.umur);

        return view;
    }
}
