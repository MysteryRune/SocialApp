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

import java.util.ArrayList;
import java.util.List;

public class ArrayListImageSave {
    public ArrayList<image> setListData(String phoneNumber) {
        ArrayList<image> arrayList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference colRef = db.collection("Users");
        DocumentReference docRef = colRef.document("user_" + phoneNumber);
        List<String> listIdImageStorage = new ArrayList<>();

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Nothing's happening when try to get document");
                    DocumentSnapshot doc = task.getResult();
                    if (doc.exists()) {
                        Log.d(TAG, "User's document getting successful");


                    }
                    else{
                        Log.d(TAG, "User's document getting failed");
                    }
                }
                else {
                    Log.d(TAG, "Something's wrong");
                }
            }
        });

//        colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot doc : task.getResult()) {
//                        listIdImageStorage.add(doc.getId());
//                    }
//                    Log.d(TAG, "Complete get all ID document from Image storage collection");
//
//                    for (int i = 0; i < listIdImageStorage.size(); i++) {
//                        try {
//                            colRef.document(listIdImageStorage.get(i))
//                                    .get()
//                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                            if (task.isSuccessful()) {
//                                                Log.d(TAG, "Success");
//                                                DocumentSnapshot doc = task.getResult();
//                                                if (doc.exists()) {
//                                                    Log.d(TAG, "Image information getting success");
//                                                    String url = doc.getString("Image URL");
//                                                    String topic = doc.getString("Topic");
//                                                    String idImageStorage = doc.getId();
//                                                    InputStream is;
//
//                                                    try {
//                                                        is = (InputStream) new URL(url).getContent();
//                                                    } catch (IOException e) {
//                                                        throw new RuntimeException(e);
//                                                    }
//                                                    Drawable d = Drawable.createFromStream(is, idImageStorage);
//
//                                                    arrayList.add(new image(d, idImageStorage, topic));
//                                                }
//                                                else {
//                                                    Log.d(TAG, "Image information does not exist");
//                                                }
//                                            }
//                                            else {
//                                                Log.d(TAG, "Something's wrong");
//                                            }
//                                        }
//                                    });
//                        } catch (Exception e) {
//                            Log.d(TAG, e.toString());
//                        }
//                    }
//                }
//                else {
//                    Log.d(TAG, "Error getting document");
//                }
//            }
//        });


        return arrayList;
    }
}
