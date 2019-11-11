package com.yol.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    Button login = null;
    EditText username, password;
    Button register = null;
    Button create = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        login = (Button) findViewById(R.id.loginBtn);
        register = (Button) findViewById(R.id.registerBtn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), username.getText(), Toast.LENGTH_LONG).show();
                volley("login");
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volley("register");
            }
        });

    }

    public void volley(String method){
        final String api_request = method;
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("http://172.16.11.8:3000/api/%s",api_request);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if ((boolean) jsonObject.get("auth")){
                                Intent launchactivity= new Intent(MainActivity.this, Information.class);
                        startActivity(launchactivity);
                            }else{
                                Toast.makeText(getApplicationContext(), "Account not found!", Toast.LENGTH_LONG).show();
                            }
                        }catch (JSONException err){
                            System.out.println(err);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                Toast.makeText(getApplicationContext(), api_request+" Error!", Toast.LENGTH_LONG).show();

            }
        })
            {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("username",username.getText().toString() );
                    params.put("password",password.getText().toString() );
                    return params;
                }
            };

        queue.add(stringRequest);
    }


}