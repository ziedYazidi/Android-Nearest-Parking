package com.example.zied.testgooglemaps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.transform.sax.SAXSource;

import static android.R.attr.countDown;
import static android.R.attr.onClick;
import static android.R.attr.syncable;

public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nomEditText,prenomEditText,emailEditText,passwordEditText;
    Button inscriptionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        nomEditText = (EditText)findViewById(R.id.nomEditText);
        prenomEditText = (EditText)findViewById(R.id.prenomEditText);
        emailEditText = (EditText)findViewById(R.id.emailEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        inscriptionButton = (Button)findViewById(R.id.inscriptionButton);
        inscriptionButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.inscriptionButton)
        {
            UserAdd userAdd = new UserAdd(this,nomEditText.getText().toString(),prenomEditText.getText().toString());
            userAdd.execute(nomEditText.getText().toString(),prenomEditText.getText().toString(),emailEditText.getText().toString(),passwordEditText.getText().toString());
            //Intent insciToAuth = new Intent(this,AuthentificationActivity.class);
            //startActivity(insciToAuth);


        }

    }
    public class UserAdd extends AsyncTask<String, Void, String> {

        Context context;
        String nom,prenom;
        private final String LOG_TAG = UserAdd.class.getSimpleName();

        private UserAdd(Context context,String nom,String prenom) {
            this.context = context.getApplicationContext();
            this.prenom=prenom;
            this.nom=nom;
        }
        @Override
        protected String doInBackground(String... params) {

            // If there's no zip code, there's nothing to look up.  Verify size of params.
            if (params.length == 0) {
                return null;
            }

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                final String FORECAST_BASE_URL =
                        "http://192.168.43.18:8181/createUser?";
                final String NOM_PARAM = "nom";
                final String PRENOM_PARAM = "prenom";
                final String ISRESPONSIBLE_PARAM = "isResponsible";
                final String EMAIl_PARAM = "email";
                final String MDP_PARAM = "hashedMdp";
                Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                        .appendQueryParameter(NOM_PARAM, params[0])
                        .appendQueryParameter(PRENOM_PARAM, params[1])
                        .appendQueryParameter(EMAIl_PARAM, params[2])
                        .appendQueryParameter(ISRESPONSIBLE_PARAM, "0")
                        .appendQueryParameter(MDP_PARAM, params[3])
                        .build();

                URL url = new URL(builtUri.toString());

                Log.v(LOG_TAG, "Built URI " + builtUri.toString());

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
                System.out.println(forecastJsonStr);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }

            }
            return forecastJsonStr;
        }

       @Override
        protected void onPostExecute(String s) {
            if(s.trim().equals("1")== true){
                Toast.makeText(getApplicationContext(),"registration Successful : Welcome "+ nom + " " + prenom,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,MainActivity.class);
                intent.putExtra("nom",nom);
                intent.putExtra("prenom",prenom);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"FAILURE",Toast.LENGTH_LONG).show();
            }

        }
    }


}
