package com.example.socialapp.model;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ArrayListImageHome {
    public ArrayList<image> setListData() {
        ArrayList<image> arrayList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference colRef = db.collection("Image storage");
        List<String> listIdImageStorage = new ArrayList<>();



        colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        listIdImageStorage.add(doc.getId());
                    }
                    Log.d(TAG, "Complete get all ID document from Image storage collection");

                    for (int i = 0; i < listIdImageStorage.size(); i++) {
                        try {
                            colRef.document(listIdImageStorage.get(i))
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "Success");
                                                DocumentSnapshot doc = task.getResult();
                                                if (doc.exists()) {
                                                    Log.d(TAG, "Image information getting success");
                                                    String url = doc.getString("Image URL");
                                                    String topic = doc.getString("Topic");
                                                    String idImageStorage = doc.getId();
                                                    InputStream is = null;

                                                    try {
                                                        Log.d(TAG, url);
                                                        is = (InputStream) new URL(url).getContent();
                                                    } catch (Exception e) {
                                                        Log.d(TAG, "Excecption is=" + e);
                                                    }

                                                    if (is != null) {
                                                        Drawable d = Drawable.createFromStream(is, url);

                                                        arrayList.add(new image(d, idImageStorage, topic, url));
                                                    }
                                                }
                                                else {
                                                    Log.d(TAG, "Image information does not exist");
                                                }
                                            }
                                            else {
                                                Log.d(TAG, "Something's wrong");
                                            }
                                        }
                                    });
                        } catch (Exception e) {
                            Log.d(TAG, e.toString());
                        }
                    }
                }
                else {
                    Log.d(TAG, "Error getting document");
                }
            }
        });



        return arrayList;
    }
}
