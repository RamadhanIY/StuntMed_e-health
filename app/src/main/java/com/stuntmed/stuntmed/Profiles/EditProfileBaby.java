package com.stuntmed.stuntmed.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.stuntmed.stuntmed.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditProfileBaby extends AppCompatActivity {

    private AutoCompleteTextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private static final String TAG = "EditProfileBaby";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_baby_remake);

        AutoCompleteTextView dateAutoComplete = findViewById(R.id.dateAutoCompletes);

        // Bangun date picker
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Pilih tanggal");
        final MaterialDatePicker<Long> materialDatePicker = builder.build();

        // Tampilkan date picker saat AutoCompleteTextView ditekan
        dateAutoComplete.setOnClickListener(v -> materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER"));

        // Tetapkan listener untuk saat tanggal dipilih
        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            // Format dan set tanggal yang dipilih ke AutoCompleteTextView
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String selectedDate = simpleDateFormat.format(new Date(selection));
            dateAutoComplete.setText(selectedDate);
        });
    }


}
