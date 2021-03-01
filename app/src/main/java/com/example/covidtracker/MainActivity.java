package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaCodec;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public TextView t1, t2, t3, t4, t5, t6, t7, t8;
    public SimpleArcLoader sal;
    public ScrollView sv;
    public PieChart pieChart;
    public Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.cases);
        t2 = findViewById(R.id.rvd);
        t3 = findViewById(R.id.ctcl);
        t4 = findViewById(R.id.atv);
        t5 = findViewById(R.id.ttlcase);
        t6 = findViewById(R.id.tdeath);
        t7 = findViewById(R.id.ttdeath);
        t8 = findViewById(R.id.tv);
        sal=findViewById(R.id.loader);
        sv=findViewById(R.id.sv);
        pieChart=findViewById(R.id.piechart);
        b1=findViewById(R.id.b1);


        fetchData();


    }
    private void fetchData(){
        String url="https://corona.lmao.ninja/v2/all";
        sal.start();
        StringRequest request=new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.toString());
                            t1.setText(jsonObject.getString("cases"));
                            t2.setText(jsonObject.getString("recovered"));
                            t3.setText(jsonObject.getString("critical"));
                            t4.setText(jsonObject.getString("active"));
                            t5.setText(jsonObject.getString("todayCases"));
                            t6.setText(jsonObject.getString("todayDeaths"));
                            t7.setText(jsonObject.getString("deaths"));
                            t8.setText(jsonObject.getString("affectedCountries"));


                            pieChart.addPieSlice(new PieModel("Recovered_Cases", Integer.parseInt(t2.getText().toString()), Color.parseColor("#4EF430")));
                            pieChart.addPieSlice(new PieModel("Active_Cases", Integer.parseInt(t4.getText().toString()), Color.parseColor("#112DDF")));
                            pieChart.addPieSlice(new PieModel("Death_Cases", Integer.parseInt(t7.getText().toString()), Color.parseColor("#393334")));
                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(t1.getText().toString()), Color.parseColor("#FF1744")));
                            pieChart.startAnimation();

                            sal.stop();
                            sal.setVisibility(View.GONE);
                            sv.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();

                            sal.stop();
                            sal.setVisibility(View.GONE);
                            sv.setVisibility(View.VISIBLE);
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sal.stop();
                sal.setVisibility(View.GONE);
                sv.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void goTrackCountries(View view){
        startActivity(new Intent(MainActivity.this,Affected_Counteries.class));
    }

}