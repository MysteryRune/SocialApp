package com.example.socialapp.image_detail;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.socialapp.MainActivity;
import com.example.socialapp.ProfileOtherUser;
import com.example.socialapp.R;
import com.example.socialapp.model.image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class ImageDetail_Fragment extends Fragment {

    private static final String ARG_IMAGE = "image";
    private com.example.socialapp.model.image image;



    public ImageDetail_Fragment() {
        // Required empty public constructor
    }
    public static ImageDetail_Fragment newInstance(image image) {
        ImageDetail_Fragment fragment = new ImageDetail_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image = (image) getArguments().getSerializable(ARG_IMAGE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_detail_, container, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Image storage").document(image.getIdImageStorage());

        CircleImageView circleImageView;
        circleImageView = v.findViewById(R.id.avatar);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ProfileOtherUser.class));
            }
        });
        ImageView imageView = v.findViewById(R.id.imageView7);
        Glide.with(this)
                .load(image.getURL())
                .into(imageView);

        ToggleButton toggleButton = v.findViewById(R.id.toggle);

        TextView numberLike = v.findViewById(R.id.numberLike);

        numberLike.setText(image.getLike());


        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    Map<String, Object> updates = new HashMap<>();
                    int number = Integer.parseInt(image.getLike()) + 1;
                    updates.put("Like", number);

                    docRef.update(updates)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "Document updated successfully");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Error updating document: " + e.getMessage());
                                }
                            });
                        numberLike.setText(String.valueOf(number));

                } else {
                    Map<String, Object> updates = new HashMap<>();
                    int number = Integer.parseInt(image.getLike());
                    updates.put("Like", number);

                    docRef.update(updates)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "Document updated successfully");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Error updating document: " + e.getMessage());
                                }
                            });
                    numberLike.setText(image.getLike());
                }
            }
        });

        return v;
    }

}