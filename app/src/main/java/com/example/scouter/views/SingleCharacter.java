package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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

public class SingleCharacter extends AppCompatActivity {
    private EditUserViewModel model;

    private ImageView charImage;
    private TextView charDesc;
    private TextView userInfo;
    private Button next;
    private Button prev;
    private Button back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_character);

        model = ViewModelProviders.of(this).get(EditUserViewModel.class);

        charImage = findViewById(R.id.char_image);
        charDesc = findViewById(R.id.char_desc);
        userInfo = findViewById(R.id.user_text);
        next = findViewById(R.id.next_button);
        prev = findViewById(R.id.prev_button);
        back = findViewById(R.id.back_button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleCharacter.this.startActivity(new Intent(SingleCharacter.this,
                        PowerDex.class));
            }
        });

        userInfo.setText(model.getUser().toString());

        final int pos = getIntent().getExtras().getInt("pos");
        LifeForm lf = model.getEncounteredLifeForms().get(pos);

        charDesc.setText(lf.toString());

        final int imageID = this.getResources().getIdentifier(lf.imageName(), "drawable",
                this.getPackageName());
        Glide.with(this).load(imageID).apply(new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)).into(charImage);

        final int positionOfUser = model.getLifeformList().indexOf(model.getUser());
        if (pos == 0) {
            prev.setVisibility(View.GONE);
            next.setOnClickListener(new View.OnClickListener() {
                Intent intent = new Intent(SingleCharacter.this, SingleCharacter.class);
                @Override
                public void onClick(View v) {
                    if (pos + 1 == positionOfUser) {
                        intent.putExtra("pos", pos + 2);
                    } else {
                        intent.putExtra("pos", pos + 1);
                    }
                    startActivity(intent);
                }
            });
        } else if (pos == model.getLifeformList().size() - 1) {
            next.setVisibility(View.GONE);
            prev.setOnClickListener(new View.OnClickListener() {
                Intent intent = new Intent(SingleCharacter.this, SingleCharacter.class);
                @Override
                public void onClick(View v) {
                    if (pos - 1 == positionOfUser) {
                        intent.putExtra("pos", pos - 2);
                    } else {
                        intent.putExtra("pos", pos - 1);
                    }
                    startActivity(intent);
                }
            });
        } else {
            next.setOnClickListener(new View.OnClickListener() {
                Intent intent = new Intent(SingleCharacter.this, SingleCharacter.class);
                @Override
                public void onClick(View v) {
                    if (pos + 1 == positionOfUser) {
                        intent.putExtra("pos", pos + 2);
                    } else {
                        intent.putExtra("pos", pos + 1);
                    }
                    startActivity(intent);
                }
            });
            prev.setOnClickListener(new View.OnClickListener() {
                Intent intent = new Intent(SingleCharacter.this, SingleCharacter.class);
                @Override
                public void onClick(View v) {
                    if (pos - 1 == positionOfUser) {
                        intent.putExtra("pos", pos - 2);
                    } else {
                        intent.putExtra("pos", pos - 1);
                    }
                    startActivity(intent);
                }
            });
        }
    }
}
