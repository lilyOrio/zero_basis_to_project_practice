package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.adapter.PlanetListAdapter;
import com.example.myapplication.bean.Planet;

public class NoscrollListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noscroll_list);
        PlanetListAdapter adapter1 = new PlanetListAdapter(this, Planet.getDefaultList());
        ListView lv_plant = findViewById(R.id.lv_plant);
        lv_plant.setAdapter(adapter1);
        lv_plant.setOnItemClickListener(adapter1);
        lv_plant.setOnItemLongClickListener(adapter1);
        PlanetListAdapter adapter2 = new PlanetListAdapter(this, Planet.getDefaultList());
        ListView nslv_plant = findViewById(R.id.nslv_plant);
        nslv_plant.setAdapter(adapter2);
        nslv_plant.setOnItemClickListener(adapter2);
        nslv_plant.setOnItemLongClickListener(adapter2);
    }
}