package com.example.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText birthdateEditText;
    private EditText cityEditText;
    private EditText photoUrlEditText;
    private Button saveButton;
    private TextView resultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        if (sharedPreferences.contains("username") &&
                sharedPreferences.contains("birthdate") &&
                sharedPreferences.contains("city") &&
                sharedPreferences.contains("photoUrl")) {

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_main);


        usernameText = findViewById(R.id.editTextUserName);
        birthdateEditText = findViewById(R.id.editTextBirthdate);
        cityEditText = findViewById(R.id.editTextCity);
        photoUrlEditText = findViewById(R.id.editTextPhotoUrl);
        saveButton = findViewById(R.id.buttonSaveData);
        resultData = findViewById(R.id.tvUsername);



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }

    private void saveData() {
        String username = usernameText.getText().toString();
        String birthdate = birthdateEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String photoUrl = photoUrlEditText.getText().toString();

        if (!username.isEmpty() && !birthdate.isEmpty() && !city.isEmpty() && !photoUrl.isEmpty()) {
            SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("username", username);
            editor.putString("birthdate", birthdate);
            editor.putString("city", city);
            editor.putString("photoUrl", photoUrl);
            editor.apply();

            resultData.setText("Данные сохранены. Перейти в профиль?");

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

    }
    private void loadUsername() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyUsername", MODE_PRIVATE);

        String username = sharedPreferences.getString("username", null);
        if (username!=null){
            resultData.setText("С возращением, "+ username);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username");
        editor.commit();
    }
}