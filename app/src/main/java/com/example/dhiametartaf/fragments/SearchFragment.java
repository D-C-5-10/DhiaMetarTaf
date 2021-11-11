package com.example.dhiametartaf.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhiametartaf.models.FetchDataAeroport;
import com.example.dhiametartaf.models.FetchDataMetarTaf;
import com.example.dhiametartaf.R;


public class SearchFragment extends Fragment {

    public static TextView textView1;
    public static TextView textView2;
    public static TextView textView3;
    public static TextView textView4;
    public static ImageView logo;
    public static TextView welcome;
    public TextView searchBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("METAR TAF BOOKING");

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        textView1 = view.findViewById(R.id.airoport1);
        textView2 = view.findViewById(R.id.airoport2);
        textView3 = view.findViewById(R.id.airoport3);
        textView4 = view.findViewById(R.id.airoport4);
        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);
        searchBar = view.findViewById(R.id.editText);

        Button btnSearch = (Button) view.findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(searchBar.getText().toString().equals("") || searchBar.getText().toString().length()<4 ){
                    Toast.makeText(getActivity(), "Code non valide !",Toast.LENGTH_LONG).show();
                }else{
                    FetchDataAeroport process = new FetchDataAeroport();
                    process.setCodeSearch(searchBar.getText().toString());
                    process.execute();

                }



            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FetchDataAeroport fetchDataAeroport = new FetchDataAeroport();

                FetchDataMetarTaf fragment = new FetchDataMetarTaf();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (!textView1.getText().toString().equals("Result1")) {

                    if (searchBar.getText().toString().contains(";")) {
                        fragment.setSearchFor(searchBar.getText().toString().split(";")[0]);
                        fragment.setLatitude(FetchDataAeroport.latitude1);
                        fragment.setLongitude(FetchDataAeroport.longitude1);
                        fragment.setName(FetchDataAeroport.name1);

                    } else
                        fragment.setSearchFor(searchBar.getText().toString());

                   fragment.setLatitude(FetchDataAeroport.latitude1);
                   fragment.setLongitude(FetchDataAeroport.longitude1);
                   fragment.setName(FetchDataAeroport.name1);
                    //FetchDataMetarTaf.name = FetchDataAeroport.name1;
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null).commit();
                }

            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FetchDataMetarTaf fragment = new FetchDataMetarTaf();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (!textView2.getText().toString().equals("Result2")) {
                    fragment.setSearchFor(searchBar.getText().toString().split(";")[1]);
                    fragment.setLatitude(FetchDataAeroport.latitude2);
                    fragment.setLongitude(FetchDataAeroport.longitude2);
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null).commit();
                }
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchDataMetarTaf fragment = new FetchDataMetarTaf();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (!textView3.getText().toString().equals("Result3")) {
                    fragment.setSearchFor(searchBar.getText().toString().split(";")[2]);
                    fragment.setLatitude(FetchDataAeroport.latitude3);
                    fragment.setLongitude(FetchDataAeroport.longitude3);
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null).commit();
                }

            }
        });

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchDataMetarTaf fragment = new FetchDataMetarTaf();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (!textView4.getText().toString().equals("Result4")) {
                    fragment.setSearchFor(searchBar.getText().toString().split(";")[3]);
                    fragment.setLatitude(FetchDataAeroport.latitude4);
                    fragment.setLongitude(FetchDataAeroport.longitude4);
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null).commit();
                }

            }
        });

        return view;
    }


}