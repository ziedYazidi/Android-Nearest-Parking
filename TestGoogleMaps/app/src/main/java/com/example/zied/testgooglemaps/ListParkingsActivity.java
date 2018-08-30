package com.example.zied.testgooglemaps;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListParkingsActivity extends AppCompatActivity {

    ListView parkingsListView;
    ArrayList<String> parkings;
    ArrayList<String> distances;
    ArrayList<String> times;
    ArrayList<String> places;
    float [] ratings ={5,3,2,4,1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_parkings);
        String json = getIntent().getStringExtra("json");
        final String initial = getIntent().getStringExtra("initial");
        Resources res = getResources();
        parkings = parsingNames(json);
        distances = parsingDistances(json);
        times = parsingTimes(json);
        places = parsingPlaces(json);
        parkingsListView = (ListView)findViewById(R.id.parkingsListView);
        MyAdapter adapter = new MyAdapter(this,parkings.toArray(new String[parkings.size()]),distances.toArray(new String[distances.size()]),times.toArray(new String[times.size()]),places.toArray(new String[places.size()]),ratings);
        parkingsListView.setAdapter(adapter);

        parkingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent listToDesc = new Intent(ListParkingsActivity.this,DescriptionParkingActivity.class);
                listToDesc.putExtra("position",position);
                listToDesc.putExtra("initial",initial);
                listToDesc.putExtra("parkings",parkings.toArray(new String[parkings.size()]));
                startActivity(listToDesc);
            }
        });
    }
    public ArrayList<String> parsingNames(String json)
    {   ArrayList<String> result = new ArrayList<>();
        JSONArray jsonArray;
        try
        {
            jsonArray = new JSONArray(json);
            for (int i = 0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject = (JSONObject)jsonArray.getJSONObject(i);
                String nom = jsonObject.getString("nom");
                result.add(nom);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<String> parsingPlaces(String json)
    {   ArrayList<String> result = new ArrayList<>();
        JSONArray jsonArray;
        try
        {
            jsonArray = new JSONArray(json);
            for (int i = 0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject = (JSONObject)jsonArray.getJSONObject(i);
                String place = jsonObject.getString("placeDisponibles");
                result.add(place);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<String> parsingTimes(String json)
    {   ArrayList<String> result = new ArrayList<>();
        JSONArray jsonArray;
        try
        {
            jsonArray = new JSONArray(json);
            for (int i = 0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject = (JSONObject)jsonArray.getJSONObject(i);
                String distance = jsonObject.getString("durationToOrigine");
                result.add(distance);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<String> parsingDistances(String json)
    {   ArrayList<String> result = new ArrayList<>();
        try
        {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject = (JSONObject)jsonArray.getJSONObject(i);
                String distance = jsonObject.getString("distanceToOrigine");
                result.add(distance);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        float [] ratings;
        String parkings[];
        String distances[];
        String times[];
        String places[];

        public MyAdapter(Context context,String parkings[],String distances[],String times[],String places[],float [] ratings) {
            super(context,R.layout.line,R.id.parkingsNameTextView,parkings);
            this.parkings=distances;
            this.distances=parkings;
            this.context=context;
            this.ratings=ratings;
            this.times=times;
            this.places=places;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View line = inflater.inflate(R.layout.line,parent,false);
            RatingBar ratingBar = (RatingBar) line.findViewById(R.id.parkingRatingBar);
            TextView myTitle = (TextView) line.findViewById(R.id.parkingsNameTextView);
            TextView myDesc = (TextView) line.findViewById(R.id.distanceTextView);
            TextView myTime = (TextView)line.findViewById(R.id.timeTextView);
            TextView myPlace = (TextView)line.findViewById(R.id.nbrPlacesTextView);
            ratingBar.setRating(ratings[position]);
            myTitle.setText(parkings[position]);
            myDesc.setText(distances[position]);
            myTime.setText(times[position]);
            myPlace.setText("nbrPlaces : " + places[position]);
            return line;
        }

    }
}
