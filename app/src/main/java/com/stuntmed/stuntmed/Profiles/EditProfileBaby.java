package com.stuntmed.stuntmed.Profiles;

import static com.stuntmed.stuntmed.Databases.Baby.writeNewBaby;
import static com.stuntmed.stuntmed.Databases.User.writeNewUser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stuntmed.stuntmed.HomepageUser;
import com.stuntmed.stuntmed.Method;
import com.stuntmed.stuntmed.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileBaby extends AppCompatActivity {

    TextInputEditText inputfullnama, inputnik;
    AutoCompleteTextView inputcountry,inputgender;

    EditText inputdatebirth;

    ImageButton editpic;
    CircleImageView profilepic;

    String[] country = {"Indonesia","Amerika", "Jepang"};
    String[] gender = {"Laki-laki","Perempuan"};

    ArrayAdapter<String> adaptercountry, adaptergender;

    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();
    private static final String TAG = "EditProfileParents";

    int year,month,day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_parents);

        inputnik = findViewById(R.id.edit_NIK_parents);
        inputfullnama = findViewById(R.id.edit_fullname_parents);
        inputcountry = findViewById(R.id.edit_country);
        inputgender = findViewById(R.id.edit_gender);
        inputdatebirth = findViewById(R.id.edit_date_birth_parents);

        adaptercountry = new ArrayAdapter<>(this,R.layout.list_country,country);
        adaptergender = new ArrayAdapter<>(this,R.layout.list_gender,gender);

        Button submitButton = (Button) findViewById(R.id.button_submit);
        ImageView backButton = (ImageView) findViewById(R.id.backButton_profiles);
        editpic = findViewById(R.id.edit_pic);
        profilepic = findViewById(R.id.profilepic_parents);

        editpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(EditProfileBaby.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        //Calendar
        final Calendar calendar = Calendar.getInstance();
        inputdatebirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfileBaby.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        inputdatebirth.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        //Country
        inputcountry.setAdapter(adaptercountry);
        inputcountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String country = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getApplicationContext(),"Country" + country,Toast.LENGTH_SHORT).show();
            }
        });

        inputgender.setAdapter(adaptergender);
        inputgender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String country = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getApplicationContext(),"gender" + country,Toast.LENGTH_SHORT).show();
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        submitButton.setOnClickListener(view -> {
            writeNewBaby(inputnik.getText().toString(),inputfullnama.getText().toString(),inputdatebirth.getText().toString(),inputcountry.getText().toString(),inputgender.getText().toString(),null,null,null);
            Intent intent = new Intent(this, HomepageUser.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        profilepic.setImageURI(uri);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomepageUser.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


}
