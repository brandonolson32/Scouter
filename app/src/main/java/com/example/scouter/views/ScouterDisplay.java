package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scouter.R;
import com.example.scouter.entity.Character.LifeForm;
import com.example.scouter.entity.User;
import com.example.scouter.viewmodels.EditUserViewModel;

public class ScouterDisplay extends AppCompatActivity {
    private EditUserViewModel userViewModel;

    private TextView userName;
    private TextView powerLevel;
    private TextView squatMax;
    private TextView benchMax;
    private TextView deadliftMax;
    private Button weakerScreen;
    private Button strongerScreen;

    @Override
    /*
      This function makes everything upon pressing the create button
      @param savedInstanceState The state of the saved game
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powerlevel);



        userViewModel = ViewModelProviders.of(this).get(EditUserViewModel.class);

        userName = findViewById(R.id.name_field);
        powerLevel = findViewById(R.id.powerlevel);
        squatMax = findViewById(R.id.squat_max_field);
        benchMax = findViewById(R.id.bench_max_field);
        deadliftMax = findViewById(R.id.deadlift_max_field);
        weakerScreen = findViewById(R.id.weakerButton);
        strongerScreen = findViewById(R.id.strongerButton);

        String squatDisplay = "Squat: " + userViewModel.getUser().getSquat();
        String benchDisplay = "Bench: " + userViewModel.getUser().getBench();
        String deadliftDisplay = "Deadlift: " + userViewModel.getUser().getDeadlift();

        userName.setText(userViewModel.getName());
        powerLevel.setText(String.valueOf(userViewModel.getPowerLevel()));
        squatMax.setText(squatDisplay);
        benchMax.setText(benchDisplay);
        deadliftMax.setText(deadliftDisplay);

        userViewModel.weakStrong();

        weakerScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScouterDisplay.this.startActivity(new Intent(ScouterDisplay.this,
                        WeakerCharacter.class));
            }
        });
        strongerScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScouterDisplay.this.startActivity(new Intent(ScouterDisplay.this,
                        StrongerCharacter.class));
            }
        });
    }
}
