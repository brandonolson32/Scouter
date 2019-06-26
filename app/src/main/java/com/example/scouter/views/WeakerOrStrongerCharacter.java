package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.scouter.R;
import com.example.scouter.entity.LifeForm;
import com.example.scouter.viewmodels.EditUserViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeakerOrStrongerCharacter extends AppCompatActivity {
    private EditUserViewModel userViewModel;

    private Context context = WeakerOrStrongerCharacter.this;

    private LifeForm user;
    private LifeForm displayedCharacter;

    private List<LifeForm> weakerAndStrongerLifeForms;
    private int indexOfLifeForm;

    private TextView characterInfoTextView;
    private TextView userInfoTextView;
    private TextView congratsTextView;
    private Button otherCharacterButton;
    private Button scouterDisplayButton;
    private Button thorTriggeredButton;
    private SharedPreferences powerDexData;

    private static List<WeakerOrStrongerCharacter.IMemoryInfo> memInfoList =
            new ArrayList<WeakerOrStrongerCharacter.IMemoryInfo>();

    public static abstract interface IMemoryInfo {
        public void goodTimeToReleaseMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        //don't compare with == as intermediate stages also can be reported,
        // always better to check >= or <=
        if (level >= ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW) {
            try {
                // Activity at the front will get earliest than activity at the
                // back
                for (int i = memInfoList.size() - 1; i >= 0; i--) {
                    try {
                        memInfoList.get(i).goodTimeToReleaseMemory();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    /*
      This function makes everything upon pressing the create button
      @param savedInstanceState The state of the saved game
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weaker_or_stronger_char);

        Intent myIntent = getIntent();
        indexOfLifeForm = myIntent.getIntExtra("indexOfLifeForm", 0);
        System.out.println("index:" + indexOfLifeForm);

        userViewModel = ViewModelProviders.of(this).get(EditUserViewModel.class);
        weakerAndStrongerLifeForms = userViewModel.getWeakerAndStronger();

        Collections.sort(weakerAndStrongerLifeForms);
        displayedCharacter = weakerAndStrongerLifeForms.get(indexOfLifeForm);

        userInfoTextView = findViewById(R.id.user_info);
        congratsTextView = findViewById(R.id.congrats_text);
        characterInfoTextView = findViewById(R.id.character_info);
        scouterDisplayButton = findViewById(R.id.scouter_display_button_1);
        otherCharacterButton = findViewById(R.id.weaker_or_stronger_challenger_button);
        thorTriggeredButton = findViewById(R.id.encountered_lifeforms_button);

        Button otherCharacterButton = findViewById(
                R.id.weaker_or_stronger_challenger_button);
        if (indexOfLifeForm == 0) {
            otherCharacterButton.setText(getResources().getString(R.string.otherCharText0));
        } else if (indexOfLifeForm == 1) {
            otherCharacterButton.setText(getResources().getString(R.string.otherCharText1));
        }

        userInfoTextView.setText(userViewModel.getUser().toString());
        characterInfoTextView.setText(displayedCharacter.toString());

        ImageView dbzImage = findViewById(R.id.dbz_image);
        Resources resources = this.getResources();
        final int imageID = resources.getIdentifier(displayedCharacter.imageName(),
                "drawable", this.getPackageName());

        // sets the image to the comparable character
        Glide.with(this).load(imageID).apply(new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)).into(dbzImage);

        powerDexData = this.getSharedPreferences("com.example.scouter",
                Context.MODE_PRIVATE);
        if (!powerDexData.contains(displayedCharacter.toString())) {
            powerDexData.edit().putString(displayedCharacter.varHolder(),
                    displayedCharacter.toString()).apply();
        }

        // if user gets Hafthor, then they unlock the list of character
        if (displayedCharacter.getName().equals("The Mountain")) {

            congratsTextView.setText(getResources().getString(R.string.congrats_text));

            // gives the user all the lifeforms
            userViewModel.setEncountered(userViewModel.getLifeformList());
            powerDexData = this.getSharedPreferences("com.example.scouter", Context.MODE_PRIVATE);
            for (LifeForm lf : userViewModel.getLifeformList()) {
                if (!powerDexData.contains(lf.toString())) {
                    powerDexData.edit().putString(lf.varHolder(),
                            lf.toString()).apply();
                }
            }

            thorTriggeredButton.setText(getString(R.string.thorButton));
            thorTriggeredButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WeakerOrStrongerCharacter.this.startActivity(
                            new Intent(WeakerOrStrongerCharacter.this,
                                    PowerDex.class));
                }
            });
        } else {
            thorTriggeredButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WeakerOrStrongerCharacter.this.startActivity(
                            new Intent(WeakerOrStrongerCharacter.this,
                                    PowerDex.class));
                }
            });
        }

        otherCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(WeakerOrStrongerCharacter.this,
                        WeakerOrStrongerCharacter.class);

                if (indexOfLifeForm == 0) {
                    myIntent.putExtra("indexOfLifeForm", 1);
                } else if (indexOfLifeForm == 1) {
                    myIntent.putExtra("indexOfLifeForm", 0);
                }

                WeakerOrStrongerCharacter.this.startActivity(myIntent);
            }
        });
        scouterDisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeakerOrStrongerCharacter.this.startActivity(
                        new Intent(WeakerOrStrongerCharacter.this,
                            ScouterDisplay.class));
            }
        });
    }
}


