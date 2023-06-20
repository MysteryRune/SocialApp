package com.example.socialapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.socialapp.nav.FollowingFragment;
import com.example.socialapp.nav.HomeFragment;
import com.example.socialapp.nav.ProfileFragment;
import com.example.socialapp.nav.SearchFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public class MainScreen extends AppCompatActivity {

    String phoneNumber;
    BottomNavigationView navigationView;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        getSupportActionBar().hide();

        Bundle b = getIntent().getExtras();
        phoneNumber = b.getString("Phone number");
        navigationView = findViewById(R.id.nav_bottom);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainscreen, new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                if(item.getItemId() == R.id.nav_home){
                    fragment = new HomeFragment();
                } else if (item.getItemId() == R.id.nav_search) {
                    fragment = new SearchFragment();
                } else if (item.getItemId() == R.id.nav_post) {
//                        showPostImage();
                    pickImageFromGallery();
                        return true;
                } else if (item.getItemId() == R.id.nav_following) {
                    fragment = new FollowingFragment();
                } else if (item.getItemId() == R.id.nav_profile) {
                    fragment = new ProfileFragment();
                    Bundle b = new Bundle();
                    b.putString("Phone number", phoneNumber);
                    fragment.setArguments(b);
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.mainscreen, fragment).commit();

                return true;
            }
        });


    }

    public void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            showPostImage(selectedImage);
        }
    }

    private void showPostImage(Uri selectedImage) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_post);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        ((ImageView) dialog.findViewById(R.id.imagePost)).setImageURI(selectedImage);

        Button button;
        button = dialog.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage_updateOnDatabase(selectedImage);
                dialog.dismiss();
            }
        });
    }

    private void uploadImage_updateOnDatabase(Uri selectedImage) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference colRef = db.collection("Users")
                                       .document("user_" + phoneNumber)
                                       .collection("Image posted");

        colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    String date_str;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        date_str = dtf.format(now);
                    }
                    else {
                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
                        Date date = new Date();
                        date_str = formatter.format(date);
                    }

                    QuerySnapshot query = task.getResult();
                    if (query.isEmpty()) {
                        //Upload first post
                        HashMap<String, Object> post = new HashMap<String, Object>();
                        post.put("Date", date_str);
                        colRef.document("1")
                                .set(post)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        String idImageStorage = date_str + "_user_" + phoneNumber + "_1";
                                        String topic = ((EditText) dialog.findViewById(R.id.topic)).getText().toString();

                                        writeInfoImageOnImageStorageCollection(idImageStorage, topic, "https://...");

                                        Log.d(TAG, "Đã cập nhật bài post");
                                        Toast.makeText(MainScreen.this,
                                                        "Đăng bài viết thành công",
                                                        Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "Đã xảy ra lỗi");
                                        Toast.makeText(MainScreen.this,
                                                "Khởi tạo bài post thất bại",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else {
                        AggregateQuery countQuery = colRef.count();
                        countQuery.get(AggregateSource.SERVER).addOnCompleteListener(new OnCompleteListener<AggregateQuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<AggregateQuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    AggregateQuerySnapshot snapshot = task.getResult();
                                    long count = snapshot.getCount();
                                    String nextID = String.valueOf(count + 1);

                                    // Tạo và đăng bài post --> Ghi dữ liệu lên cơ sở dữ liệu
                                    HashMap<String, Object> post = new HashMap<String, Object>();
                                    post.put("Date", date_str);
                                    colRef.document(nextID)
                                            .set(post)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    String idImageStorage = date_str + "_user_" + phoneNumber + "_" + nextID;
                                                    String topic = ((EditText) dialog.findViewById(R.id.topic)).getText().toString();

                                                    writeInfoImageOnImageStorageCollection(idImageStorage, topic, "https://...");

                                                    Log.d(TAG, "Đã cập nhật bài post");
                                                    Toast.makeText(MainScreen.this,
                                                            "Đăng bài viết thành công",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.d(TAG, "Đã xảy ra lỗi");
                                                    Toast.makeText(MainScreen.this,
                                                            "Khởi tạo bài post thất bại",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }
                        });

                    }
                }
            }
        });
    }

    public void writeInfoImageOnImageStorageCollection(String idImageStorage, String topic, String url) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference colRef = db.collection("Image storage");
        DocumentReference docRef = colRef.document(idImageStorage);

        HashMap<String, Object> infoPost = new HashMap<>();
        infoPost.put("Topic", topic);
        infoPost.put("Image URL", url);
        infoPost.put("Like", 0);

        docRef.set(infoPost)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Ghi thông tin của ảnh vào Image storage collection thành công");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Ghi thông tin của ảnh vào Image storage collection thất bại");
                    }
                });
    }
}