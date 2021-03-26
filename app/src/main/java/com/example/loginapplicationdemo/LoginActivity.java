package com.example.loginapplicationdemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private TextView signUpText;
    private EditText email;
    private EditText password;
    private Button login;
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;
    private CheckBox showPassword;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dataBaseHelper = new DataBaseHelper(this);
        database = dataBaseHelper.getReadableDatabase();

        signUpText = findViewById(R.id.signUpText);
        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        showPassword = findViewById(R.id.loginShowPasswordCheckBox);
        login = findViewById(R.id.loginButton);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_LONG).show();
                    return;
                }

                cursor = database.rawQuery("SELECT * FROM " + DataBaseHelper.USER_TABLE + " WHERE "
                                + DataBaseHelper.COLUMN_EMAIL + " =? AND " + DataBaseHelper.COLUMN_PASSWORD + " =?",
                        new String[]{email.getText().toString(), password.getText().toString()});

                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();

                        Toast.makeText(LoginActivity.this, "Logged In succesfully!",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid username or password!",
                                Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    password.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    password.setTransformationMethod(null);
                }
            }
        });
    }
}