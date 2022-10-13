package com.example.monitoring.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.monitoring.R;
import com.example.monitoring.api.ApiInterface;
import com.example.monitoring.api.ApiServer;
import com.example.monitoring.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    String user, pass, confirmpass;
    EditText username, password, confirmpassword;
    AppCompatButton btnlogin, btnregis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.tl_username);
        password = findViewById(R.id.tl_password);
        confirmpassword = findViewById(R.id.tl_confirmpassword);
        btnlogin = findViewById(R.id.btn_login2);
        btnregis = findViewById(R.id.btn_rgs);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();
                confirmpass = confirmpassword.getText().toString();
                if (confirmpass.equals(pass)) {
                    moveToRegister(user, pass);

                } else {
                    password.setError("Pasword tidak sama");
                }
            }

        });
    }

    private void moveToRegister(String user, String pass) {
        ApiInterface apiInterface = ApiServer.getClient().create(ApiInterface.class);
        Call<ResponseLogin> call = apiInterface.register(user, pass);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Berhasil mendaftar", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Gagal mendaftar", Toast.LENGTH_SHORT).toString();
;
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).toString();

            }
        });

    }
}
