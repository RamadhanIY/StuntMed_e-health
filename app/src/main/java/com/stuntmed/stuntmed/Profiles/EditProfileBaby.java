package com.stuntmed.stuntmed.Profiles;

import static com.stuntmed.stuntmed.Databases.Baby.updateBaby;
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
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stuntmed.stuntmed.HomepageUser;
import com.stuntmed.stuntmed.ListChild;
import com.stuntmed.stuntmed.Method;
import com.stuntmed.stuntmed.R;
import com.stuntmed.stuntmed.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileBaby extends AppCompatActivity {

    TextInputEditText inputfullnama, inputnik;
    AutoCompleteTextView inputcountry,inputgender;

    EditText inputdatebirth;

    Uri uri;

    ImageButton editpic;
    ShapeableImageView profilepic;

    TextInputLayout inputdatebirths;

    String[] country = {"Indonesia","Amerika", "Jepang"};
    String[] gender = {"Laki-laki","Perempuan"};

    ArrayAdapter<String> adaptercountry, adaptergender;

    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();
    private static final String TAG = "EditProfileParents";

    int year,month,day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_baby_remake);


        inputnik = findViewById(R.id.edit_NIK_baby);
        inputfullnama = findViewById(R.id.edit_fullname_baby);
        inputcountry = findViewById(R.id.edit_country);
        inputgender = findViewById(R.id.edit_gender);
        inputdatebirth = findViewById(R.id.edit_date_birth_baby);

        adaptercountry = new ArrayAdapter<>(this,R.layout.list_country,country);
        adaptergender = new ArrayAdapter<>(this,R.layout.list_gender,gender);

        Button submitButton = (Button) findViewById(R.id.button_submit);
        ImageView backButton = (ImageView) findViewById(R.id.backButton_profiles);
        editpic = findViewById(R.id.edit_pic);
        profilepic = findViewById(R.id.profilepic_baby);

        editpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(EditProfileBaby.this)
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
                        calendar.set(i, i1, i2);  // Set calendar dengan tanggal yang dipilih
                        inputdatebirth.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                }, year, month, day);
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
            if (checkInput()){
                String nik = getIntent().getStringExtra("NIK");
                updateBaby(nik,uri.toString().toString(),inputfullnama.getText().toString(),inputdatebirth.getText().toString(),inputcountry.getText().toString(),inputgender.getText().toString(),null,null,null,null);

                Method.uploadPictBaby(uri, nik);

                Toast.makeText(this, "Berhasil update data!", Toast.LENGTH_SHORT).show();
                Log.d("debuging", "Berhasil update data!");
                finish();
//                Intent intent = new Intent(this, HomepageUser.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        profilepic.setImageURI(uri);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    //Checker
    private boolean checkInput() {
        if (uri == null) {
            Toast.makeText(getApplicationContext(),
                            "Please set your baby image!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (inputfullnama.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your baby fullname!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (inputdatebirth.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your baby date of birth!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        } else if (Validator.isValidDate(inputdatebirth.getText().toString()) == false) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your valid baby date of birth!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (inputcountry.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your baby country!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (inputgender.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your baby gender!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        }


        return true;
    }


}
