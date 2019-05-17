package com.example.scouter.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.scouter.R;
import com.example.scouter.viewmodels.EditUserViewModel;

/**
 * class to configure the player upon creation
 */
public class UserCreation extends AppCompatActivity {

    private EditUserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private EditText nameField;

    private TextView squatMax;
    private TextView benchMax;
    private TextView deadliftMax;

    private int sm;
    private int bm;
    private int dm;

    private Spinner majorSpinner;

    private RequestQueue requestQueue;
}
