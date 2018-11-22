package com.msksoft.tabs;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;

public class DetayActivity extends AppCompatActivity {


    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);
        ImageView masalDetayResim = findViewById(R.id.MasalDetayResim);
        TextView masalDetayAdi = findViewById(R.id.MasalDetayAdi);
        TextView masalDetayAciklamasi = findViewById(R.id.masalDetayAciklamasi);



        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);
        masalDetayAdi.setText(intent.getStringExtra("masal"));
        masalDetayAciklamasi.setText(intent.getStringExtra("masalAciklama"));
        Picasso.get().load(MainActivity.masalResimFromFb.get(position)).into(masalDetayResim);

        //masalDinle = MediaPlayer.create(getApplicationContext(),MainActivity.masalDinleFromFb.get(position));


    }



}
