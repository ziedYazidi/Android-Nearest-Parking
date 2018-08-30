package com.opendata.myparking.parkingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.opendata.myparking.parkingapp.R;
import com.opendata.myparking.parkingapp.adapter.ParkingsAdapter;
import com.opendata.myparking.parkingapp.database.DBOpenHelper;
import com.opendata.myparking.parkingapp.model.Parking;

import java.util.ArrayList;

/**
 * Created by is chan on 17/04/2016.
 */
public class ParkingsFragment extends Fragment {
    private ListView listPlateNumbers;
    private ArrayList<Parking> listParkings; //temporary for testing
    private ParkingsAdapter adapterparking;

    private DBOpenHelper db;
    private int intIndexSelected;

    private FloatingActionButton fabButton;

    public ParkingsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_parkings, container, false);

        // create a new parking
        fabButton = (FloatingActionButton) rootView.findViewById(R.id.fabButton);

        fabButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                /*String location = inputLocation.getText().toString().trim();
                int cost = Integer.parseInt(inputCost.getText().toString().trim());
                inputLocation(location, cost);*/

                Intent intent = new Intent(ParkingsFragment.this.getActivity(), CreateParkingActivity.class);
                startActivity(intent);
            }
        });


        db = new DBOpenHelper(getActivity().getApplicationContext());

        listParkings = db.getAllParkings();

        adapterparking = new ParkingsAdapter(getContext(), listParkings);
        listPlateNumbers = (ListView) rootView.findViewById(R.id.list_plates);

        listPlateNumbers.setAdapter(adapterparking);

        db.closeDB();

        registerForContextMenu(listPlateNumbers);


        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Delete");
        menu.add(0, v.getId(), 0, "Update");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //  info.position will give the index of selected item
        intIndexSelected = info.position;
        if (item.getTitle() == "Delete") {

            // Code to execute when clicked on This Item
        } else if (item.getTitle() == "Update") {

            // Code to execute when clicked on This Item
            Intent intent = new Intent(ParkingsFragment.this.getActivity(), EditParkingActivity.class);
            startActivity(intent);


        }
        return true;
    }
}
