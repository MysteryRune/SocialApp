package com.example.socialapp.model;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.socialapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ArrayListImageSave {
    public interface ImageDataCallback {
        void onDataLoaded(ArrayList<image> imageData);
    }

    public void setListData(ArrayListImageSave.ImageDataCallback callback) {
        ArrayList<image> arrayList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference colRef = db.collection("Image storage");

        colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        String url = doc.getString("Image URL");
                        String topic = doc.getString("Topic");
                        String like = String.valueOf(doc.get("Like"));
                        String idImageStorage = doc.getId();

                        arrayList.add(new image(null, idImageStorage, topic, url, like));
                    }
                    Log.d(TAG, "Complete get all ID document from Image storage collection");
                    callback.onDataLoaded(arrayList);
                } else {
                    Log.d(TAG, "Error getting document");
                }
            }
        });
    }
}
