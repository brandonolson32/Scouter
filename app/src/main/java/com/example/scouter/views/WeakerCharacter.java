package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scouter.R;
import com.example.scouter.entity.LifeForm;
import com.example.scouter.viewmodels.EditUserViewModel;

public class WeakerCharacter extends AppCompatActivity {
    private EditUserViewModel userViewModel;

    private LifeForm weakerLifeForm;
    private TextView characterInfoTextView;
    private TextView userInfoTextView;
    private Button strongerCharacterButton_2;
    private Button scouterDisplayButton_1;

    @Override
    /*
      This function makes everything upon pressing the create button
      @param savedInstanceState The state of the saved game
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weaker_char);

        userViewModel = ViewModelProviders.of(this).get(EditUserViewModel.class);
        weakerLifeForm = userViewModel.getWeaker();

        userInfoTextView = findViewById(R.id.user_info);
        characterInfoTextView = findViewById(R.id.character_info);
        strongerCharacterButton_2 = findViewById(R.id.stronger_challenger_button_2);
        scouterDisplayButton_1 = findViewById(R.id.scouter_display_button_1);

        userInfoTextView.setText(userViewModel.getUser().toString());
        characterInfoTextView.setText(weakerLifeForm.toString());

        ImageView dbzImage = findViewById(R.id.dbz_image);
        if (weakerLifeForm.getName().equals("Tien Shinhan")
                && weakerLifeForm.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {
            int id = R.drawable.tien_shinhan_vegeta_saga_saiyan_invasion;
            dbzImage.setImageResource(id);
        }

        strongerCharacterButton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeakerCharacter.this.startActivity(new Intent(WeakerCharacter.this,
                        StrongerCharacter.class));
            }
        });
        scouterDisplayButton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeakerCharacter.this.startActivity(new Intent(WeakerCharacter.this,
                        ScouterDisplay.class));
            }
        });
    }
}
