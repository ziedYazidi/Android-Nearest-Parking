package com.example.zied.testgooglemaps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    TextView user_profile_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);;
        user_profile_name =  (TextView)v.findViewById(R.id.user_profile_name);
        String nom = getActivity().getIntent().getStringExtra("nom");
        String prenom = getActivity().getIntent().getStringExtra("prenom");
        user_profile_name.setText(nom + " " + prenom);
        return v;
    }

}
