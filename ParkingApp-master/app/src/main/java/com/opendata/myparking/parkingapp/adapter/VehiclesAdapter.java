package com.opendata.myparking.parkingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.opendata.myparking.parkingapp.R;
import com.opendata.myparking.parkingapp.model.Vehicle;

import java.util.ArrayList;

/**
 * Created by is chan on 17/04/2016.
 */
public class VehiclesAdapter extends ArrayAdapter<Vehicle> {

    private ArrayList<Vehicle> vehicleArrayList;

    public VehiclesAdapter (Context context, ArrayList<Vehicle> vehicleArrayList){
        super(context, R.layout.cardview_parking, vehicleArrayList);
        this.vehicleArrayList = vehicleArrayList; //comment if wish to test UI
    }

    public View getView(int position, View view, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_vehicle, parent, false);

        ViewHolder viewHolder = new ViewHolder();

        viewHolder.plateNumber = (TextView)itemView.findViewById(R.id.plate_number);
        viewHolder.brand = (TextView)itemView.findViewById(R.id.brand);
        viewHolder.model = (TextView)itemView.findViewById(R.id.model);
        viewHolder.colour = (TextView)itemView.findViewById(R.id.colour);
        viewHolder.year = (TextView)itemView.findViewById(R.id.year);

        //using data from database
        final String plate_number = vehicleArrayList.get(position).getPlateNumber();
        final String brand = vehicleArrayList.get(position).getBrand();
        final String model = vehicleArrayList.get(position).getModel();
        final String year = vehicleArrayList.get(position).getYearManufactured();
        final String colour = vehicleArrayList.get(position).getColor();

        viewHolder.plateNumber.setText(plate_number.toUpperCase());
        viewHolder.brand.setText("Brand: " + brand);
        viewHolder.model.setText("Model: " + model);
        viewHolder.year.setText("Year: " + year);
        viewHolder.colour.setText("Colour: " + colour);

        //for testing UI only
        /*viewHolder.plateNumber.setText("MT09NKS");
        viewHolder.brand.setText("Fiat");
        viewHolder.model.setText("405");
        viewHolder.year.setText("2010");
        viewHolder.colour.setText("Blue");*/

        return itemView;
    }

    public int getCount(){
        return vehicleArrayList.size();
    }

    private class ViewHolder {
        protected TextView plateNumber;
        protected TextView brand;
        protected TextView model;
        protected TextView colour;
        protected TextView year;
    }

}
