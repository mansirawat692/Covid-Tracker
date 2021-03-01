package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaCodec;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Affected_Counteries extends AppCompatActivity {

    public ListView listView;
    public EditText editText;
    public SimpleArcLoader simpleArcLoader;
    public Model model;
    public Adapter adapter;
    public static List<Model> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected__counteries);

        simpleArcLoader=findViewById(R.id.loader);
        listView=findViewById(R.id.lv);
        editText=findViewById(R.id.search);
        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity( new Intent(Affected_Counteries.this,Detail.class).putExtra("position", position));
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            adapter.getFilter().filter(s);
            adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void fetchData(){
        String url="https://corona.lmao.ninja/v2/countries";
        simpleArcLoader.start();
        StringRequest request=new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                            try {
                                JSONArray jsonArray=new JSONArray(response);
                                for(int i=0;i<jsonArray.length();i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String country = jsonObject.getString("country");
                                    String active = jsonObject.getString("active");
                                    String recovered = jsonObject.getString("recovered");
                                    String critical = jsonObject.getString("critical");
                                    String death = jsonObject.getString("deaths");
                                    String todaydeath = jsonObject.getString("todayDeaths");
                                    String todayCases = jsonObject.getString("todayCases");
                                    String todayRecovered = jsonObject.getString("todayRecovered");
                                    String cases = jsonObject.getString("cases");

                                    JSONObject jsonObject1 = jsonObject.getJSONObject("countryInfo");
                                    String flagurl = jsonObject1.getString("flag");
                                    model = new Model(flagurl, country, active, cases, recovered, death, todayCases, todayRecovered, todaydeath, critical);
                                    list.add(model);

                                }

                                adapter=new Adapter(Affected_Counteries.this, list);
                                listView.setAdapter(adapter);
                                simpleArcLoader.stop();
                                simpleArcLoader.setVisibility(View.GONE);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                Toast.makeText(Affected_Counteries.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}