package com.stuntmed.stuntmed;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.se.omapi.Session;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Method {
    public static String database_url = "https://stuntmed-default-rtdb.asia-southeast1.firebasedatabase.app";
    private static String result = " ";



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

    public static void updateProfileImageBaby(CircleImageView image_profile, String nik){
        FirebaseStorage
                .getInstance("gs://stuntmed.appspot.com")
                .getReference(Method.getCurrentUser().getUid()+"/baby_"+nik+".jpg")
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

    public static void uploadPictBaby (Uri file, String nik){
        StorageReference storage = FirebaseStorage.getInstance("gs://stuntmed.appspot.com").getReference();

        UploadTask uploadTask = storage
                .child(Method.getCurrentUser().getUid())
                .child("baby_"+nik+".jpg")
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

//    Requests HTML
    public static void predict_hc(Context this_activity, String gender, String age, String hc, VolleyCallback callback){
        String url = "https://benngki.pythonanywhere.com/predict-hc";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String data = jsonObject.getString("result");
                            Log.d("debuging", data);

                            callback.onSuccess(data);
                        } catch (JSONException e) {
                            Log.d("debuging", "JSON exception");
                            Log.d("debuging", e.toString());
                            throw new RuntimeException(e);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());

                        Log.d("debuging", error.toString());
                        Toast.makeText(this_activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("gender", gender);
                params.put("hc", hc);
                params.put("age", age);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(this_activity);
        queue.add(stringRequest);
    }

    public static void predict_weight(Context this_activity, String gender, String age, String weight, VolleyCallback callback){
        String url = "https://benngki.pythonanywhere.com/predict-weight";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String data = jsonObject.getString("result");
                            Log.d("debuging", data);

                            callback.onSuccess(data);
                        } catch (JSONException e) {
                            Log.d("debuging", "JSON exception");
                            Log.d("debuging", e.toString());
                            throw new RuntimeException(e);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());

                        Log.d("debuging", error.toString());
                        Toast.makeText(this_activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("gender", gender);
                params.put("weight", weight);
                params.put("age", age);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(this_activity);
        queue.add(stringRequest);
    }

    public static void predict_height(Context this_activity, String gender, String age, String height, VolleyCallback callback){
        String url = "https://benngki.pythonanywhere.com/predict-height";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String data = jsonObject.getString("result");
                            Log.d("debuging", data);

                            callback.onSuccess(data);
                        } catch (JSONException e) {
                            Log.d("debuging", "JSON exception");
                            Log.d("debuging", e.toString());
                            throw new RuntimeException(e);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());

                        Log.d("debuging", error.toString());
                        Toast.makeText(this_activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("gender", gender);
                params.put("height", height);
                params.put("age", age);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(this_activity);
        queue.add(stringRequest);
    }
//    End of Requests HTML

    public interface VolleyCallback {
        void onSuccess(Object result);
        void onError(Object error);
    }

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

    //convert gender
    public static String convertgender(String gender){
        if(gender.equals("Laki-laki")){
            return "1";
        }
        else{
            return "0";
        }
    }

}
