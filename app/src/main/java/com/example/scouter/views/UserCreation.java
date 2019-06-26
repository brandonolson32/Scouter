package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentCallbacks2;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.scouter.R;
import com.example.scouter.entity.User;
import com.example.scouter.model.Model;
import com.example.scouter.viewmodels.EditUserViewModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * class to configure the player upon creation
 */
public class UserCreation extends AppCompatActivity {

    private static EditUserViewModel userViewModel;

    private EditText nameField;
    private EditText squatMaxField;
    private EditText benchMaxField;
    private EditText deadliftMaxField;
    private Button computeButton;

    private RequestQueue requestQueue;

    private final String baseUrl = "http://10.0.2.2:9080/myapi";
    private String url;

    private static List<UserCreation.IMemoryInfo> memInfoList =
            new ArrayList<UserCreation.IMemoryInfo>();

    public static abstract interface IMemoryInfo {
        public void goodTimeToReleaseMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        //don't compare with == as intermediate stages also can be reported,
        // always better to check >= or <=
        if (level >= ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW) {
            try {
                // Activity at the front will get earliest than activity at the
                // back
                for (int i = memInfoList.size() - 1; i >= 0; i--) {
                    try {
                        memInfoList.get(i).goodTimeToReleaseMemory();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

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

        computeButton = findViewById(R.id.compute_button);
        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameField.getText().toString().matches("")
                        || benchMaxField.getText().toString().matches("")
                        || deadliftMaxField.getText().toString().matches("")
                        || squatMaxField.getText().toString().matches("")) {
                    Toast.makeText(UserCreation.this, "No fields can be blank",
                            Toast.LENGTH_SHORT).show();
                } else if (Double.parseDouble(benchMaxField.getText().toString()) > Math.pow(10, 7)) {
                    Toast.makeText(UserCreation.this, "You cannot bench that much",
                            Toast.LENGTH_SHORT).show();
                } else if (Double.parseDouble(squatMaxField.getText().toString()) > Math.pow(10, 7)) {
                    Toast.makeText(UserCreation.this, "You cannot squat that much",
                            Toast.LENGTH_SHORT).show();
                } else if (Double.parseDouble(deadliftMaxField.getText().toString()) > Math.pow(10, 7)) {
                    Toast.makeText(UserCreation.this, "You cannot deadlift that much",
                            Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(nameField.getText().toString(),
                            Integer.parseInt(squatMaxField.getText().toString()),
                            Integer.parseInt(benchMaxField.getText().toString()),
                            Integer.parseInt(deadliftMaxField.getText().toString()));
                    userViewModel.addUser(user);
                    UserCreation.this.startActivity(new Intent(UserCreation.this,
                            ScouterDisplay.class));
                }
            }
        });
    }
}
