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

public class StrongerCharacter extends AppCompatActivity {
    private EditUserViewModel model;

    private LifeForm stronger;
    private TextView textView;
    private Button weaker;
    private Button restart;

    @Override
    /*
      This function makes everything upon pressing the create button
      @param savedInstanceState The state of the saved game
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stronger_char);

        model = ViewModelProviders.of(this).get(EditUserViewModel.class);
        stronger = model.getStronger();

        textView = findViewById(R.id.characterDesc);
        weaker = findViewById(R.id.weaker);
        restart = findViewById(R.id.restart);

        textView.setText(stronger.toString());

        weaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrongerCharacter.this.startActivity(new Intent(StrongerCharacter.this, WeakerCharacter.class));
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrongerCharacter.this.startActivity(new Intent(StrongerCharacter.this, UserCreation.class));
            }
        });
    }
}
