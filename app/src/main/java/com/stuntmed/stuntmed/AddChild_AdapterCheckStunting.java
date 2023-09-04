package com.stuntmed.stuntmed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stuntmed.stuntmed.Profiles.EditProfileBaby;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddChild_AdapterCheckStunting extends ArrayAdapter<DataAddChild> {
    private Context context;

    public AddChild_AdapterCheckStunting(@NonNull Context context, ArrayList<DataAddChild> dataAddChildArrayList) {
        super(context, R.layout.list_child, dataAddChildArrayList);
        this.context = context;  // Menyimpan referensi ke context
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        final DataAddChild dataAddChild = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_child, parent, false);
        }

        // Inisialisasi komponen view
        TextView NameChild = view.findViewById(R.id.NameChild);
        TextView umur = view.findViewById(R.id.umur);
        CircleImageView pic =  view.findViewById(R.id.photo);
        Method.loadImageBaby(pic,dataAddChild.nik);

        NameChild.setText(dataAddChild.NameChild);
        umur.setText(dataAddChild.umur);

        // Menambahkan OnClickListener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CheckStuntingActivity.class);
                intent.putExtra("NIK", dataAddChild.getNik()); // Asumsi DataAddChild memiliki method getNik()
                context.startActivity(intent);
            }
        });

        return view;
    }
}
