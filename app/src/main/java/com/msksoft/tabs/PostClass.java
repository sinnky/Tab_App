package com.msksoft.tabs;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostClass extends ArrayAdapter<String> {

    private final ArrayList<String> masalAdiList;
    private final ArrayList<String> masalResmiList;
    public final Activity context;

    public PostClass(ArrayList<String> masalAdi, ArrayList<String> masalResmi, Activity context) {
        super(context,R.layout.custom_view,masalAdi);
        this.masalAdiList = masalAdi;
        this.masalResmiList = masalResmi;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();

        View customView = layoutInflater.inflate(R.layout.custom_view,null,true);

        TextView masalAdi = customView.findViewById(R.id.masalAdi);
        ImageView masalResmi = customView.findViewById(R.id.masalResmi);

        masalAdi.setText(masalAdiList.get(position));
        Picasso.get().load(masalResmiList.get(position)).into(masalResmi);


        return customView;
    }
}