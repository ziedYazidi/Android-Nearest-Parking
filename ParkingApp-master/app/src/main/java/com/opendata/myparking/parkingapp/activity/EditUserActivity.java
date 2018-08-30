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
import android.widget.Toast;

import com.opendata.myparking.parkingapp.R;
import com.opendata.myparking.parkingapp.database.DBOpenHelper;
import com.opendata.myparking.parkingapp.model.Location;
import com.opendata.myparking.parkingapp.model.User;

/**
 * Created by Shamel on 22/04/2016.
 */
public class EditUserActivity extends ActionBarActivity {

    private TextInputLayout inputLayoutUserId;
    private TextInputLayout inputLayoutCreditNumber;
    private TextInputLayout inputLayoutPlateNumber;
    private TextInputLayout inputLayoutBalance;
    private TextInputLayout inputLayoutErrorBalance;
    private EditText inputUserId;
    private EditText inputCreditNumber;
    private EditText inputPlateNumber;
    private EditText inputBalance;
    private EditText inputErrorBalance;
    private Button btnSubmit;
    private Toolbar toolbar;
    private DBOpenHelper db;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        inputLayoutUserId = (TextInputLayout) findViewById(R.id.input_layout_user_id);
        inputLayoutCreditNumber = (TextInputLayout) findViewById(R.id.input_layout_credit_number);
        inputLayoutPlateNumber = (TextInputLayout) findViewById(R.id.input_layout_plate_number);
        inputLayoutBalance = (TextInputLayout) findViewById(R.id.input_layout_balance);
        inputLayoutErrorBalance = (TextInputLayout) findViewById(R.id.input_layout_error_balance);
        inputUserId = (EditText) findViewById(R.id.input_user_id);
        inputCreditNumber = (EditText) findViewById(R.id.input_credit_number);
        inputPlateNumber = (EditText) findViewById(R.id.input_plate_number);
        inputBalance = (EditText) findViewById(R.id.input_balance);
        inputErrorBalance = (EditText) findViewById(R.id.input_error_balance);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Edit My Profile"); //Set Title on Toolbar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                db = new DBOpenHelper(getApplicationContext());
                String userId = inputUserId.getText().toString().trim();
                String creditNumber = inputCreditNumber.getText().toString().trim();
                String plateNumber = inputPlateNumber.getText().toString().trim();
                float balance = Float.parseFloat(inputBalance.getText().toString().trim());
                float errorBalance = Float.parseFloat(inputErrorBalance.getText().toString().trim());
                //inputLocation(location, cost);
                db.closeDB();
                onBackPressed();
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

    /**
     * Input locatiopn of parking to Database
     * @param location_name is a location name
     * @param cost is cost per hour
     *//*
    public void inputLocation(String location_name, int cost){
        location = new Location();
        location.setLocation_name(location_name);
        location.setCost(cost);

        long location_id = db.createLocation(location);

        Toast.makeText(getApplicationContext(), "The location of parking is submitted.", Toast.LENGTH_SHORT).show();
    }*/

}
