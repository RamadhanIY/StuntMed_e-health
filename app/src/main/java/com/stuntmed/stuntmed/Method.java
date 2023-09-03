package com.stuntmed.stuntmed;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stuntmed.stuntmed.Databases.Baby;
import com.stuntmed.stuntmed.Databases.User;

public class Method {
    public static String database_url = "https://stuntmed-default-rtdb.asia-southeast1.firebasedatabase.app";
    private static Object obj;

    public static DatabaseReference getDatabaseReference(String reference_path){
      return FirebaseDatabase.getInstance(Method.database_url).getReference(reference_path);
    };

    public static boolean setValueOnDatabase(String refererence_path, Object value){
        getDatabaseReference(refererence_path).setValue(value);

        return true;
    };

    private static void setObj(Object obj){
        Method.obj =  obj;
    }

    private static void readData(String refererence_path){
        DatabaseReference mDatabase = getDatabaseReference(refererence_path);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Method.setObj(snapshot.getValue(Baby.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static Object getValueOnDatabase(String refererence_path){
//        getDatabaseReference(refererence_path).setValue(value);
//
        readData(refererence_path);

        return Method.obj;
    };

    public static FirebaseUser getCurrentUser(){
        try {
            return FirebaseAuth.getInstance().getCurrentUser();
        }catch (Exception e){
            return null;
        }
    }
}