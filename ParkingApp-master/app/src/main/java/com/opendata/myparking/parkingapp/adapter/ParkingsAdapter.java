package com.opendata.myparking.parkingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.opendata.myparking.parkingapp.R;
import com.opendata.myparking.parkingapp.model.Parking;

import java.util.ArrayList;

/**
 * Created by is chan on 07/04/2016.
 * Temporary Arraylist of Vehicle
 */
public class ParkingsAdapter extends ArrayAdapter<Parking> {
    private ArrayList<Parking> parkingArrayList;
    //private DBOpenHelper db;

    public ParkingsAdapter (Context context, ArrayList<Parking> parkingArrayList){
        super(context, R.layout.cardview_parking, parkingArrayList);
        //db = new DBOpenHelper(context.getApplicationContext());

        this.parkingArrayList = parkingArrayList;
    }

    public int getCount(){
        return parkingArrayList.size();
    }


    public View getView(int position, View view, ViewGroup parent) {


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_parking, parent, false);

        ViewHolder viewHolder = new ViewHolder();

        viewHolder.plateNumber = (TextView)itemView.findViewById(R.id.plate_number);
        viewHolder.timeIn = (TextView)itemView.findViewById(R.id.time_in);
        viewHolder.timeOut = (TextView)itemView.findViewById(R.id.time_out);
        viewHolder.charge = (TextView)itemView.findViewById(R.id.charge);
        // comment for the moment as we cant find the vehicle from the platenumber
        /*viewHolder.brand = (TextView)itemView.findViewById(R.id.brand);
        viewHolder.model = (TextView)itemView.findViewById(R.id.model);
        viewHolder.colour = (TextView)itemView.findViewById(R.id.colour);
        viewHolder.year = (TextView)itemView.findViewById(R.id.year);*/
        //


        //using data from database
        final String plateNum = parkingArrayList.get(position).getPlateNumber(); // UPDATED to check for vehicle Id. Plate number was here.
        final String timein = parkingArrayList.get(position).getTime_in();
        final String timeout = parkingArrayList.get(position).getTime_out();
        final Double charge = parkingArrayList.get(position).getCharge();
        //Vehicle vce = db.getVehicle(plate_number);
        //Location loc = db.getLocation(parkingArrayList.get(position).getKey_location_id());

        //db.closeDB();

        // comment for the moment as we cant find the vehicle from the platenumber
        /*final String brand = vce.getBrand();
        final String model = vce.getModel();
        final String year = vce.getYearManufactured();
        final String colour = vce.getColor();*/
        //

        viewHolder.plateNumber.setText(plateNum); // Updated to vehicleId .. Plate number was here. String.valueOf(vehId)
        viewHolder.timeIn.setText("Time in: " +timein);
        viewHolder.timeOut.setText("Time out: " +timeout);
        viewHolder.charge.setText("Charge: " + String.valueOf(charge));
        // comment for the moment as we cant find the vehicle from the platenumber
        /*viewHolder.brand.setText("Brand: " + brand);
        viewHolder.model.setText("Model: " + model);
        viewHolder.year.setText("Year: " + year);
        viewHolder.colour.setText("Colour: " + colour);*/
        //

        /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Click", "TextView clicked on row ");
                Intent intent = new Intent(view.getContext(), VehicleDetailsActivity.class);
                //Bundle bundle = new Bundle();
                Log.d("forviewdetails", "Plate Number list: " + plate_number);
                intent.putExtra("plate_number", plate_number);
                intent.putExtra("brand", brand);
                intent.putExtra("model", model);
                intent.putExtra("colour", colour);
                intent.putExtra("year", year);

                view.getContext().startActivity(intent);
            }
        });*/
        return itemView;
    }


    private class ViewHolder {
        protected TextView plateNumber;
        protected TextView timeIn;
        protected TextView timeOut;
        protected TextView charge;
        protected TextView model;
        protected TextView colour;
        protected TextView year;
    }
}
