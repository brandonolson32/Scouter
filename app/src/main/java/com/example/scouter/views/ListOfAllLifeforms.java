package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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

public class ListOfAllLifeforms extends AppCompatActivity {
    private EditUserViewModel model;

    private ListView listOfLifeforms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lifeforms);

        model = ViewModelProviders.of(this).get(EditUserViewModel.class);

        listOfLifeforms = findViewById(R.id.list);

        ArrayAdapter<LifeForm> arrayToListView = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, model.getLifeformList());

        listOfLifeforms.setAdapter(arrayToListView);
        listOfLifeforms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = new Intent(ListOfAllLifeforms.this, SingleCharacter.class);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LifeForm lf = model.getLifeformList().get(position);
                intent.putExtra("lifeform", lf.getName() + "-" + lf.getPowerLevel()
                        + "-" + lf.getSaga());
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
    }
}
