package com.example.scouter.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.scouter.R;
import com.example.scouter.viewmodels.EditUserViewModel;

public class ScouterDisplay extends AppCompatActivity {
    private EditUserViewModel model;

    private TextView pl;

    @Override
    /*
      This function makes everything upon pressing the create button
      @param savedInstanceState The state of the saved game
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powerlevel);

        model = ViewModelProviders.of(this).get(EditUserViewModel.class);

        pl = findViewById(R.id.pl);
        pl.setText(String.valueOf(model.getPowerLevel()));
    }
}
