package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    private ImageView profileImageView;
    private TextView usernameText;
    private TextView birthdateTextView;
    private TextView cityTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        profileImageView = findViewById(R.id.profileImageView);
        usernameText = findViewById(R.id.usernameTextView);
        birthdateTextView = findViewById(R.id.birthdateTextView);
        cityTextView = findViewById(R.id.cityTextView);

        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        String username = sharedPreferences.getString("username", "Не указано");
        String birthdate = sharedPreferences.getString("birthdate", "Не указана");
        String city = sharedPreferences.getString("city", "Не указан");
        String photoUrl = sharedPreferences.getString("photoUrl", "");


        usernameText.setText("Имя: " + username);
        birthdateTextView.setText("Дата рождения: " + birthdate);
        cityTextView.setText("Город: " + city);

        // Load image with Picasso
        if (!photoUrl.isEmpty()) {
            Picasso.get().load(photoUrl).into(profileImageView);
        }
    }
}