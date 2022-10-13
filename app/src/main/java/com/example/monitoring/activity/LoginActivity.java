package com.example.monitoring.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.monitoring.R;
import com.example.monitoring.api.ApiInterface;
import com.example.monitoring.api.ApiServer;
import com.example.monitoring.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    String textUser, textPass;
    EditText user, pass;
    AppCompatButton btnlogin, btnregis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        btnlogin = findViewById(R.id.btn_login);
        btnregis = findViewById(R.id.btn_rgs2);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textUser = user.getText().toString();
                textPass = pass .getText().toString();

                if(user.equals("")){
                    user.setError("mohon diisi");
                }else if(pass.equals("")){
                    pass.setError("mohon diisi");
                }else{
                    moveToLogin(textUser,textPass);
                }
            }
        });
        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

            }
        });

    }
    private void moveToLogin(String textUser, String textPass){
        ApiInterface apiInterface = ApiServer.getClient().create(ApiInterface.class);
        Call<ResponseLogin> responseLoginCall = apiInterface.login(textUser, textPass);
        responseLoginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

            }
        });
    }
}