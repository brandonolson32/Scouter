package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProvider;
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

import java.util.List;

public class ListOfAllLifeforms extends AppCompatActivity {
    private EditUserViewModel model;
    private SharedPreferences powerDexData;
    private ListView allLifeForms;
    private Button scouterDisplay;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceFile) {
        super.onCreate(savedInstanceFile);
        setContentView(R.layout.activity_list_of_all_lifeforms);

        model = ViewModelProviders.of(this).get(EditUserViewModel.class);

        // gives the client all the lifeforms
        model.setEncountered(model.getLifeformList());
        powerDexData = this.getSharedPreferences("com.example.scouter", Context.MODE_PRIVATE);
        for (LifeForm lf : model.getLifeformList()) {
            if (!powerDexData.contains(lf.toString())) {
                powerDexData.edit().putString(lf.varHolder(),
                        lf.toString()).apply();
            }
        }

        // sets up the listview
        allLifeForms = findViewById(R.id.all_characters_listview);
        ArrayAdapter<LifeForm> arrayToListView = new ArrayAdapter<>(this,
                R.layout.listview_text, model.getLifeformList());
        allLifeForms.setAdapter(arrayToListView);

        // when they click on an item, it sends them to the single character screen
        allLifeForms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = new Intent(ListOfAllLifeforms.this, SingleCharacter.class);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });

        scouterDisplay = findViewById(R.id.scouter_display_portal);
        home = findViewById(R.id.home_portal);
        scouterDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListOfAllLifeforms.this.startActivity(new Intent(ListOfAllLifeforms.this, ScouterDisplay.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListOfAllLifeforms.this.startActivity(new Intent(ListOfAllLifeforms.this, UserCreation.class));
            }
        });
    }
}
