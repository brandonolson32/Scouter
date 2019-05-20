package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.scouter.R;
import com.example.scouter.viewmodels.EditUserViewModel;

public class ScouterDisplay extends AppCompatActivity {
    private EditUserViewModel userViewModel;

    private EditText pl;

    private RequestQueue requestQueue;

    @Override
    /*
      This function makes everything upon pressing the create button
      @param savedInstanceState The state of the saved game
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powerlevel);

        userViewModel = ViewModelProviders.of(this).get(EditUserViewModel.class);
        requestQueue = Volley.newRequestQueue(this);

        pl = findViewById(R.id.powerLevel);
//        pl.setText(UserCreation.getUserViewModel().getUser().getPowerLevel());
    }
}
