package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.scouter.R;
import com.example.scouter.viewmodels.EditUserViewModel;

public class ScouterDisplay extends AppCompatActivity {
    private EditUserViewModel userViewModel;

    private TextView userName;
    private TextView powerLevel;
    private TextView squatMax;
    private TextView benchMax;
    private TextView deadliftMax;
    private Button weakerCharacterButton;
    private Button strongerCharacterButton;
    private Button homeButton;

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

        powerLevel.setAutoSizeTextTypeUniformWithConfiguration(
                1, 17, 1,
                TypedValue.COMPLEX_UNIT_DIP);
        squatMax.setAutoSizeTextTypeUniformWithConfiguration(
                1, 17, 1,
                    TypedValue.COMPLEX_UNIT_DIP);
        benchMax.setAutoSizeTextTypeUniformWithConfiguration(
                1, 17, 1,
                TypedValue.COMPLEX_UNIT_DIP);
        deadliftMax.setAutoSizeTextTypeUniformWithConfiguration(
                1, 17, 1,
                TypedValue.COMPLEX_UNIT_DIP);


        weakerCharacterButton = findViewById(R.id.weaker_character_button);
        strongerCharacterButton = findViewById(R.id.stronger_character_button);
        homeButton = findViewById(R.id.home);

        DecimalFormat formatter = new DecimalFormat("#,###,###");

        String squatDisplay = "Squat: " + formatter.format(
                userViewModel.getUser().getSquat());
        String benchDisplay = "Bench: " + formatter.format(
                userViewModel.getUser().getBench());
        String deadliftDisplay = "Deadlift: " + formatter.format(
                userViewModel.getUser().getDeadlift());

        userName.setText(userViewModel.getName());

        System.out.println(userViewModel.getPowerLevel());

        if (userViewModel.getPowerLevel() < 1000000) {
            powerLevel.setText(formatter.format(userViewModel.getPowerLevel()));
        } else {
            formatter = new DecimalFormat("0.###E0");
            powerLevel.setText(String.valueOf(formatter.format(userViewModel.getPowerLevel())));
        }
        squatMax.setText(squatDisplay);
        benchMax.setText(benchDisplay);
        deadliftMax.setText(deadliftDisplay);

        userViewModel.weakStrong();

        weakerCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ScouterDisplay.this,
                        WeakerOrStrongerCharacter.class);
                myIntent.putExtra("indexOfLifeForm", 0);
                ScouterDisplay.this.startActivity(myIntent);
            }
        });
        strongerCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ScouterDisplay.this,
                        WeakerOrStrongerCharacter.class);
                myIntent.putExtra("indexOfLifeForm", 1);
                ScouterDisplay.this.startActivity(myIntent);
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScouterDisplay.this.startActivity(new Intent(ScouterDisplay.this,
                        UserCreation.class));
            }
        });
    }
}
