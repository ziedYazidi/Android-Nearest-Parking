package com.opendata.myparking.parkingapp.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.opendata.myparking.parkingapp.R;
import com.opendata.myparking.parkingapp.database.DBOpenHelper;
import com.opendata.myparking.parkingapp.model.Location;
import com.opendata.myparking.parkingapp.model.Parking;
import com.opendata.myparking.parkingapp.model.Vehicle;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.openalpr.OpenALPR;
import org.openalpr.model.Results;
import org.openalpr.model.ResultsError;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by is chan on 17/04/2016.
 */
public class HomeFragment extends Fragment {
    private static final int REQUEST_IMAGE = 100;
    final int STORAGE=1;
    private String ANDROID_DATA_DIR;
    private static File destination;
    private TextView resultTextView;
    private ImageView imageView;
    private FloatingActionButton fabButton;
    private FloatingActionButton clearButton;
    //private String plate_number;
    //private DBOpenHelper db;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DBOpenHelper db = new DBOpenHelper(getActivity().getApplicationContext());
        //Vehicle vById = db.getVehicleById(1);
        //Log.d("Results vById: ","Id= " + vById.getId() + " Numberplate= " + vById.getPlateNumber() + " Brand= " + vById.getBrand() + " Model= " + vById.getModel() + " yr= " + vById.getYearManufactured());

        /*

        DBOpenHelper db = new DBOpenHelper(getActivity().getApplicationContext());
        ArrayList<Parking> allParking = db.getAllParkings();
        for (Parking p: allParking) {
            Log.d("Parking Results: ","Id= " + p.getParking_id() + " Veh Id= " + p.getKey_vehicle_id() + " Platenum= " + p.getPlateNumber());

        }

        DBOpenHelper db = new DBOpenHelper(getActivity().getApplicationContext());
        More testing..on dates
        ArrayList<Parking> allParking = db.getAllParkings();
        for (Parking p: allParking) {
            Log.d("Parking Results: ","Id= " + p.getParking_id() + " Veh Id= " + p.getKey_vehicle_id() + " LocId= " + p.getKey_location_id() + " TimeIn= " + p.getTime_in() + " TimeOut= " + p.getTime_out() + " charge= " + p.getCharge() + " status=" + p.getActive());

            if (p.getParking_id() == 2){
                Date tOut = stringToDate(p.getTime_out());
                Date tIn = stringToDate(p.getTime_in());
                //long diffTimes = tOut.getTime() -  tIn.getTime();

                Log.d("(a)"," Time In "+ tIn + " Time Out=" + tOut);
                Log.d("(b)"," Time In "+  p.getTime_in() + " Time Out=" + p.getTime_out());
            }
        }

        Log.d("*****Testing: ", "Parking******");
        //Parking testing.
        Parking park1 = new Parking(1,1,1,5.0);
        Parking park2 = new Parking(2,1,1,15.0);
        Parking park3 = new Parking(3,1,1,115.0);
        Parking park4 = new Parking(4,1,1,1115.0);
        Parking park5 = new Parking(5,1,1,1115.0);

        long parkingId1 = db.createParking(park1);
        long parkingId2 = db.createParking(park2);
        long parkingId3 = db.createParking(park3);
        long parkingId4 = db.createParking(park4);
        long parkingId5 = db.createParking(park5);

        ArrayList<Parking> allParking = db.getAllParkings();
        for (Parking p: allParking) {
            Log.d("Results: ","Id= " + p.getParking_id() + " Veh Id= " + p.getKey_vehicle_id() + " LocId= " + p.getKey_location_id() + " TimeIn= " + p.getTime_in() + " charge= " + p.getCharge());

        }

        Parking oneParking = db.getParkingById(parkingId1);
        Log.d("oneParking: ","Id= " + oneParking.getParking_id() + " Veh Id= " + oneParking.getKey_vehicle_id() + " LocId= " + oneParking.getKey_location_id() + " TimeIn= " + oneParking.getTime_in() + " charge= " + oneParking.getCharge());


        String pCount = String.valueOf(db.countParking());
        Log.d("Parking count = ", pCount);


        Log.d("*****Testing: ", "Vehicle******");
        //Vehicle Testing
        Vehicle vehicle1 = new Vehicle("ABC1234","Brand 1","Model 1","1997","Blue");
        Vehicle vehicle2 = new Vehicle("EFG1234","Brand 1","Model 1","1997","Blue");
        Vehicle vehicle3 = new Vehicle("HIJK1234","Brand 1","Model 1","1997","Blue");
        Vehicle vehicle4 = new Vehicle("LMNO1234","Brand 1","Model 1","1997","Blue");
        Vehicle vehicle5 = new Vehicle("PQR1234","Brand 1","Model 1","1997","Blue");

        long vehicleId1 = db.createVehicle(vehicle1);
        long vehicleId2 = db.createVehicle(vehicle2);
        long vehicleId3 = db.createVehicle(vehicle3);
        long vehicleId4 = db.createVehicle(vehicle4);
        long vehicleId5 = db.createVehicle(vehicle5);

        ArrayList<Vehicle> allVehicle = db.getAllVehicles();
        for (Vehicle v: allVehicle) {
            Log.d("Results: ","Id= " + v.getId() + " Numberplate= " + v.getPlateNumber() + " Brand= " + v.getBrand() + " Model= " + v.getModel() + " yr= " + v.getYearManufactured());
        }

        Vehicle vById = db.getVehicleById(vehicleId1);
        Log.d("Results vById: ","Id= " + vById.getId() + " Numberplate= " + vById.getPlateNumber() + " Brand= " + vById.getBrand() + " Model= " + vById.getModel() + " yr= " + vById.getYearManufactured());

        Vehicle vByPlate = db.getVehicleByPlateNumber(vehicle1.getPlateNumber());
        Log.d("Results vByPlate: ","Id= " + vByPlate.getId() + " Numberplate= " + vByPlate.getPlateNumber() + " Brand= " + vByPlate.getBrand() + " Model= " + vByPlate.getModel() + " yr= " + vByPlate.getYearManufactured());

        String vCount = String.valueOf(db.countVehicle());
        Log.d("Vehicle count = ", vCount);

        ArrayList<Location> allLocation = db.getAllLocations();
        for (Location l: allLocation) {
            Log.d("Location Results: ", "Id= " + l.getLocation_id() + " Loc Name= " + l.getLocation_name() + " Loc Cost= " + l.getCost());
        }*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        ANDROID_DATA_DIR = getActivity().getApplicationInfo().dataDir;


        resultTextView = (TextView) rootView.findViewById(R.id.textView);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);

        rootView.findViewById(R.id.fabButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();
            }
        });

        resultTextView.setText("Welcome. \r\n Press the red button to scan plate number.");
        FloatingActionButton reset = (FloatingActionButton)rootView.findViewById(R.id.clearButton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //v.findViewWithTag(imageView);
                imageView.setImageResource(android.R.color.transparent);
                resultTextView.setText("Welcome. \r\n Press the red button to scan plate number.");
            }
        });

        /**
         * For testing only
         * plate_number = "MT09NKS";
         */
        return rootView;
    }

    /**
     * Check permission to write the Phone's storage
     */
    private void checkPermission() {
        List<String> permissions = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }
        if (!permissions.isEmpty()) {
            Toast.makeText(getActivity(), "Storage access needed to manage the picture.", Toast.LENGTH_LONG).show();
            String[] params = permissions.toArray(new String[permissions.size()]);
            ActivityCompat.requestPermissions(getActivity(), params, STORAGE);
        } else { // We already have permissions, so handle as normal
            takePicture();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE && resultCode == Activity.RESULT_OK) {
            final ProgressDialog progress = ProgressDialog.show(getActivity(), "Loading", "Parsing result...", true);
            final String openAlprConfFile = ANDROID_DATA_DIR + File.separatorChar + "runtime_data" + File.separatorChar + "openalpr.conf";
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 10;

            // Picasso requires permission.WRITE_EXTERNAL_STORAGE
            Picasso.with(getActivity()).load(destination).fit().centerCrop().into(imageView);
            resultTextView.setText("Processing");

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    String result = OpenALPR.Factory.create(getActivity(), ANDROID_DATA_DIR).recognizeWithCountryRegionNConfig("eu", "", destination.getAbsolutePath(), openAlprConfFile, 10);

                    Log.d("OPEN ALPR", result);

                    try {
                        final Results results = new Gson().fromJson(result, Results.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (results == null || results.getResults() == null || results.getResults().size() == 0) {
                                    Toast.makeText(getActivity(), "It was not possible to detect the licence plate.", Toast.LENGTH_LONG).show();
                                    resultTextView.setText("It was not possible to detect the licence plate.");
                                } else {

                                    DBOpenHelper db = new DBOpenHelper(getActivity().getApplicationContext());
                                    Location aLocation = db.createDefaultLocation(); // Creates a default locaton with value.

                                    resultTextView.setText("Plate Number: " + results.getResults().get(0).getPlate() + "."
                                            + "\r\n Accuracy: "  + String.format("%.2f", results.getResults().get(0).getConfidence()) + "%");

                                    /*
                                    *       resultTextView.setText("Plate Number: " + results.getResults().get(0).getPlate()
                                            // Trim confidence to two decimal places
                                            + " Confidence: " + String.format("%.2f", results.getResults().get(0).getConfidence()) + "%"
                                            // Convert processing time to seconds and trim to two decimal places
                                            + " Processing time: " + String.format("%.2f", ((results.getProcessing_time_ms() / 1000.0) % 60)) + " seconds");
                                    * */

                                    String plate_number = results.getResults().get(0).getPlate(); //  "ABCx1234";
                                    if (db.isVehicleExist(plate_number)){
                                        //yes .. exist.
                                        Log.d("Messageeesss","1");
                                        Vehicle aVehicle = db.getVehicleByPlateNumber(plate_number); // get the vehicle
                                        if (db.isVehicleParked(aVehicle.getId())){
                                            //yes.. vehicle is parked
                                            Log.d("Messageeesss","2");
                                            Parking aParking = db.getParkingByVehicleId(aVehicle.getId()); // get current parking record for this vehicle.

                                            Date tOut = new Date();
                                            Date tIn = stringToDate(aParking.getTime_in());
                                            long duration = tOut.getTime() - tIn.getTime();
                                            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
                                            aParking.setTime_out(dateToString(tOut,"yyyy-MM-dd HH:mm:ss"));
                                            Log.d("Messageeesss","2:::" + tOut);
                                            aParking.setCharge(diffInMinutes * aLocation.getCost()); // set charge.
                                            aParking.setActive(0); // 0 is inactive
                                            db.updateParking(aParking);

                                        }else{
                                            //no.. vehicle is not parked.
                                            Log.d("Messageeesss","3");
                                            Date tIn = new Date();
                                            Log.d("Messageeesss","3:::" + tIn);
                                            Parking p = new Parking(aVehicle.getId(),aLocation.getLocation_id(),dateToString(tIn,"yyyy-MM-dd HH:mm:ss"));
                                            db.createParking(p);
                                        }

                                    }else{
                                        //doesnt exist .. so insert.
                                        Log.d("Messageeesss","4");
                                        String urlString = "https://dvlasearch.appspot.com/DvlaSearch?licencePlate=" + plate_number + "&apikey=DvlaSearchDemoAccount";
                                        new JSONTask().execute(urlString,plate_number);
                                    }
                                    db.closeDB();
                                }
                            }
                        });

                    } catch (JsonSyntaxException exception) {
                        final ResultsError resultsError = new Gson().fromJson(result, ResultsError.class);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                resultTextView.setText(resultsError.getMsg());
                            }
                        });
                    }

                    progress.dismiss();
                }
            });
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case STORAGE:{
                Map<String, Integer> perms = new HashMap<>();
                // Initial
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for WRITE_EXTERNAL_STORAGE
                Boolean storage = perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                if (storage) {
                    // permission was granted, yay!
                    takePicture();
                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "Storage permission is needed to analyse the picture.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    /**
     * Taking a picture
     */
    public void takePicture() {
        // Use a folder to store all results
        File folder = new File(Environment.getExternalStorageDirectory() + "/OpenParking/");
        if (!folder.exists()) {
            folder.mkdir();
        }

        // Generate the path for the next photo
        String name = dateToString(new Date(), "yyyy-MM-dd-hh-mm-ss");
        destination = new File(folder, name + ".jpg");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destination));
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    //This works
    private String dateToString(Date date, String format) {
        if (date != null && format != null){
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        }else{
            return null;
        }

    }
    private Date stringToDate(String dateString){
        Date date = new Date();

        if (dateString != null){
            try{
                DateFormat formatter;
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = (Date)formatter.parse(dateString);
            }catch(ParseException ex){
                ex.printStackTrace();
            }

        }

        return date;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (destination != null) {// Picasso does not seem to have an issue with a null value, but to be safe
            Picasso.with(getActivity()).load(destination).fit().centerCrop().into(imageView);
        }
    }

    public class JSONTask extends AsyncTask<String,String,Vehicle> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dialog.show();
        }

        @Override
        protected Vehicle doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            Vehicle vehicle = new Vehicle();
            Log.d("Messageeesss","5");

            try {

                URL url = new URL(params[0]);
                String plate_number = params[1];

                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);

                if (!parentObject.has("error")){
                    Log.d("Messageeesss","6");
                    String make = parentObject.getString("make");
                    String model = parentObject.getString("model");
                    String colour = parentObject.getString("colour");
                    String year = parentObject.getString("yearOfManufacture");

                    vehicle.setPlateNumber(plate_number);
                    vehicle.setBrand(make);
                    vehicle.setModel(model);
                    vehicle.setYearManufactured(year);
                    vehicle.setColor(colour);

                }else {
                    Log.d("Messageeesss","7");
                    vehicle.setPlateNumber(plate_number);
                    vehicle.setBrand("Unknown");
                    vehicle.setModel("Unknown");
                    vehicle.setYearManufactured("Unknown");
                    vehicle.setColor("Unknown");

                }

                Log.d("Messageeesss","8");
                DBOpenHelper db = new DBOpenHelper(getActivity().getApplicationContext());
                Date tIn = new Date();
                long vehicle_id = db.createVehicle(vehicle);
                Location aLocation = db.createDefaultLocation(); // Creates a default locaton with value.
                Parking park = new Parking(vehicle_id,aLocation.getLocation_id(),dateToString(tIn,"yyyy-MM-dd HH:mm:ss"));
                long parkingId = db.createParking(park);
                Parking p = db.getParkingById(parkingId);
                db.closeDB();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return  null;

        }

        @Override
        protected void onPostExecute(final Vehicle result) {
            super.onPostExecute(result);

            try {
                Log.d("Hey","lo");
            } catch(Exception e) {

                //dialog.dismiss();
                Toast.makeText(getActivity().getApplicationContext(), "The plate number is not recognised (A).", Toast.LENGTH_SHORT).show();
            }

            //dialog.dismiss();
            if(result != null) {
                Log.d("Hey","yo");
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "A similar matching plate was found. Please try again.", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
