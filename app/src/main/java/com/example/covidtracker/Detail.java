package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Detail extends AppCompatActivity {
public int position;
public TextView country,cases,critical,recovered,death,todaydeath,todaycases,todayrecovered,active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        country=findViewById(R.id.country);
        cases=findViewById(R.id.cases);
        recovered=findViewById(R.id.rvd);
        active=findViewById(R.id.atv);
        critical=findViewById(R.id.ctcl);
        death=findViewById(R.id.ttdeath);
        todayrecovered=findViewById(R.id.tdrvd);
        todaydeath=findViewById(R.id.tdeath);
        todaycases=findViewById(R.id.ttlcase);
        Intent intent=getIntent();
        position=intent.getIntExtra("position", 0);

        country.setText(Affected_Counteries.list.get(position).getCountry());
        cases.setText(Affected_Counteries.list.get(position).getCases());
        recovered.setText(Affected_Counteries.list.get(position).getRecovered());
        active.setText(Affected_Counteries.list.get(position).getActive());
        critical.setText(Affected_Counteries.list.get(position).getCritical());
        death.setText(Affected_Counteries.list.get(position).getDeath());
        todaydeath.setText(Affected_Counteries.list.get(position).getToday_death());
        todayrecovered.setText(Affected_Counteries.list.get(position).getToday_recoverd());
        todaycases.setText(Affected_Counteries.list.get(position).getToday_case());



        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}