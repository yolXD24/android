package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login = null;
    EditText username = null;
    EditText password = null;
    Button register = null;
    Button create = null;
    Button back = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        login = (Button) findViewById(R.id.btn_login);
        create = (Button) findViewById(R.id.btn_registerPage);
        register = (Button) findViewById(R.id.btn_register);
        username = (EditText) findViewById(R.id.text_username);
        password = (EditText) findViewById(R.id.text_password);
        back = (Button) findViewById(R.id.btn_back);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), username.getText(), Toast.LENGTH_LONG).show();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });


    }


}
