package com.opendata.myparking.parkingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.opendata.myparking.parkingapp.R;
import com.opendata.myparking.parkingapp.activity.CreateLocationActivity;
import com.opendata.myparking.parkingapp.model.Location;

import java.util.ArrayList;

/**
 * Created by is chan on 17/04/2016.
 */
public class LocationsAdapter extends ArrayAdapter<Location> {
    private ArrayList<Location> locationArrayList;

    private FloatingActionButton fabButton;

    public LocationsAdapter (Context context, ArrayList<Location> locationArrayList){
        super(context, R.layout.cardview_location, locationArrayList);
        this.locationArrayList = locationArrayList;
    }

    public int getCount(){
        return locationArrayList.size();
    }

    public View getView(int position, View view, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_location, parent, false);

        ViewHolder viewHolder = new ViewHolder();

        viewHolder.location = (TextView)itemView.findViewById(R.id.location);
        viewHolder.cost = (TextView)itemView.findViewById(R.id.cost);

        final String location_name = locationArrayList.get(position).getLocation_name();
        final Double cost = locationArrayList.get(position).getCost();

        viewHolder.location.setText("Name :" + location_name);
        viewHolder.cost.setText("Cost/min : " + cost);


        return itemView;
    }

    private class ViewHolder {
        protected TextView location;
        protected TextView cost;
    }
}
