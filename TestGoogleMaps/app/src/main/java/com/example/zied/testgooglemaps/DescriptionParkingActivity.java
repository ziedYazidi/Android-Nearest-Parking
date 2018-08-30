package com.example.zied.testgooglemaps;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DescriptionParkingActivity extends AppCompatActivity {

    TextView parkinNameTextView;
    Button iteneraireButton;
    String[] parkingName;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_parking);
        parkinNameTextView =(TextView)findViewById(R.id.parkinNameTextView);
        iteneraireButton = (Button)findViewById(R.id.iteneraireButton);
        parkingName= getIntent().getStringArrayExtra("parkings");
        position = getIntent().getIntExtra("position",0);
        final String initial = getIntent().getStringExtra("initial");
        final List<String> myList = new ArrayList<String>(Arrays.asList(parkingName));
        parkinNameTextView.setText(myList.get(position));

        iteneraireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr="+initial+"&daddr="+myList.get(position)));
                startActivity(intent);
            }
        });

    }
}
