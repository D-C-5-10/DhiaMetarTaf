package com.example.dhiametartaf.models;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dhiametartaf.R;
import com.example.dhiametartaf.fragments.mapFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class FetchDataMetarTaf extends Fragment {

    private String searchFor;
    private double latitude;
    private double longitude;
    public String name;
    String body;

    private Button btnLocation;
    private Button btnMetar;
    private Button btnTaf;
    private Button btnCoded;

    public static TextView airportName;
    private TextView typeResult;
    private TextView finalResult;

    private Fragment actualMapFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_metartaf, container, false);


        mapFragment fragment = new mapFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        fragment.setLatitude(latitude);
        fragment.setLongitude(longitude);
        transaction.replace(R.id.actualMapFragment, fragment);
        transaction.addToBackStack(null).commit();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(name + " - " + searchFor.toUpperCase());
        btnLocation = view.findViewById(R.id.location);
        btnMetar = view.findViewById(R.id.btnMetar);
        btnTaf = view.findViewById(R.id.btnTaf);
        btnCoded = view.findViewById(R.id.btnCoded);

        airportName = view.findViewById(R.id.airportName);
        airportName.setText(name + "\n" + searchFor.toUpperCase());

        finalResult = view.findViewById(R.id.resultBody);
        typeResult = view.findViewById(R.id.typeResult);
        new getMetar().execute();

        finalResult.setMovementMethod(new ScrollingMovementMethod());


        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapFragment fragment = new mapFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                fragment.setLatitude(latitude);
                fragment.setLongitude(longitude);
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null).commit();

            }
        });

        btnMetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMetar.setBackgroundResource(R.drawable.rounded_corner);
                btnMetar.setTextColor(Color.WHITE);

                btnCoded.setBackgroundResource(R.drawable.my_button_bg);
                btnCoded.setTextColor(Color.BLACK);

                btnTaf.setBackgroundResource(R.drawable.my_button_bg);
                btnTaf.setTextColor(Color.BLACK);

                typeResult.setText("METAR");
                new getMetar().execute();
            }
        });

        btnTaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMetar.setBackgroundResource(R.drawable.my_button_bg);
                btnMetar.setTextColor(Color.BLACK);

                btnTaf.setBackgroundResource(R.drawable.rounded_corner);
                btnTaf.setTextColor(Color.WHITE);

                btnCoded.setBackgroundResource(R.drawable.my_button_bg);
                btnCoded.setTextColor(Color.BLACK);
                typeResult.setText("TAF");
                new getTaf().execute();
            }
        });

        btnCoded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMetar.setBackgroundResource(R.drawable.my_button_bg);
                btnMetar.setTextColor(Color.BLACK);

                btnTaf.setBackgroundResource(R.drawable.my_button_bg);
                btnTaf.setTextColor(Color.BLACK);

                btnCoded.setBackgroundResource(R.drawable.rounded_corner);
                btnCoded.setTextColor(Color.WHITE);
                typeResult.setText("");
                new getCoded().execute();
            }
        });
        return view;
    }

    public void setSearchFor(String searchFor) {
        this.searchFor = searchFor;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }





private class getMetar extends AsyncTask<Void, Void, Void> {

    String title;
    String data;

    @Override
    protected Void doInBackground(Void... params) {

        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.aviationweather.gov/metar/data?ids="+searchFor+"&format=decoded&hours=0&taf=on&layou").get();

            Element table = doc.select("table").get(0); //select the first table.
            Elements rows = table.select("tr");
            for (int i = 0; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");
                data += "• " + cols.get(0).text() +cols.get(1).text() + "\n";

            }

            body = data;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        finalResult.setText(body);
        if(finalResult.getText().toString().contains("null")){
            finalResult.setText(finalResult.getText().toString().replace("null",""));
        }
    }
}


    private class getTaf extends AsyncTask<Void, Void, Void> {

        String title;
        String data;

        @Override
        protected Void doInBackground(Void... params) {

            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.aviationweather.gov/metar/data?ids="+searchFor+"&format=decoded&hours=0&taf=on&layou").get();
                Element table = doc.select("table").get(1);
                Elements rows = table.select("tr");
                for (int i = 0; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    data += "• " + cols.get(0).text() +cols.get(1).text() + "\n";

                }
                body = data;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            finalResult.setText(body);
            if(finalResult.getText().toString().contains("null")){
                finalResult.setText(finalResult.getText().toString().replace("null",""));
            }
        }
    }

    private class getCoded extends AsyncTask<Void, Void, Void> {

        String title;
        String data;

        @Override
        protected Void doInBackground(Void... params) {

            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.aviationweather.gov/metar/data?ids="+searchFor+"&format=raw&hours=0&taf=on&layout=on").get();
                Element code = doc.select("code").get(0);
                Element code2 = doc.select("code").get(1);
                body = code.text() + code2.text();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            finalResult.setText(body);

        }
    }
}