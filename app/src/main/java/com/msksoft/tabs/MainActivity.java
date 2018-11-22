package com.msksoft.tabs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    PostClass adapter1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    ArrayList<String> masalAdiFromFB;
    static ArrayList<String> masalResimFromFb;
    ArrayList<String> masalAciklamaFromFb;
    static ArrayList<String> masalDinleFromFb;

    SectionsPageAdapter sectionsPageAdapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        masalAdiFromFB = new ArrayList<String>();
        masalResimFromFb = new ArrayList<String>();
        masalAciklamaFromFb = new ArrayList<String>();
        masalDinleFromFb = new ArrayList<String>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        adapter1 = new PostClass(masalAdiFromFB,masalResimFromFb,FragmentHot.);

        listView.setAdapter(adapter1);
        getDataFromFirebase();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),DetayActivity.class);
                intent.putExtra("position",i);
                String masalAdi =masalAdiFromFB.get(i).toString();
                String masalAciklama = masalAciklamaFromFb.get(i).toString();

                intent.putExtra("masalAciklama",masalAciklama);
                intent.putExtra("masal",masalAdi);

                startActivity(intent);
            }
        });



        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        setupPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);



    }

    public void getDataFromFirebase(){
        DatabaseReference newRef = firebaseDatabase.getReference("Masallar");
        newRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //System.out.println("FBV children " + dataSnapshot.getChildren());
                //System.out.println("FBV key " + dataSnapshot.getKey());
                //System.out.println("FBV value " + dataSnapshot.getValue());
                //System.out.println("FBV priority " +dataSnapshot.getPriority());

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    //System.out.println("FBV children " +ds.getValue());

                    HashMap<String,String> hashMap =(HashMap<String, String>) ds.getValue();
                    //System.out.println("FBV : " +hashMap.get("masalMetni") );
                    masalAdiFromFB.add(hashMap.get("masalAdi"));
                    masalResimFromFb.add(hashMap.get("masalResmi"));
                    masalAciklamaFromFb.add(hashMap.get("masalMetni"));
                    adapter1.notifyDataSetChanged();
                    //masalDinleFromFb.add(hashMap.get("masalDinle"));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void setupPager(ViewPager viewPager) {

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentHot(),"Hot");
        adapter.addFragment(new FragmentFresh(), "Fresh");
        adapter.addFragment(new FragmentTrending(),"Trending");
        viewPager.setAdapter(adapter);
    }


}





