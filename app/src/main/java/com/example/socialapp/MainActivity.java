package com.example.socialapp;


import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getSupportActionBar().hide();

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginDialog("0123456789", "123456");
            }
        });
    }

    public void navigateToHomePage(String phoneNumber) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Init before Start Main Screen
        Intent intent = new Intent(MainActivity.this, MainScreen.class);
        Bundle b = new Bundle();
        b.putString("Phone number", phoneNumber);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }

    private void showLoginDialog(String phoneNumber, String password) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_login);
        Button btnLogin = dialog.findViewById(R.id.button_login);
        TextView textView = dialog.findViewById(R.id.signUpNow);

        ((EditText) dialog.findViewById(R.id.userPhoneNumber)).setText(phoneNumber);
        ((EditText) dialog.findViewById(R.id.password)).setText(password);
        dialog.show();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = ((EditText) dialog.findViewById(R.id.userPhoneNumber)).getText().toString();
                String password = ((EditText) dialog.findViewById(R.id.password)).getText().toString();
                loginUser(phoneNumber, password);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                showSignUpDialog();
            }
        });
    }

    private void loginUser(String phoneNumber, String password) {
        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Users").document("user_" + phoneNumber);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String passwordDb = document.getString("Password");
                        if (Objects.equals(password, passwordDb)) {
                            Toast.makeText(MainActivity.this,
                                            "Đăng nhập thành công",
                                            Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            navigateToHomePage(phoneNumber);
                        }
                        else {
                            Toast.makeText(MainActivity.this,
                                            "Sai mật mật khẩu, vui lòng thử lại!",
                                            Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Người dùng không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void showSignUpDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_signup);
        Button btnLogin = dialog.findViewById(R.id.button_Signup);

        dialog.show();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_phoneNumber = ((EditText) dialog.findViewById(R.id.numbersign)).getText().toString();
                String txt_password = ((EditText) dialog.findViewById(R.id.password)).getText().toString();
                String txt_name = ((EditText) dialog.findViewById(R.id.name)).getText().toString();
                String txt_id = ((EditText) dialog.findViewById(R.id.id)).getText().toString();

                if (TextUtils.isEmpty(txt_phoneNumber) ||
                    TextUtils.isEmpty(txt_password) ||
                    TextUtils.isEmpty(txt_name) ||
                    TextUtils.isEmpty(txt_id)) {

                    Toast.makeText(MainActivity.this,
                                    "Vui lòng điền hết các trường dữ liệu",
                                    Toast.LENGTH_SHORT).show();

                }
                else if (txt_phoneNumber.length() < 10 ||
                        !txt_phoneNumber.matches("[0-9]+")) {

                    Toast.makeText(MainActivity.this,
                            "Số điện thoại bạn nhập không hợp lệ",
                            Toast.LENGTH_SHORT).show();

                }
                else if (txt_password.length() < 6) {

                    Toast.makeText(MainActivity.this,
                                    "Độ dài mật khẩu tối thiểu là 6 kí tự",
                                    Toast.LENGTH_SHORT).show();
                    
                }
                else {

                    registerUser(txt_phoneNumber, txt_password, txt_name, txt_id);

                }
            }
        });
    }

    private void registerUser(String phoneNumber, String password, String name, String id) {

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Users").document("user_" + phoneNumber);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        Log.d(TAG, "Document exists!");
                        Toast.makeText(MainActivity.this,
                                "Người dùng đã tồn tại!\nVui lòng dùng số điện thoại khác!",
                                Toast.LENGTH_SHORT).show();

                    }
                    else {

                        Log.d(TAG, "Document does not exist!");
                        // Declare Variable:
                        //  - db: Database --> Used to access Database
                        //  - user: Create Hash Map to storage Information user and add them to Database
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        HashMap<String, Object> user = new HashMap<>();

                        // Initialize User's Information
                        user.put("Phone number", phoneNumber);
                        user.put("Password", password);
                        user.put("Name", name);
                        user.put("Citizen identification", id);
                        user.put("Profile avatar", "default");
                        user.put("Nickname", "");
                        user.put("Following", 0);
                        user.put("Follower", 0);
                        user.put("Like", 0);

                        db.collection("Users")
                                .document("user_" + phoneNumber)
                                .set(user)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            Toast.makeText(MainActivity.this,
                                                    "Chúc mừng bạn đã tạo tài khoản thành công",
                                                    Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                        dialog.dismiss();
                        showLoginDialog(phoneNumber, password);

                    }
                }
                else {

                    Log.d(TAG, "Failed with: ", task.getException());
                    Toast.makeText(MainActivity.this,
                            "Có vấn đề gì đó đã xảy ra, hãy thử lại",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}