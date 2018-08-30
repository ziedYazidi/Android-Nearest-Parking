package com.opendata.myparking.parkingapp.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.opendata.myparking.parkingapp.R;

/**
 * Created by Shamel on 26/04/2016.
 */
public class EditParkingActivity extends ActionBarActivity {

    private TextInputLayout inputLayoutKeyVehicleId;
    private TextInputLayout inputLayoutKeyLocationId;
    private TextInputLayout inputLayoutTimeIn;
    private TextInputLayout inputLayoutTimeOut;
    private TextInputLayout inputLayoutActive;
    private EditText inputKeyVehicleId;
    private EditText inputKeyLocationId;
    private EditText inputTimeIn;
    private EditText inputTimeOut;
    private EditText inputActive;
    private Button btnSubmit;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_parking);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        inputLayoutKeyVehicleId = (TextInputLayout) findViewById(R.id.input_layout_key_vehicle_id);
        inputLayoutKeyLocationId = (TextInputLayout) findViewById(R.id.input_layout_key_location_id);
        inputLayoutTimeIn = (TextInputLayout) findViewById(R.id.input_layout_time_in);
        inputLayoutTimeOut = (TextInputLayout) findViewById(R.id.input_layout_time_out);
        inputLayoutActive = (TextInputLayout) findViewById(R.id.input_layout_active);
        inputKeyVehicleId = (EditText) findViewById(R.id.input_key_vehicle_id);
        inputKeyLocationId = (EditText) findViewById(R.id.input_key_location_id);
        inputTimeIn = (EditText) findViewById(R.id.input_time_in);
        inputTimeOut = (EditText) findViewById(R.id.input_time_out);
        inputActive = (EditText) findViewById(R.id.input_active);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Edit Parking"); //Set Title on Toolbar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //to be implemented to handle parking edition in db
        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               /* db = new DBOpenHelper(getApplicationContext());
                String location = inputLocation.getText().toString().trim();
                int cost = Integer.parseInt(inputCost.getText().toString().trim());
                inputLocation(location, cost);
                db.closeDB();
                onBackPressed();*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Action when the menu on the toolbar pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_clear:
                return true;
            case R.id.action_done:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
