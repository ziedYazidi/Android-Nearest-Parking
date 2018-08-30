package com.opendata.myparking.parkingapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.opendata.myparking.parkingapp.R;
import com.opendata.myparking.parkingapp.database.DBOpenHelper;
import com.opendata.myparking.parkingapp.model.User;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputUsername;
    private EditText inputPassword;
    private EditText inputConfirmPassword;
    private Button btnSubmit;
    //private DBOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputUsername = (EditText) findViewById(R.id.editTextUserName);
        inputPassword = (EditText) findViewById(R.id.editTextPassword);
        inputConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        btnSubmit = (Button) findViewById(R.id.buttonCreateAccount);


            btnSubmit.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    DBOpenHelper db = new DBOpenHelper(getApplicationContext());
                    String password1 = inputPassword.getText().toString().toLowerCase();
                    String password2 = inputConfirmPassword.getText().toString().toLowerCase();

                    String uName = inputUsername.getText().toString().toLowerCase();
                    Log.d("Password:: ", "Pw1= " + password1 + " Pw2= " + password2 + " uName=" + uName);

                    ArrayList<User> userList = db.getAllUser();
                    if (userList != null && userList.size() > 1){
                        dialogBox("Only one account per device is allowed. Do you want to remove your existing account?");

                    }else{
                        if (!db.isUserExist(uName)){
                            if (password1.equals(password2)){
                                User usr = new User("John", "Smith",25.0,"ABCx1234",uName,password1);
                                long userId = db.createUser(usr);
                                onBackPressed();

                            }else{
                                Log.d("Error::"," Passwords does not match");
                                dialogBox("Passwords does not match!");
                            }
                        }else{
                            Log.d("Error::"," Username exist!");
                            dialogBox("Username exist!");
                        }
                    }
                    db.closeDB();
                }
            });

    }
    public void dialogBox(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //DBOpenHelper db = new DBOpenHelper(getApplicationContext());
                        Log.d("Reset"," A delete feature here!");
                    }
                });

        alertDialogBuilder.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
