package com.opendata.myparking.parkingapp.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.opendata.myparking.parkingapp.R;
import com.opendata.myparking.parkingapp.database.DBOpenHelper;
import com.opendata.myparking.parkingapp.model.User;

public class LoginActivity extends AppCompatActivity {

    private Button buttonRegistration;
    private Button buttonLogin;
    private EditText inputUsername;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonRegistration = (Button) findViewById(R.id.buttonRegistration);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, 0);
                finish();
            }
        });

        final Context context = this;
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBOpenHelper db = new DBOpenHelper(getApplicationContext());
                inputUsername = (EditText) findViewById(R.id.enterUserName);
                inputPassword = (EditText) findViewById(R.id.enterPassword);
                String password = inputPassword.getText().toString().toLowerCase();
                String uName = inputUsername.getText().toString().toLowerCase();

                if (db.matchUsernameAndPassword(uName,password)){
                    Log.d("::::","logOutAllUsers");
                    User curUser = db.getUserByUsernameAndPassword(uName,password);
                    Log.d("::::","updateLoginStatus");

                    //Login in successful
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    //startActivity(intent);
                    startActivityForResult(intent, 0);
                    finish();

                }else{
                    Log.d("Error::", " Username or password incorrect!");
                    dialogBox("Username or password incorrect!");

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
                    }
                });
        /*
        *        alertDialogBuilder.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
        * */
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}