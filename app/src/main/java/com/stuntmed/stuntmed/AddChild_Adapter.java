package com.stuntmed.stuntmed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stuntmed.stuntmed.Profiles.EditProfileBaby;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddChild_Adapter extends ArrayAdapter<DataAddChild> {
    private Context context;
    private CircleImageView profile_img;
    DataAddChild dataAddChild;

    public AddChild_Adapter(@NonNull Context context, ArrayList<DataAddChild> dataAddChildArrayList) {
        super(context, R.layout.list_child, dataAddChildArrayList);
        this.context = context;  // Menyimpan referensi ke context


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        dataAddChild = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_child, parent, false);
        }

        // Inisialisasi komponen view
        TextView NameChild = view.findViewById(R.id.NameChild);
        TextView umur = view.findViewById(R.id.umur);
        profile_img = view.findViewById(R.id.photo);

        Method.updateProfileImageBaby(profile_img, dataAddChild.getNik());

        NameChild.setText(dataAddChild.NameChild);
        umur.setText(dataAddChild.umur);

        // Menambahkan OnClickListener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditProfileBaby.class);
                intent.putExtra("NIK", dataAddChild.getNik()); // Asumsi DataAddChild memiliki method getNik()
                context.startActivity(intent);
            }
        });

        return view;
    }

    public CircleImageView getProfile_img() {
        return profile_img;
    }

    public String getNik() {
        return dataAddChild.getNik();
    }
}
