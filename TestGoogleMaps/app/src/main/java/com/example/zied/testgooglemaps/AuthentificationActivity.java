package com.example.zied.testgooglemaps;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.entity.mime.Header;

public class AuthentificationActivity extends AppCompatActivity implements View.OnClickListener {


    public EditText emailEditText;
    public EditText passwordEditText;
    public Button connexionButton;
    public TextView inscriTextView;
    private final String LOG_TAG = UserAuth.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        emailEditText=(EditText)findViewById(R.id.emailEditText);
        passwordEditText=(EditText)findViewById(R.id.passwordEditText);
        connexionButton=(Button)findViewById(R.id.connexionButton);
        inscriTextView = (TextView)findViewById(R.id.inscriTextView);

        inscriTextView.setOnClickListener(this);
        connexionButton.setOnClickListener(this);


    }

    public void onClick(View v)
    {
        if(v.getId()==R.id.connexionButton)
        {
            UserAuth userAuth = new UserAuth(this);
            userAuth.execute(emailEditText.getText().toString(),passwordEditText.getText().toString());
            //Intent AuthToMain = new Intent(this,MainActivity.class);
            //startActivity(AuthToMain);
        }
        if(v.getId() == R.id.inscriTextView)
        {
            Intent authToInscri = new Intent(this,InscriptionActivity.class);
            startActivity(authToInscri);
        }
    }





    public class UserAuth extends AsyncTask<String,Void,ArrayList> {
        ArrayList infosPersonne = null;
        Context context;

        private UserAuth(Context context)
        {
            this.context = context.getApplicationContext();
        }
        @Override
        protected ArrayList doInBackground(String... params) {
            // If there's no zip code, there's nothing to look up.  Verify size of params.
            if (params.length == 0) {
                return null;
            }

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            infosPersonne=new ArrayList();
            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                final String FORECAST_BASE_URL =
                        "http://192.168.43.18:8181/connexion?";
                final String EMAIl_PARAM = "email";
                final String MDP_PARAM = "mdp";

                Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                        .appendQueryParameter(EMAIl_PARAM,params[0])
                        .appendQueryParameter(MDP_PARAM,params[1])
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
                return null;
            }
            finally{
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
            try {
                JSONObject jsonObject = new JSONObject(forecastJsonStr);
                String nom = jsonObject.getString("nom");
                String prenom = jsonObject.getString("prenom");

                infosPersonne.add(nom);
                infosPersonne.add(prenom);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            return infosPersonne;
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);
            String nom = arrayList.get(0).toString();
            String prenom = arrayList.get(1).toString();
            Toast.makeText(getApplicationContext(),nom + " " + prenom+" connected",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context,MainActivity.class);
            intent.putExtra("nom",nom);
            intent.putExtra("prenom",prenom);
            startActivity(intent);

        }
    }

}
