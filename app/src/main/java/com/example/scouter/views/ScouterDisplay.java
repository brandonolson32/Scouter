package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.scouter.R;
import com.example.scouter.viewmodels.EditUserViewModel;

public class ScouterDisplay extends AppCompatActivity {
    private EditUserViewModel model;

    private TextView sr;
    private TextView pl;
    private TextView characterName;

    @Override
    /*
      This function makes everything upon pressing the create button
      @param savedInstanceState The state of the saved game
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powerlevel);

        model = ViewModelProviders.of(this).get(EditUserViewModel.class);

        pl = findViewById(R.id.powerlevel);
        sr = findViewById(R.id.statreader);
        characterName = findViewById(R.id.characterName);
        characterName.setText(model.comparableLifeForm().getName());
        sr.setText(String.format("With a squat of %d, a bench of %d, and a deadlift of %d, %s has a power level of ...",
                model.getUser().getSquat(), model.getUser().getBench(), model.getUser().getDeadlift(),
                model.getUser().getName(), model.getPowerLevel()));
        pl.setText(String.valueOf(model.getPowerLevel()));
    }
}
