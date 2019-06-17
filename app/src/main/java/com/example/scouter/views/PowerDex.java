package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class PowerDex extends AppCompatActivity {
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
        setContentView(R.layout.activity_power_dex);
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
                R.layout.listview_text, encounteredLifeForms);
        powerDex.setAdapter(arrayToListView);

        // when they click on an item, it sends them to the single character screen
        powerDex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = new Intent(PowerDex.this, SingleCharacter.class);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
        scouterDisplay = findViewById(R.id.scouter_display_portal);
        home = findViewById(R.id.new_reading);
        resetDex = findViewById(R.id.reset_dex);
        scouterDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PowerDex.this.startActivity(new Intent(PowerDex.this, ScouterDisplay.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PowerDex.this.startActivity(new Intent(PowerDex.this,
                        UserCreation.class));
            }
        });
        final SharedPreferences pref = this.getSharedPreferences("com.example.scouter",
                Context.MODE_PRIVATE);
        resetDex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref.edit().clear().apply();
                PowerDex.this.startActivity(new Intent(PowerDex.this,
                        PowerDex.class));
            }
        });
    }
}
