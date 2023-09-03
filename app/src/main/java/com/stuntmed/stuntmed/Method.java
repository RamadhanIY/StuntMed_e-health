package com.stuntmed.stuntmed;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.stuntmed.stuntmed.Databases.Baby;
import com.stuntmed.stuntmed.Databases.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class Method {
    public static String database_url = "https://stuntmed-default-rtdb.asia-southeast1.firebasedatabase.app";
    private static Object obj;

//  FIREBASE STORAGE
public static void updateProfileImage(CircleImageView image_profile){
    FirebaseStorage
            .getInstance("gs://stuntmed.appspot.com")
            .getReference(Method.getCurrentUser().getUid()+"/profile_image.jpg")
            .getDownloadUrl()
            .addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
//                        image_profile.setImageURI(uri);
                    Picasso.get().load(uri).into(image_profile);
                    Log.d("debuging", "Berhasil update profile image");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("debuging", "gagal update profile image");
                    Log.d("debuging", e.toString());
                }
            });
}
    public static void uploadPict (Uri file){
        //      FIREBASE STORAGE
        StorageReference storage = FirebaseStorage.getInstance("gs://stuntmed.appspot.com").getReference();

        UploadTask uploadTask = storage
                .child(Method.getCurrentUser().getUid())
                .child("profile_image.jpg")
                .putFile(file);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d("debuging", "gagal upload profile image");
                Log.d("debuging", exception.toString());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Log.d("debuging", "sukses upload profile image");
            }
        });
    }

//    END OF FIREBASE STORAGE

    public static DatabaseReference getDatabaseReference(String reference_path){
      return FirebaseDatabase.getInstance(Method.database_url).getReference(reference_path);
    };

    public static boolean setValueOnDatabase(String refererence_path, Object value){
        getDatabaseReference(refererence_path).setValue(value);

        return true;
    };

    public static FirebaseUser getCurrentUser(){
        try {
            return FirebaseAuth.getInstance().getCurrentUser();
        }catch (Exception e){
            return null;
        }
    }
}