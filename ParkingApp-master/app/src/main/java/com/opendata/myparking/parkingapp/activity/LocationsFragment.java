package com.opendata.myparking.parkingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.opendata.myparking.parkingapp.R;
import com.opendata.myparking.parkingapp.adapter.LocationsAdapter;
import com.opendata.myparking.parkingapp.database.DBOpenHelper;
import com.opendata.myparking.parkingapp.model.Location;

import java.util.ArrayList;

/**
 * Created by is chan on 17/04/2016.
 */
public class LocationsFragment extends Fragment {
    private ListView listLocations;
    private ArrayList<Location> locationArrayList; //temporary for testing
    private LocationsAdapter locationsAdapter;

    private Location location;

    private DBOpenHelper db;
    private TextInputLayout inputLayoutLocation;
    private TextInputLayout inputLayoutCost;
    private EditText inputLocation;
    private EditText inputCost;
    private Button btnSubmit;
    private FloatingActionButton fabButton;
    private FloatingActionButton fabButtonEdit;
    private int intIndexSelected;


    public LocationsFragment(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_locations, container, false);

        fabButton = (FloatingActionButton) rootView.findViewById(R.id.fabButton);

        fabButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                /*String location = inputLocation.getText().toString().trim();
                int cost = Integer.parseInt(inputCost.getText().toString().trim());
                inputLocation(location, cost);*/

                Intent intent = new Intent(LocationsFragment.this.getActivity(), CreateLocationActivity.class);
                startActivity(intent);
            }
        });





        //Build access to database
        db = new DBOpenHelper(getActivity().getApplicationContext());

        locationsAdapter = new LocationsAdapter(getContext(), getLocations());
        listLocations = (ListView) rootView.findViewById(R.id.list_plates);

        listLocations.setAdapter(locationsAdapter);

        //Dont forget to close database
        db.closeDB();

        //Register the ListView for Context menu
        registerForContextMenu(listLocations);
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
            //

            Intent intent = new Intent(LocationsFragment.this.getActivity(), EditLocationActivity.class);
            startActivity(intent);



        }
        return true;
    }
    /**
     * Input locatiopn of parking to Database
     * @param location_name is a location name
     * @param cost is cost per hour
     */
    public void inputLocation(String location_name, Double cost){
        location = new Location();
        location.setLocation_name(location_name);
        location.setCost(cost);

        long location_id = db.createLocation(location);

        Toast.makeText(getActivity().getApplicationContext(), "The location of parking is submitted.", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<Location> getLocations(){
        locationArrayList = db.getAllLocations();
        return locationArrayList;
    }
}
