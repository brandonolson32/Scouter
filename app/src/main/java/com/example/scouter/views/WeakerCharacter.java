package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.scouter.R;
import com.example.scouter.entity.Character.LifeForm;
import com.example.scouter.viewmodels.EditUserViewModel;

public class WeakerCharacter extends AppCompatActivity {
    private EditUserViewModel model;

    private LifeForm weaker;
    private TextView textView;
    private Button stronger;
    private Button restart;

    @Override
    /*
      This function makes everything upon pressing the create button
      @param savedInstanceState The state of the saved game
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weaker_char);

        model = ViewModelProviders.of(this).get(EditUserViewModel.class);
        weaker = model.getWeaker();

        textView = findViewById(R.id.characterDesc);
        stronger = findViewById(R.id.stronger);
        restart = findViewById(R.id.restart);

        textView.setText(weaker.toString());
        stronger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeakerCharacter.this.startActivity(new Intent(WeakerCharacter.this, StrongerCharacter.class));
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeakerCharacter.this.startActivity(new Intent(WeakerCharacter.this, UserCreation.class));
            }
        });
    }
}
