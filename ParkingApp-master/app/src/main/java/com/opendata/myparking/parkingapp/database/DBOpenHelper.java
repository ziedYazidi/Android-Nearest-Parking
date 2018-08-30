package com.opendata.myparking.parkingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.opendata.myparking.parkingapp.model.Location;
import com.opendata.myparking.parkingapp.model.Parking;
import com.opendata.myparking.parkingapp.model.User;
import com.opendata.myparking.parkingapp.model.Vehicle;

import java.util.ArrayList;


/**
 * Created by is chan on 17/04/2016.
 * Updated by dp on 23/04/2016.
 */
public class DBOpenHelper extends SQLiteOpenHelper{
    // Logcat tag
    private static final String LOG = "DBOpenHelper";

    //Constants for db name and version
    private static final String DATABASE_NAME = "openparking.db";
    private static final int DATABASE_VERSION = 3; //incremented


    //Constants for identifying table & column

    //Table names
    public static final String TABLE_PARKING = "parking";
    public static final String TABLE_LOCATION = "location";
    public static final String TABLE_VEHICLE = "vehicle";
    public static final String TABLE_USER = "user";

    // Common column names
    private static final String KEY_ID = "_id";

    //Active status for parking table.
    private static final int ACTIVE_STATUS = 1;
    private static final int INACTIVE_STATUS = 0;

    // PARKING Table - column names
    //public static final String PARKING_ID = "_id";
    public static final String TIME_IN = "timein"; //datetime values.
    public static final String TIME_OUT = "timeout"; //datetime values.
    public static final String ACTIVE = "active";
    private static final String KEY_LOCATION_ID = "location_id";
    private static final String KEY_VEHICLE_ID = "vehicle_id";
    public static final String CHARGE = "charge";

    // LOCATION Table - column names
    //public static final String LOCATION_ID = "_id";
    public static final String LOCATION_NAME = "location_name";
    public static final String COST = "cost";

    // VEHICLE Table - column names
    public static final String PLATE_NUMBER = "plate_number";
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String YEAR_MANUFACTURED = "year_manufactured";
    public static final String COLOR = "color";

    // User Table - column names
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String BALANCE = "balance";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public static final String [] ALL_COLUMNS_PARKING =
            {KEY_ID, TIME_IN, TIME_OUT, ACTIVE, KEY_LOCATION_ID, KEY_VEHICLE_ID, CHARGE};

    public static final String [] ALL_COLUMNS_LOCATION =
            {KEY_ID, LOCATION_NAME, COST};

    public static final String [] ALL_COLUMNS_VEHICLE =
            {KEY_ID, PLATE_NUMBER, BRAND, MODEL, YEAR_MANUFACTURED, COLOR};

    public static final String [] ALL_COLUMNS_USER =
            {KEY_ID, FIRST_NAME, LAST_NAME, BALANCE, PLATE_NUMBER,PASSWORD,USERNAME};


    // parking table create statement
    //KEY_VEHICLE_ID set for plate number
    private static final String CREATE_TABLE_PARKING =
            "CREATE TABLE " + TABLE_PARKING + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + //autoincrement added for pk
                    TIME_IN + " DATE, " +
                    TIME_OUT + " DATE, " +
                    ACTIVE + " INTEGER, " +
                    CHARGE + " DECIMAL, " +
                    KEY_LOCATION_ID + " INTEGER, " +
                    KEY_VEHICLE_ID + " TEXT" +
                    ");";


    // location table create statement
    private static final String CREATE_TABLE_LOCATION =
            "CREATE TABLE " + TABLE_LOCATION + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LOCATION_NAME + " TEXT, " +
                    COST + " INTEGER" +
                    ");";


    // vehicle table create statement
    private static final String CREATE_TABLE_VEHICLE =
            "CREATE TABLE " + TABLE_VEHICLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PLATE_NUMBER + " TEXT, " +
                    BRAND + " TEXT, " +
                    MODEL + " TEXT, " +
                    YEAR_MANUFACTURED + " DATE, " +
                    COLOR + " TEXT" +
                    ");";

    // user table create statement (dp)
    private static final String CREATE_TABLE_USER =
            "CREATE TABLE " + TABLE_USER + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FIRST_NAME + " TEXT, " +
                    LAST_NAME + " TEXT, " +
                    BALANCE + " DECIMAL, " +
                    PLATE_NUMBER + " TEXT, " +
                    USERNAME + " TEXT, " +
                    PASSWORD + " TEXT " +
                    ");";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL(TABLE_CREATE);
        // creating required tables
        db.execSQL(CREATE_TABLE_PARKING);
        db.execSQL(CREATE_TABLE_LOCATION);
        db.execSQL(CREATE_TABLE_VEHICLE);
        db.execSQL(CREATE_TABLE_USER); //new

        Log.d("DBOpenHelper","db created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARKING);
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARKING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER); //new
        onCreate(db);

        Log.d("DBOpenHelper","db dropped and re-created");
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

// ------------------------ "Parking" table methods ----------------//
    public int countParking(){
        String count = "SELECT * FROM " + TABLE_PARKING ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(count, null);

        int pCount = c.getCount();
        c.close();
        return pCount;
    }

    public boolean isVehicleParked (long vehicleId){

        String count = "SELECT * FROM " + TABLE_PARKING +
                        " WHERE " + KEY_VEHICLE_ID + " = " + vehicleId +
                        " AND " + ACTIVE + " = " + ACTIVE_STATUS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(count, null);

        int pCount = c.getCount();
        c.close();

        if (pCount > 0)
            return  true;
        else
            return false;

    }
    public Parking getParkingByVehicleId (long vehicleId){
        SQLiteDatabase db = this.getReadableDatabase();
        String count = "SELECT * FROM " + TABLE_PARKING +
                " WHERE " + KEY_VEHICLE_ID + " = " + vehicleId +
                " AND " + ACTIVE + " = " + ACTIVE_STATUS;
        Log.e(LOG, count);

        Cursor c = db.rawQuery(count, null);



        if(c != null)
            c.moveToFirst();

        Parking park = new Parking();
        park.setParking_id(c.getInt((c.getColumnIndex(KEY_ID))));
        park.setKey_vehicle_id(c.getInt(c.getColumnIndex(KEY_VEHICLE_ID)));
        park.setKey_location_id(c.getInt(c.getColumnIndex(KEY_LOCATION_ID)));
        park.setTime_in(c.getString(c.getColumnIndex(TIME_IN)));
        park.setTime_out(c.getString(c.getColumnIndex(TIME_OUT)));
        park.setActive(c.getInt(c.getColumnIndex(ACTIVE)));
        park.setCharge(c.getDouble(c.getColumnIndex(CHARGE))); // added charge
        //c.close();
        return park;
    }


    public long createParking (Parking parking){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VEHICLE_ID, parking.getKey_vehicle_id());
        values.put(KEY_LOCATION_ID, parking.getKey_location_id());
        values.put(ACTIVE, parking.getActive());
        values.put(CHARGE, parking.getCharge());
        values.put(TIME_IN, parking.getTime_in());
        //values.put(TIME_OUT, parking.getTime_out()); // on create park, time out will be null.


        // insert row
        long parking_id = db.insert(TABLE_PARKING, null, values);

        return parking_id;
    }

    //Getting a parking
    public Parking getParkingById(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_PARKING +
                " WHERE " + KEY_ID + " = " + id;
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null)
            c.moveToFirst();

        Parking park = new Parking();
        park.setParking_id(c.getInt((c.getColumnIndex(KEY_ID))));
        park.setKey_vehicle_id(c.getInt(c.getColumnIndex(KEY_VEHICLE_ID)));
        park.setKey_location_id(c.getInt(c.getColumnIndex(KEY_LOCATION_ID)));
        park.setTime_in(c.getString(c.getColumnIndex(TIME_IN)));
        park.setTime_out(c.getString(c.getColumnIndex(TIME_OUT)));
        park.setActive(c.getInt(c.getColumnIndex(ACTIVE)));
        park.setCharge(c.getDouble(c.getColumnIndex(CHARGE))); // added charge
        //c.close();
        return park;
    }

    /**
     * getting all parkings - returns List of parkings.
     * */
    public ArrayList<Parking> getAllParkings() {
        ArrayList<Parking> parkingList = new ArrayList<Parking>();
        String selectQuery = "SELECT  * FROM " + TABLE_PARKING + " INNER JOIN " + TABLE_VEHICLE + " ON "
                                + TABLE_PARKING + "." + KEY_VEHICLE_ID + " = " + TABLE_VEHICLE + "." + KEY_ID;

        /*
        * SELECT EMP_ID, NAME, DEPT FROM COMPANY INNER JOIN DEPARTMENT
      ON COMPANY.ID = DEPARTMENT.EMP_ID;
        * */
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Parking parking = new Parking();
                parking.setParking_id(c.getInt((c.getColumnIndex(KEY_ID))));
                parking.setTime_in(c.getString(c.getColumnIndex(TIME_IN)));
                parking.setTime_out(c.getString(c.getColumnIndex(TIME_OUT)));
                parking.setKey_location_id(c.getInt(c.getColumnIndex(KEY_LOCATION_ID)));
                parking.setKey_vehicle_id(c.getInt(c.getColumnIndex(KEY_VEHICLE_ID)));
                parking.setActive(c.getInt(c.getColumnIndex(ACTIVE)));
                parking.setCharge(c.getDouble(c.getColumnIndex(CHARGE))); // added charge
                parking.setPlateNumber(c.getString(c.getColumnIndex(PLATE_NUMBER)));

                // adding to parking list
                parkingList.add(parking);
            } while (c.moveToNext());
        }
        c.close();
        return parkingList;
    }

    /*
     * Updating a parking
     */
    public int updateParking(Parking parking) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, parking.getParking_id());
        values.put(TIME_OUT, parking.getTime_out());
        values.put(ACTIVE, parking.getActive());
        values.put(CHARGE, parking.getCharge());

        // updating row
        return db.update(TABLE_PARKING, values, KEY_ID + " = ?",
                new String[] { String.valueOf(parking.getParking_id())});
    }

    public void deleteParking(int parkingId){
        // delete by parking id instead of plate. Can have multiple parking with the same plate. Use id to get unique value.
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PARKING, KEY_ID + " = ?",
                new String[] { String.valueOf(parkingId)});
    }

// ------------------------ "vehicle" table methods ----------------//

//see http://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/
    public int countVehicle(){
        String count = "SELECT * FROM " + TABLE_VEHICLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(count, null);
        int vCount = c.getCount();
        c.close();
        return vCount;
    }

    public boolean isVehicleExist (String plate){

        String count = "SELECT * FROM " + TABLE_VEHICLE + " WHERE " + PLATE_NUMBER + " = \"" + plate + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(count, null);
        int vCount = c.getCount();
        c.close();
        if (vCount > 0)
            return  true;
        else
            return false;
    }

    /*
     * Creating vehicle
     */
    public long createVehicle(Vehicle vehicle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PLATE_NUMBER, vehicle.getPlateNumber());
        values.put(BRAND, vehicle.getBrand());
        values.put(MODEL, vehicle.getModel());
        values.put(YEAR_MANUFACTURED, vehicle.getYearManufactured());
        values.put(COLOR, vehicle.getColor());

        // insert row
        long vehicle_id = db.insert(TABLE_VEHICLE, null, values);

        return vehicle_id;
    }

    //Getting a vehicle
    public Vehicle getVehicleByPlateNumber(String plate_number){

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_VEHICLE +
                " WHERE " + PLATE_NUMBER + " = \"" + plate_number + "\"";
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null)
            c.moveToFirst();

        Vehicle vce = new Vehicle();
        vce.setId(c.getInt((c.getColumnIndex(KEY_ID))));
        vce.setPlateNumber(c.getString(c.getColumnIndex(PLATE_NUMBER)));
        vce.setBrand(c.getString(c.getColumnIndex(BRAND)));
        vce.setModel(c.getString(c.getColumnIndex(MODEL)));
        vce.setColor(c.getString(c.getColumnIndex(COLOR)));
        vce.setYearManufactured(c.getString(c.getColumnIndex(YEAR_MANUFACTURED)));
        //c.close();
        return vce;
    }

    //Getting a vehicle
    public Vehicle getVehicleById(long vehicle_id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_VEHICLE +
                " WHERE " + KEY_ID + " = " + vehicle_id;
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null)
            c.moveToFirst();

        Vehicle vce = new Vehicle();
        vce.setId(c.getInt((c.getColumnIndex(KEY_ID))));
        vce.setPlateNumber(c.getString(c.getColumnIndex(PLATE_NUMBER)));
        vce.setBrand(c.getString(c.getColumnIndex(BRAND)));
        vce.setModel(c.getString(c.getColumnIndex(MODEL)));
        vce.setColor(c.getString(c.getColumnIndex(COLOR)));
        vce.setYearManufactured(c.getString(c.getColumnIndex(YEAR_MANUFACTURED)));
        //c.close();
        return vce;
    }

    /**
     * Getting all vehicles
     * */

    public ArrayList<Vehicle> getAllVehicles() {
        ArrayList<Vehicle> vehiclesList = new ArrayList<Vehicle>();
        String selectQuery = "SELECT  * FROM " + TABLE_VEHICLE;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Vehicle vce = new Vehicle();
                vce.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                vce.setPlateNumber(c.getString(c.getColumnIndex(PLATE_NUMBER)));
                vce.setBrand(c.getString(c.getColumnIndex(BRAND)));
                vce.setModel(c.getString(c.getColumnIndex(MODEL)));
                vce.setColor(c.getString(c.getColumnIndex(COLOR)));
                vce.setYearManufactured(c.getString(c.getColumnIndex(YEAR_MANUFACTURED)));

                // adding to vehicle list
                vehiclesList.add(vce);
            } while (c.moveToNext());
        }
        c.close();
        return vehiclesList;
    }
    /*
 * Updating a parking
 */
    public int updateVehicle(Vehicle vehicle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PLATE_NUMBER, vehicle.getPlateNumber());
        values.put(BRAND, vehicle.getBrand());
        values.put(MODEL, vehicle.getModel());
        values.put(YEAR_MANUFACTURED, vehicle.getYearManufactured());
        values.put(COLOR, vehicle.getColor());

        // updating row
        return db.update(TABLE_PARKING, values, KEY_ID + " = ?",
                new String[] { String.valueOf(vehicle.getId())});
    }

    public void deleteVehicle(int vehicleId){
        // delete by vehicle id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VEHICLE, KEY_ID + " = ?",
                new String[] { String.valueOf(vehicleId)});
    }


    // ------------------------ "User" table methods ----------------//

    //see http://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/
    public int countUser(){
        String count = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(count, null);
        int vCount = c.getCount();
        c.close();
        return vCount;
    }

    public boolean isUserExist (String username){

        String count = "SELECT * FROM " + TABLE_USER +
                        " WHERE " + USERNAME + " = \"" + username + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(count, null);
        int vCount = c.getCount();
        c.close();
        if (vCount > 0)
            return  true;
        else
            return false;
    }

    /*
     * Creating vehicle
     */
    public long createUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FIRST_NAME, user.getFirst_name());
        values.put(LAST_NAME, user.getLast_name());
        values.put(PLATE_NUMBER, user.getPlate_number());
        values.put(USERNAME, user.getUsername());
        values.put(BALANCE, user.getBalance());
        values.put(PASSWORD, user.getPassword());

        // insert row
        long user_id = db.insert(TABLE_USER, null, values);

        return user_id;
    }
    public boolean matchUsernameAndPassword (String username, String password){

        String count = "SELECT * FROM " + TABLE_USER +
                        " WHERE " + USERNAME + " = \"" + username + "\""+
                        " AND " + PASSWORD + " = \"" + password + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(count, null);
        int vCount = c.getCount();
        c.close();
        if (vCount > 0)
            return  true;
        else
            return false;
    }
    //Getting a User
    public User getUserByUsernameAndPassword(String username, String password){

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USER +
                                " WHERE " + USERNAME + " = \"" + username + "\""+
                                " AND " + PASSWORD + " = \"" + password + "\"";
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null)
            c.moveToFirst();

        User user = new User();
        user.setUserId(c.getInt((c.getColumnIndex(KEY_ID))));
        user.setFirst_name(c.getString(c.getColumnIndex(FIRST_NAME)));
        user.setLast_name(c.getString(c.getColumnIndex(LAST_NAME)));
        user.setBalance(c.getDouble(c.getColumnIndex(BALANCE)));
        user.setUsername(c.getString(c.getColumnIndex(USERNAME)));
        user.setPlate_number(c.getString(c.getColumnIndex(PLATE_NUMBER)));
        user.setPassword(c.getString(c.getColumnIndex(PASSWORD)));
        //c.close();
        return user;
    }

    //Getting a User
    public User getUserById(long userId){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USER +
                " WHERE " + KEY_ID + " = " + userId;
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null)
            c.moveToFirst();

        User user = new User();
        user.setUserId(c.getInt((c.getColumnIndex(KEY_ID))));
        user.setFirst_name(c.getString(c.getColumnIndex(FIRST_NAME)));
        user.setLast_name(c.getString(c.getColumnIndex(LAST_NAME)));
        user.setBalance(c.getDouble(c.getColumnIndex(BALANCE)));
        user.setUsername(c.getString(c.getColumnIndex(USERNAME)));
        user.setPlate_number(c.getString(c.getColumnIndex(PLATE_NUMBER)));
        //c.close();
        return user;
    }
    /**
     * Getting all User
     * */

    public ArrayList<User> getAllUser() {
        ArrayList<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                User user = new User();
                user.setUserId(c.getInt((c.getColumnIndex(KEY_ID))));
                user.setFirst_name(c.getString(c.getColumnIndex(FIRST_NAME)));
                user.setLast_name(c.getString(c.getColumnIndex(LAST_NAME)));
                user.setBalance(c.getDouble(c.getColumnIndex(BALANCE)));
                user.setUsername(c.getString(c.getColumnIndex(USERNAME)));
                user.setPlate_number(c.getString(c.getColumnIndex(PLATE_NUMBER)));

                // adding to User list
                userList.add(user);
            } while (c.moveToNext());
        }
        c.close();
        return userList;
    }
    /*
 * Updating a User
 */
    public int updateUserFirstAndLastName(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FIRST_NAME, user.getFirst_name());
        values.put(LAST_NAME, user.getLast_name());

        // updating row
        return db.update(TABLE_PARKING, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getUserId())});
    }
    public int updateUserName(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(USERNAME, user.getUsername());

        // updating row
        return db.update(TABLE_PARKING, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getUserId())});
    }

    public void deleteUser(int userId){
        // delete by User id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[] { String.valueOf(userId)});
    }

// ------------------------ "Location" table methods ----------------//

//see http://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/

    public boolean isLocationExist (long location_id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LOCATION +
                " WHERE " + KEY_ID + " = " + location_id;

        Cursor c = db.rawQuery(selectQuery, null);
        int lCount = c.getCount();
        if (lCount > 0)
            return true;
        else
            return false;
    }
    private boolean LocationExistByName (String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LOCATION +
                " WHERE " + LOCATION_NAME + " = \"" + name + "\"";

        Cursor c = db.rawQuery(selectQuery, null);
        int lCount = c.getCount();
        if (lCount > 0)
            return true;
        else
            return false;
    }

    /**
     * Create a location of parking
     * @param location
     * @return location id
     */
    public long createLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LOCATION_NAME, location.getLocation_name());
        values.put(COST, location.getCost());

        // insert row
        long location_id = db.insert(TABLE_LOCATION, null, values);
        return location_id;
    }
    public Location createDefaultLocation() {
        SQLiteDatabase db = this.getWritableDatabase();
        Location loc = null;

        if (!LocationExistByName("Default")){
            ContentValues values = new ContentValues();
            values.put(LOCATION_NAME, "Default");
            values.put(COST, 0.25);

            // insert row
            long location_id = db.insert(TABLE_LOCATION, null, values);
            loc = getLocationById(location_id);

        }else{
            loc = getLocationByName("Default");
        }
        return loc;
    }

    //Getting a Location
    public Location getLocationById(long key_id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LOCATION +
                " WHERE " + KEY_ID + " = " + key_id;
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null)
            c.moveToFirst();

        Location loc = new Location();
        loc.setLocation_id(c.getInt((c.getColumnIndex(KEY_ID))));
        loc.setLocation_name(c.getString(c.getColumnIndex(LOCATION_NAME)));
        loc.setCost(c.getDouble(c.getColumnIndex(COST)));
        //c.close();
        return loc;

    }
    public Location getLocationByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LOCATION +
                " WHERE " + LOCATION_NAME + " = \"" + name + "\"";
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null)
            c.moveToFirst();

        Location loc = new Location();
        loc.setLocation_id(c.getInt((c.getColumnIndex(KEY_ID))));
        loc.setLocation_name(c.getString(c.getColumnIndex(LOCATION_NAME)));
        loc.setCost(c.getDouble(c.getColumnIndex(COST)));
        //c.close();
        return loc;

    }

    public ArrayList<Location> getAllLocations() {
        ArrayList<Location> locations = new ArrayList<Location>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOCATION;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Location location = new Location();
                location.setLocation_id(c.getInt((c.getColumnIndex(KEY_ID))));
                location.setLocation_name(c.getString(c.getColumnIndex(LOCATION_NAME)));
                location.setCost(c.getDouble(c.getColumnIndex(COST)));

                // adding to location list
                locations.add(location);
            } while (c.moveToNext());
        }
        c.close();
        return locations;
    }

}
