package com.saunak.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public class MyProfileActivity extends AppCompatActivity {
    Button btn;
    TextView txtName, txtEmail;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        btn = findViewById(R.id.logOut);
        txtName = findViewById(R.id.userName);
        txtEmail = findViewById(R.id.userEmail);
        image = findViewById(R.id.imgUser);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        String userName = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getDisplayName();
        String userEmail = firebaseAuth.getCurrentUser().getEmail();


        if (firebaseAuth.getCurrentUser().getPhotoUrl() != null) {
            Glide.with(this).load(firebaseAuth.getCurrentUser().getPhotoUrl()).into(image);
        }
        txtName.setText(userName);
        txtEmail.setText(userEmail);

        btn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MyProfileActivity.this, LoginActivity.class));
            finish();
        });

    }
}