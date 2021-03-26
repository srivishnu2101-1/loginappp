package com.example.loginapplicationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private Button signInBtn;
    private EditText userName;
    private EditText password;
    private EditText email;
    private EditText confirmPassword;
    private CheckBox showPassword;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dataBaseHelper = new DataBaseHelper(this);

        signInBtn = findViewById(R.id.signInButton);
        userName = findViewById(R.id.registerUsername);
        password = findViewById(R.id.registerPassword);
        email = findViewById(R.id.registerEmailAddress);
        confirmPassword = findViewById(R.id.registerConfirmPassword);
        showPassword = findViewById(R.id.registerShowPasswordCheckBox);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().equals("") ||
                        email.getText().toString().equals("") ||
                        password.getText().toString().equals("") || confirmPassword.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }

                dataBaseHelper.addUser(email.getText().toString(),
                        userName.getText().toString(), password.getText().toString());

                Toast.makeText(RegisterActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    password.setTransformationMethod(new PasswordTransformationMethod());
                    confirmPassword.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    password.setTransformationMethod(null);
                    confirmPassword.setTransformationMethod(null);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}