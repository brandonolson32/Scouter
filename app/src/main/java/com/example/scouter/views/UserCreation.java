package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.scouter.entity.User;
import com.example.scouter.viewmodels.EditUserViewModel;

/**
 * class to configure the player upon creation
 */
public class UserCreation extends AppCompatActivity {

    private static EditUserViewModel userViewModel;

    private EditText nameField;
    private EditText squatMaxField;
    private EditText benchMaxField;
    private EditText deadliftMaxField;

    private int sm;
    private int bm;
    private int dm;

    private RequestQueue requestQueue;

    private final String baseUrl = "http://10.0.2.2:9080/myapi";
    private String url;

    @Override
    /*
      This function makes everything upon pressing the create button
      @param savedInstanceState The state of the saved game
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.name_field);
        squatMaxField = findViewById(R.id.squat_max_field);
        benchMaxField = findViewById(R.id.bench_max_field);
        deadliftMaxField = findViewById(R.id.deadlift_max_field);

        userViewModel = ViewModelProviders.of(this).get(EditUserViewModel.class);

        requestQueue = Volley.newRequestQueue(this);
    }

    /**
     * This function allows things to happen when the create button is pressed
     * @param view The games current view
     */
    public void onCreatePressed(View view) {
        User user = new User(nameField.getText().toString(),
                Integer.parseInt(squatMaxField.getText().toString()),
                Integer.parseInt(benchMaxField.getText().toString()),
                Integer.parseInt(deadliftMaxField.getText().toString()));
        System.out.println("User created");
        userViewModel.addUser(user);

        Button computeButton = findViewById(R.id.compute_button);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserCreation.this, ScouterDisplay.class));
            }
        });
    }

    public static EditUserViewModel getUserViewModel() {
        return userViewModel;
    }

//        addPlayer();
//    } else {
//        Log.i("MyActivity", "Pleas make sure you've used all your skills!");
//    }
}
