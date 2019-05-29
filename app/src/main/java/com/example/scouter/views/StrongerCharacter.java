//package com.example.scouter.views;
//
//import android.arch.lifecycle.ViewModelProviders;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.scouter.R;
//import com.example.scouter.entity.LifeForm;
//import com.example.scouter.viewmodels.EditUserViewModel;
//
//public class StrongerCharacter extends AppCompatActivity {
//    private EditUserViewModel model;
//
//    private LifeForm strongerLifeForm;
//    private TextView textView;
//    private Button weaker;
//    private Button scouterDisplay;
//
//    @Override
//    /*
//      This function makes everything upon pressing the create button
//      @param savedInstanceState The state of the saved game
//     */
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_stronger_char);
//
//        model = ViewModelProviders.of(this).get(EditUserViewModel.class);
//        strongerLifeForm = model.getStronger();
//
//        textView = findViewById(R.id.character_info);
//        weaker = findViewById(R.id.weaker);
//        scouterDisplay = findViewById(R.id.scouter_display);
//
//        textView.setText(strongerLifeForm.toString());
//
//        ImageView dbzImage = findViewById(R.id.dbz_image);
//        if (strongerLifeForm.getName().equals("Gohan (Masenko)")
//                && strongerLifeForm.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {
//            int id = R.drawable.gohan_masenko_vegeta_saga_saiyan_invasion;
//            dbzImage.setImageResource(id);
//        }
//
//        weaker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                StrongerCharacter.this.startActivity(
//                        new Intent(StrongerCharacter.this, WeakerCharacter.class));
//            }
//        });
//        scouterDisplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                StrongerCharacter.this.startActivity(
//                        new Intent(StrongerCharacter.this, ScouterDisplay.class));
//            }
//        });
//    }
//}
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

public class StrongerCharacter extends AppCompatActivity {
    private EditUserViewModel userViewModel;

    private LifeForm strongerLifeForm;
    private TextView characterInfoTextView;
    private TextView userInfoTextView;
    private Button weakerCharacterButton_2;
    private Button scouterDisplayButton_2;

    @Override
    /*
      This function makes everything upon pressing the create button
      @param savedInstanceState The state of the saved game
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stronger_char);

        userViewModel = ViewModelProviders.of(this).get(EditUserViewModel.class);
        strongerLifeForm = userViewModel.getStronger();

        userInfoTextView = findViewById(R.id.user_info);
        characterInfoTextView = findViewById(R.id.character_info);
        weakerCharacterButton_2 = findViewById(R.id.weaker_challenger_button_2);
        scouterDisplayButton_2 = findViewById(R.id.scouter_display_button_2);

        userInfoTextView.setText(userViewModel.getUser().toString());
        characterInfoTextView.setText(strongerLifeForm.toString());

        ImageView dbzImage = findViewById(R.id.dbz_image);
        if (strongerLifeForm.getName().equals("Gohan (Masenko)")
                && strongerLifeForm.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {
            int id = R.drawable.gohan_masenko_vegeta_saga_saiyan_invasion;
            dbzImage.setImageResource(id);
        }

        weakerCharacterButton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrongerCharacter.this.startActivity(
                        new Intent(StrongerCharacter.this,
                                WeakerCharacter.class));
            }
        });
        scouterDisplayButton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrongerCharacter.this.startActivity(
                        new Intent(StrongerCharacter.this,
                                ScouterDisplay.class));
            }
        });
    }
}
