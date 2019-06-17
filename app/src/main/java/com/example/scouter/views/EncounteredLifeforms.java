package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.scouter.R;
import com.example.scouter.entity.LifeForm;
import com.example.scouter.viewmodels.EditUserViewModel;

import java.util.ArrayList;
import java.util.Map;

public class EncounteredLifeforms extends AppCompatActivity {
    private EditUserViewModel model;
    private ListView powerDex;
    private SharedPreferences powerDexData;
    private ArrayList<LifeForm> encounteredLifeForms = new ArrayList<>();
    private Button resetDex;
    private Button home;
    private Button scouterDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encountered_lifeforms);
        model = ViewModelProviders.of(this).get(EditUserViewModel.class);

        powerDexData = this.getSharedPreferences("com.example.scouter", Context.MODE_PRIVATE);
        Map<String, ?> sharedPrefMap = powerDexData.getAll();
        for (String k : sharedPrefMap.keySet()) {
            String[] split = k.split("\\*");
            LifeForm lf = new LifeForm(split[0], Double.parseDouble(split[2]), split[1]);
            encounteredLifeForms.add(lf);
        }
        model.setEncountered(encounteredLifeForms);

        powerDex = findViewById(R.id.list);
        ArrayAdapter<LifeForm> arrayToListView = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, encounteredLifeForms);
        powerDex.setAdapter(arrayToListView);

        // when they click on an item, it sends them to the single character screen
        powerDex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = new Intent(EncounteredLifeforms.this, SingleCharacter.class);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
        scouterDisplay = findViewById(R.id.scouter_display_portal);
        home = findViewById(R.id.home_portal);
        resetDex = findViewById(R.id.reset_dex);
        scouterDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EncounteredLifeforms.this.startActivity(new Intent(EncounteredLifeforms.this, ScouterDisplay.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EncounteredLifeforms.this.startActivity(new Intent(EncounteredLifeforms.this, UserCreation.class));
            }
        });
        final SharedPreferences pref = this.getSharedPreferences("com.example.scouter", Context.MODE_PRIVATE);
        resetDex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref.edit().clear().apply();
                EncounteredLifeforms.this.startActivity(new Intent(EncounteredLifeforms.this, EncounteredLifeforms.class));
            }
        });
    }
}
