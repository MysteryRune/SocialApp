package com.example.socialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getSupportActionBar().hide();

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginDialog();
            }
        });
    }

    private void showLoginDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_login);
        Button btnLogin = dialog.findViewById(R.id.button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainScreen.class));
            }
        });

        TextView textView = dialog.findViewById(R.id.textView11);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                showSignUp();
            }
        });

        dialog.show();
    }

    private void showSignUp() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_signup);
        Button btnLogin = dialog.findViewById(R.id.button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainScreen.class));
            }
        });
        dialog.show();
    }
}