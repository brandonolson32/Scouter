package com.example.scouter.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
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
    private Button otherCharacterButton;
    private Button scouterDisplayButton;

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
        characterInfoTextView = findViewById(R.id.character_info);
        scouterDisplayButton = findViewById(R.id.scouter_display_button_1);
        otherCharacterButton = findViewById(R.id.weaker_or_stronger_challenger_button);


        Button otherCharacterButton = findViewById(
                R.id.weaker_or_stronger_challenger_button);
        if (indexOfLifeForm == 0) {
            otherCharacterButton.setText("Stronger Challenger");
        } else if (indexOfLifeForm == 1) {
            otherCharacterButton.setText("Weaker Challenger");
        }

        userInfoTextView.setText(userViewModel.getUser().toString());
        characterInfoTextView.setText(displayedCharacter.toString());

        ImageView dbzImage = findViewById(R.id.dbz_image);
        Resources resources = this.getResources();
        final int imageID = resources.getIdentifier(displayedCharacter.imageName(), "drawable",
                this.getPackageName());

//        Glide.with(context).load(imageID).into(dbzImage);

        Glide.with(this).load(imageID).apply(new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)).into(dbzImage);



        if (displayedCharacter.getName().equals("Dr. Kochin")
                && displayedCharacter.getSaga().equals("The World's Strongest")) {


        } else if (displayedCharacter.getName().equals("Goku")
                && displayedCharacter.getSaga().equals("Emperor Pilaf Saga")) {


        } else if (displayedCharacter.getName().equals("Oolong")
                && displayedCharacter.getSaga().equals("Emperor Pilaf Saga")) {


        } else if (displayedCharacter.getName().equals("Puar")
                && displayedCharacter.getSaga().equals("Emperor Pilaf Saga")) {


        } else if (displayedCharacter.getName().equals("Bulma")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Training)")) {


        } else if (displayedCharacter.getName().equals("Shu")
                && displayedCharacter.getSaga().equals("Emperor Pilaf Saga")) {


        } else if (displayedCharacter.getName().equals("Mai")
                && displayedCharacter.getSaga().equals("Emperor Pilaf Saga")) {


        } else if (displayedCharacter.getName().equals("Emperor Pilaf")
                && displayedCharacter.getSaga().equals("Emperor Pilaf Saga")) {


        } else if (displayedCharacter.getName().equals("Ranfan")
                && displayedCharacter.getSaga().equals("Tournament Saga")) {


        } else if (displayedCharacter.getName().equals("Commander Red")
                && displayedCharacter.getSaga().equals("Red Ribbon Army Saga")) {


        } else if (displayedCharacter.getName().equals("Nam")
                && displayedCharacter.getSaga().equals("Tournament Saga")) {


        } else if (displayedCharacter.getName().equals("Giran")
                && displayedCharacter.getSaga().equals("Tournament Saga")) {


        } else if (displayedCharacter.getName().equals("Goku (Great Ape)")
                && displayedCharacter.getSaga().equals("Emperor Pilaf Saga")) {


        } else if (displayedCharacter.getName().equals("Bacterian")
                && displayedCharacter.getSaga().equals("Tournament Saga")) {


        } else if (displayedCharacter.getName().equals("Master Shen")
                && displayedCharacter.getSaga().equals("Tien Shinhan Saga")) {


        } else if (displayedCharacter.getName().equals("Chi-Chi")
                && displayedCharacter.getSaga().equals("Piccolo Jr. Saga")) {


        } else if (displayedCharacter.getName().equals("Master Roshi")
                && displayedCharacter.getSaga().equals("Tien Shinhan Saga")) {
            Glide.with(this).load(R.drawable.masterroshi_tienshinhansaga)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(dbzImage);
        } else if (displayedCharacter.getName().equals("Master Roshi")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Training)")) {


        } else if (displayedCharacter.getName().equals("Major Metallitron")
                && displayedCharacter.getSaga().equals("Red Ribbon Army Saga")) {


        } else if (displayedCharacter.getName().equals("Colonel Violet")
                && displayedCharacter.getSaga().equals("Red Ribbon Army Saga")) {


        } else if (displayedCharacter.getName().equals("General White")
                && displayedCharacter.getSaga().equals("Red Ribbon Army Saga")) {


        } else if (displayedCharacter.getName().equals("Assistant Black")
                && displayedCharacter.getSaga().equals("Red Ribbon Army Saga")) {


        } else if (displayedCharacter.getName().equals("Yamcha")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Training)")) {


        } else if (displayedCharacter.getName().equals("Goku")
                && displayedCharacter.getSaga().equals("Tien Shinhan Saga")) {


        } else if (displayedCharacter.getName().equals("Tien Shinhan")
                && displayedCharacter.getSaga().equals("Tien Shinhan Saga")) {


        } else if (displayedCharacter.getName().equals("General Blue")
                && displayedCharacter.getSaga().equals("Red Ribbon Army Saga")) {


        } else if (displayedCharacter.getName().equals("Master Roshi")
                && displayedCharacter.getSaga().equals("King Piccolo Saga")) {


        } else if (displayedCharacter.getName().equals("Piano")
                && displayedCharacter.getSaga().equals("King Piccolo Saga")) {


        } else if (displayedCharacter.getName().equals("Korin")
                && displayedCharacter.getSaga().equals("Red Ribbon Army Saga")) {


        } else if (displayedCharacter.getName().equals("Colonel Silver")
                && displayedCharacter.getSaga().equals("Red Ribbon Army Saga")) {


        } else if (displayedCharacter.getName().equals("Krillin")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Training)")) {


        } else if (displayedCharacter.getName().equals("Cyborg Tao")
                && displayedCharacter.getSaga().equals("Piccolo Jr. Saga")) {


        } else if (displayedCharacter.getName().equals("Kami")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Training)")) {


        } else if (displayedCharacter.getName().equals("Tien Shinhan")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Training)")) {


        } else if (displayedCharacter.getName().equals("Goku")
                && displayedCharacter.getSaga().equals("King Piccolo Saga")) {


        } else if (displayedCharacter.getName().equals("King Piccolo")
                && displayedCharacter.getSaga().equals("King Piccolo Saga")) {


        } else if (displayedCharacter.getName().equals("Drum")
                && displayedCharacter.getSaga().equals("King Piccolo Saga")) {


        } else if (displayedCharacter.getName().equals("Piccolo (w/weights)")
                && displayedCharacter.getSaga().equals("Raditz Saga")) {


        } else if (displayedCharacter.getName().equals("Piccolo")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Training)")) {


        } else if (displayedCharacter.getName().equals("Goku (w/weights)")
                && displayedCharacter.getSaga().equals("Raditz Saga")) {


        } else if (displayedCharacter.getName().equals("Tambourine")
                && displayedCharacter.getSaga().equals("King Piccolo Saga")) {


        } else if (displayedCharacter.getName().equals("Ginger")
                && displayedCharacter.getSaga().equals("Dead Zone")) {


        } else if (displayedCharacter.getName().equals("Sansho")
                && displayedCharacter.getSaga().equals("Dead Zone")) {


        } else if (displayedCharacter.getName().equals("Nicky")
                && displayedCharacter.getSaga().equals("Dead Zone")) {


        } else if (displayedCharacter.getName().equals("Piccolo")
                && displayedCharacter.getSaga().equals("Raditz Saga")) {


        } else if (displayedCharacter.getName().equals("Goku")
                && displayedCharacter.getSaga().equals("Raditz Saga")) {
            Glide.with(this).load(R.drawable.goku_raditzsaga)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(dbzImage);
        } else if (displayedCharacter.getName().equals("Chiaotzu")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {
            Glide.with(this).load(R.drawable.chiaotzu_vegetasagasaiyaninvasion)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(dbzImage);
        } else if (displayedCharacter.getName().equals("Gohan")
                && displayedCharacter.getSaga().equals("Raditz Saga")) {


        } else if (displayedCharacter.getName().equals("Yakon")
                && displayedCharacter.getSaga().equals("Babidi Saga")) {


        } else if (displayedCharacter.getName().equals("Princess Snake")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Training)")) {


        } else if (displayedCharacter.getName().equals("Ox-King")
                && displayedCharacter.getSaga().equals("Emperor Pilaf Saga")) {


        } else if (displayedCharacter.getName().equals("Goku (Super Kamehameha)")
                && displayedCharacter.getSaga().equals("Piccolo Jr. Saga")) {


        } else if (displayedCharacter.getName().equals("Goku (Kamehameha)")
                && displayedCharacter.getSaga().equals("Raditz Saga")) {


        } else if (displayedCharacter.getName().equals("Yajirobe")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {


        } else if (displayedCharacter.getName().equals("Gohan")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {


        } else if (displayedCharacter.getName().equals("Namekian Warriors (suppressed)")
                && displayedCharacter.getSaga().equals("Namek Saga")) {


        } else if (displayedCharacter.getName().equals("Bio-Men")
                && displayedCharacter.getSaga().equals("The World's Strongest")) {


        } else if (displayedCharacter.getName().equals("Bubbles")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Training)")) {


        } else if (displayedCharacter.getName().equals("Mr Popo")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Training)")) {


        } else if (displayedCharacter.getName().equals("Gregory")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Training)")) {


        } else if (displayedCharacter.getName().equals("Saibamen")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {


        } else if (displayedCharacter.getName().equals("King Yemma")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Training)")) {


        } else if (displayedCharacter.getName().equals("Gohan (Enraged)")
                && displayedCharacter.getSaga().equals("Raditz Saga")) {


        } else if (displayedCharacter.getName().equals("Piccolo (Special Beam Cannon #1)")
                && displayedCharacter.getSaga().equals("Raditz Saga")) {
            Glide.with(this).load(R.drawable.special_beam_cannon_1)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(dbzImage);
        } else if (displayedCharacter.getName().equals("Garlic Jr.")
                && displayedCharacter.getSaga().equals("Dead Zone")) {


        } else if (displayedCharacter.getName().equals("Yamcha")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {


        } else if (displayedCharacter.getName().equals("Piccolo (Special Beam Cannon #2)")
                && displayedCharacter.getSaga().equals("Raditz Saga")) {
            Glide.with(this).load(R.drawable.special_bean_cannon_2)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(dbzImage);
        } else if (displayedCharacter.getName().equals("Krillin (vs. Frieza's soldiers)")
                && displayedCharacter.getSaga().equals("Namek Saga")) {


        } else if (displayedCharacter.getName().equals("Gohan (vs. Frieza's soldiers)")
                && displayedCharacter.getSaga().equals("Namek Saga")) {


        } else if (displayedCharacter.getName().equals("Banan and Sui")
                && displayedCharacter.getSaga().equals("Namek Saga")) {


        } else if (displayedCharacter.getName().equals("Raditz")
                && displayedCharacter.getSaga().equals("Raditz Saga")) {


        } else if (displayedCharacter.getName().equals("Krillin")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {


        } else if (displayedCharacter.getName().equals("Gohan (Masenko)")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {
            int id = R.drawable.gohanmasenko_vegetasagasaiyaninvasion;
            dbzImage.setImageResource(id);
        } else if (displayedCharacter.getName().equals("Goku (Super Saiyan)")
                && displayedCharacter.getSaga().equals("Babidi Saga")) {


        } else if (displayedCharacter.getName().equals("Namekian Warriors")
                && displayedCharacter.getSaga().equals("Namek Saga")) {


        } else if (displayedCharacter.getName().equals("Scarface")
                && displayedCharacter.getSaga().equals("Raditz Saga")) {


        } else if (displayedCharacter.getName().equals("Shorty")
                && displayedCharacter.getSaga().equals("Raditz Saga")) {


        } else if (displayedCharacter.getName().equals("Piccolo")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {


        } else if (displayedCharacter.getName().equals("King Kai")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Training)")) {


        } else if (displayedCharacter.getName().equals("Dabura")
                && displayedCharacter.getSaga().equals("Babidi Saga")) {


        } else if (displayedCharacter.getName().equals("Nappa")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {


        } else if (displayedCharacter.getName().equals("Paragus")
                && displayedCharacter.getSaga().equals("Broly")) {


        } else if (displayedCharacter.getName().equals("Misokatsun")
                && displayedCharacter.getSaga().equals("The World's Strongest")) {


        } else if (displayedCharacter.getName().equals("Goku (suppressed)")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {


        } else if (displayedCharacter.getName().equals("Moori")
                && displayedCharacter.getSaga().equals("Namek Saga")) {


        } else if (displayedCharacter.getName().equals("Krillin")
                && displayedCharacter.getSaga().equals("The World's Strongest")) {


        } else if (displayedCharacter.getName().equals("Goku (suppressed)")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Gohan")
                && displayedCharacter.getSaga().equals("The World's Strongest")) {


        } else if (displayedCharacter.getName().equals("Kishime")
                && displayedCharacter.getSaga().equals("The World's Strongest")) {


        } else if (displayedCharacter.getName().equals("Ebifurya")
                && displayedCharacter.getSaga().equals("The World's Strongest")) {


        } else if (displayedCharacter.getName().equals("Lakasei")
                && displayedCharacter.getSaga().equals("The Tree of Might")) {


        } else if (displayedCharacter.getName().equals("Goku")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {


        } else if (displayedCharacter.getName().equals("Rasin")
                && displayedCharacter.getSaga().equals("The Tree of Might")) {


        } else if (displayedCharacter.getName().equals("Piccolo")
                && displayedCharacter.getSaga().equals("The World's Strongest")) {


        } else if (displayedCharacter.getName().equals("Daiz")
                && displayedCharacter.getSaga().equals("The Tree of Might")) {


        } else if (displayedCharacter.getName().equals("Amond")
                && displayedCharacter.getSaga().equals("The Tree of Might")) {


        } else if (displayedCharacter.getName().equals("Gohan")
                && displayedCharacter.getSaga().equals("The Tree of Might")) {


        } else if (displayedCharacter.getName().equals("Yamcha (Reincarnated)")
                && displayedCharacter.getSaga().equals("That Time I Got Reincarnated as Yamcha")) {


        } else if (displayedCharacter.getName().equals("Goku")
                && displayedCharacter.getSaga().equals("The World's Strongest")) {


        } else if (displayedCharacter.getName().equals("Gohan (vs. Guldo)")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Krillin (vs. Guldo)")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Namekian Warriors")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Bardock")
                && displayedCharacter.getSaga().equals("Bardock - The Father of Goku")) {
            Glide.with(this).load(R.drawable.bardock_bardockthefatherofgoku)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(dbzImage);
        } else if (displayedCharacter.getName().equals("Cacao")
                && displayedCharacter.getSaga().equals("The Tree of Might")) {


        } else if (displayedCharacter.getName().equals("Krillin (vs. Recoome)")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Gohan (vs. Recoome)")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Goku (x2 Kaioken)")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {
            Glide.with(this).load(R.drawable.gokux2kaioken_vegetasaga)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(dbzImage);
        } else if (displayedCharacter.getName().equals("Vegeta")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {


        } else if (displayedCharacter.getName().equals("Cui")
                && displayedCharacter.getSaga().equals("Namek Saga")) {


        } else if (displayedCharacter.getName().equals("Piccolo")
                && displayedCharacter.getSaga().equals("The Tree of Might")) {


        } else if (displayedCharacter.getName().equals("Turles")
                && displayedCharacter.getSaga().equals("The Tree of Might")) {


        } else if (displayedCharacter.getName().equals("Goku (x2 Kaioken)")
                && displayedCharacter.getSaga().equals("The World's Strongest")) {


        } else if (displayedCharacter.getName().equals("Gohan (Masenko)")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Dodoria")
                && displayedCharacter.getSaga().equals("Namek Saga")) {


        } else if (displayedCharacter.getName().equals("Zarbon")
                && displayedCharacter.getSaga().equals("Namek Saga")) {


        } else if (displayedCharacter.getName().equals("Captain Ginyu (in Goku's body)")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Vegeta")
                && displayedCharacter.getSaga().equals("Namek Saga")) {


        } else if (displayedCharacter.getName().equals("Goku (x3 Kaioken)")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {
            Glide.with(this).load(R.drawable.gokux3kaioken_vegetasaga)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(dbzImage);
        } else if (displayedCharacter.getName().equals("Goku")
                && displayedCharacter.getSaga().equals("The Tree of Might")) {


        } else if (displayedCharacter.getName().equals("Goku (x3 Kaioken)")
                && displayedCharacter.getSaga().equals("The World's Strongest")) {


        } else if (displayedCharacter.getName().equals("Vegeta")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Goku (x4 Kaioken)")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {
            Glide.with(this).load(R.drawable.goku_x4_kaioken_vegeta_saga)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(dbzImage);
        } else if (displayedCharacter.getName().equals("Dr. Wheelo")
                && displayedCharacter.getSaga().equals("The World's Strongest")) {


        } else if (displayedCharacter.getName().equals("Goku (x4 Kaioken)")
                && displayedCharacter.getSaga().equals("The World's Strongest")) {


        } else if (displayedCharacter.getName().equals("Nail")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Goku (x2 Kaioken)")
                && displayedCharacter.getSaga().equals("The Tree of Might")) {


        } else if (displayedCharacter.getName().equals("Krillin")
                && displayedCharacter.getSaga().equals("Frieza Saga")) {


        } else if (displayedCharacter.getName().equals("Goku")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Gohan (Great Ape)")
                && displayedCharacter.getSaga().equals("The Tree of Might")) {


        } else if (displayedCharacter.getName().equals("Captain Ginyu")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Neiz")
                && displayedCharacter.getSaga().equals("Cooler's Revenge")) {


        } else if (displayedCharacter.getName().equals("Salza")
                && displayedCharacter.getSaga().equals("Cooler's Revenge")) {


        } else if (displayedCharacter.getName().equals("Vegeta (Great Ape)")
                && displayedCharacter.getSaga().equals("Vegeta Saga (Saiyan Invasion)")) {


        } else if (displayedCharacter.getName().equals("Goku (x2 Kaioken)")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Dore")
                && displayedCharacter.getSaga().equals("Cooler's Revenge")) {


        } else if (displayedCharacter.getName().equals("Gohan")
                && displayedCharacter.getSaga().equals("Frieza Saga")) {


        } else if (displayedCharacter.getName().equals("Vegeta (Post Recoome)")
                && displayedCharacter.getSaga().equals("Captain Ginyu Saga")) {


        } else if (displayedCharacter.getName().equals("Goku (x10 Kaioken)")
                && displayedCharacter.getSaga().equals("The Tree of Might")) {
            Glide.with(this).load(R.drawable.gokux10kaioken_thetreeofmight)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(dbzImage);
        } else if (displayedCharacter.getName().equals("Turles")
                && displayedCharacter.getSaga().equals("The Tree of Might")) {


        } else if (displayedCharacter.getName().equals("Frieza (first form)")
                && displayedCharacter.getSaga().equals("Frieza Saga")) {


        } else if (displayedCharacter.getName().equals("Frieza (second form)")
                && displayedCharacter.getSaga().equals("Frieza Saga")) {


        } else if (displayedCharacter.getName().equals("Frieza")
                && displayedCharacter.getSaga().equals("Resurrection ‘F’")) {


        } else if (displayedCharacter.getName().equals("Goku")
                && displayedCharacter.getSaga().equals("Frieza Saga")) {


        } else if (displayedCharacter.getName().equals("Goku (x10 Kaioken)")
                && displayedCharacter.getSaga().equals("Frieza Saga")) {


        } else if (displayedCharacter.getName().equals("Goku (x20 Kaioken)")
                && displayedCharacter.getSaga().equals("Frieza Saga")) {


        } else if (displayedCharacter.getName().equals("Frieza (final form; 50%)")
                && displayedCharacter.getSaga().equals("Frieza Saga")) {


        } else if (displayedCharacter.getName().equals("Frieza (final form; 100%)")
                && displayedCharacter.getSaga().equals("Frieza Saga")) {


        } else if (displayedCharacter.getName().equals("Goku (Super Saiyan)")
                && displayedCharacter.getSaga().equals("Frieza Saga")) {
//            Glide.with(this).load("https://66.media.tumblr.com/4e327afb4ba85a5c4fb496da7d8cbc5e/tumblr_mlaywvLCiZ1qlkrb8o1_500.gif").asGif().into(dbzImage);

            Glide.with(this).load(R.drawable.gokusupersaiyan_friezasaga)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(dbzImage);

        } else if (displayedCharacter.getName().equals("Cooler (fifth form)")
                && displayedCharacter.getSaga().equals("Cooler's Revenge")) {
            Glide.with(this).load(R.drawable.coolerfifthform_coolersrevenge)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(dbzImage);
        } else if (displayedCharacter.getName().equals("Broly (Legendary Super Saiyan)")
                && displayedCharacter.getSaga().equals("Broly - The Legendary Super Saiyan")) {
            Glide.with(this).load(R.drawable.brolydbs_broly)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(dbzImage);
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


