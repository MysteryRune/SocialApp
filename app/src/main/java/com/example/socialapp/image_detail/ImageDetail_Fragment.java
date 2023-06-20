package com.example.socialapp.image_detail;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.socialapp.MainActivity;
import com.example.socialapp.ProfileOtherUser;
import com.example.socialapp.R;
import com.example.socialapp.model.image;

import java.io.Serializable;

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

        CircleImageView circleImageView;
        circleImageView = v.findViewById(R.id.avatar);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ProfileOtherUser.class));
            }
        });
        ImageView imageView = v.findViewById(R.id.imageView7);
        imageView.setImageResource(image.getImageId());

        ToggleButton toggleButton = v.findViewById(R.id.toggle);

        TextView numberLike = v.findViewById(R.id.numberLike);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    numberLike.setText("1");
                } else {
                    numberLike.setText("0");
                }
            }
        });

        return v;
    }

}