package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.scouter.R;
import com.example.scouter.viewmodels.EditUserViewModel;

public class ScouterDisplay extends AppCompatActivity {
    private EditUserViewModel model;

    private TextView userName;
    private TextView powerLevel;
    private TextView squatMax;
    private TextView benchMax;
    private TextView deadliftMax;

    @Override
    /*
      This function makes everything upon pressing the create button
      @param savedInstanceState The state of the saved game
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powerlevel);

        model = ViewModelProviders.of(this).get(EditUserViewModel.class);

        userName = findViewById(R.id.name_field);
        powerLevel = findViewById(R.id.powerlevel);
        squatMax = findViewById(R.id.squat_max_field);
        benchMax = findViewById(R.id.bench_max_field);
        deadliftMax = findViewById(R.id.deadlift_max_field);

        userName.setText(model.getName());
        powerLevel.setText(String.valueOf(model.getPowerLevel()));
        squatMax.setText(String.valueOf(model.getUser().getSquat()));
        benchMax.setText(String.valueOf(model.getUser().getBench()));
        deadliftMax.setText(String.valueOf(model.getUser().getDeadlift()));
    }
}
