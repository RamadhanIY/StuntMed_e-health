package com.stuntmed.stuntmed.Profiles;

import static com.stuntmed.stuntmed.Databases.User.writeNewParents;
import static com.stuntmed.stuntmed.Databases.User.writeNewUser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.github.dhaval2404.imagepicker.ImagePicker;
//
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stuntmed.stuntmed.HomepageUser;
import com.stuntmed.stuntmed.Method;
import com.stuntmed.stuntmed.ProfileActivity;
import com.stuntmed.stuntmed.R;
import com.stuntmed.stuntmed.Validator;

import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileParents extends AppCompatActivity {

    TextInputEditText inputfullnama, inputnik, inputemail, inputphonenumber,inputaddress;
    AutoCompleteTextView inputcountry,inputgender;

    EditText inputdatebirth;

    ImageButton editpic;
    ShapeableImageView profilepic;

    String[] country = {"Indonesia","Amerika", "Jepang"};
    String[] gender = {"Laki-laki","Perempuan"};

    ArrayAdapter<String> adaptercountry, adaptergender;

    Uri uri;

    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();
    private static final String TAG = "EditProfileParents";

    int year,month,day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_parents);

        inputnik = findViewById(R.id.edit_NIK_parents);
        inputfullnama = findViewById(R.id.edit_fullname_parents);
        inputemail = findViewById(R.id.edit_email);
        inputphonenumber = findViewById(R.id.edit_phonenumber);
        inputcountry = findViewById(R.id.edit_country);
        inputgender = findViewById(R.id.edit_gender);
        inputaddress = findViewById(R.id.edit_address);
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
                ImagePicker.with(EditProfileParents.this)
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfileParents.this, new DatePickerDialog.OnDateSetListener() {
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
            writeNewParents(uri.toString(),null,inputfullnama.getText().toString(),inputemail.getText().toString(),inputgender.getText().toString(),inputaddress.getText().toString(),inputcountry.getText().toString(),inputphonenumber.getText().toString(),inputnik.getText().toString(),null);
            Intent intent = new Intent(this, HomepageUser.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
        Intent intent = new Intent(EditProfileParents.this, ProfileActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    private boolean checkInput() {
        if (uri == null) {
            Toast.makeText(getApplicationContext(),
                            "Please set your image!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (inputnik.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),
                            "Please enter your NIK!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        } else if (Validator.isValidNIK(inputnik.getText().toString()) == false) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your valid NIK!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (inputdatebirth.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your date of birth!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        } else if (Validator.isValidDate(inputdatebirth.getText().toString()) == false) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your valid date of birth!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (inputphonenumber.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your phone number!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        } else if (Validator.isValidPhoneNumber(inputphonenumber.getText().toString()) == false) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your valid phone number!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (inputcountry.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your country!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (inputgender.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your gender!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (inputaddress.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your address!",
                            Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        return true;
    }
}