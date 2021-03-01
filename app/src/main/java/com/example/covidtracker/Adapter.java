package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends ArrayAdapter<Model> {

    public Context context;
    public List<Model> list;
    public List<Model> listfilter;
    public Adapter(@NonNull Context context, List<Model> list) {
        super(context, R.layout.model_adapter,list);
        this.context=context;
        this.list=list;
        this.listfilter=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.model_adapter, null,true);

        TextView tv=view.findViewById(R.id.country);
        CircleImageView cv=view.findViewById(R.id.flag);
        tv.setText(listfilter.get(position).getCountry());

        Glide.with(context).load(listfilter.get(position).getFlag()).into(cv);
        return view;
    }

    @Override
    public int getCount() {
        return listfilter.size();
    }

    @Nullable
    @Override
    public Model getItem(int position) {
        return listfilter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = list.size();
                    filterResults.values = list;

                }else{
                    List<Model> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(Model itemsModel:list){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                listfilter = (List<Model>) results.values;
                Affected_Counteries.list = (List<Model>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
