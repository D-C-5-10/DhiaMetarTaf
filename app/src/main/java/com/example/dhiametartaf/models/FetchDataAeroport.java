package com.example.dhiametartaf.models;

import android.os.AsyncTask;
import android.view.View;

import com.example.dhiametartaf.fragments.SearchFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class FetchDataAeroport extends AsyncTask<Void, Void, Void> {


    private String codeSearch;
    String result1 = null;
    String result2 = null;
    String result3 = null;
    String result4 = null;

    public static double latitude1;
    public static double longitude1;
    public static double latitude2;
    public static double longitude2;
    public static double latitude3;
    public static double longitude3;
    public static double latitude4;
    public static double longitude4;
    public static String name1;
    public static String name2;
    public static String name3;
    public static String name4;

    private String retrieveData(String search) {
        String data = "";
        try {
            URL url = new URL("https://applications.icao.int/dataservices/api/indicators-list?api_key=009ec761-3855-4607-a79f-2a7b5d738961" +
                    "&state=&airports=" + search + "&format=json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return data;
    }


    private String searchAeroport(String data) {
        String result = "";
        String objects[] = null;
//tout récupérer à l'interieur
        objects = data.split("\\{");
        //recuperer les champs
        String object[] = objects[1].split("\",");
        //prendre les 4 premiers
        for (int i = 0; i <= 3; i++)
            result = result + object[i] + "\n";
//supprimer le "
        return result.replaceAll("\"", "");
    }

    private String name(String data) {
        String result = "";
        String objects[] = null;
        objects = data.split("\\{");
        String object[] = objects[1].split("\",");
        result = objects[1].split("\",")[2].split(":")[1].split(",")[0];
        return result.replaceAll("\"", "");
    }

    private String Latitude(String data) {
        String result = "";
        String objects[] = null;
        objects = data.split("\\{");
        String object[] = objects[1].split("\",");
        String latitudeS = objects[1].split("\",")[4].split(":")[1];
        return objects[1].split("\",")[4].split(":")[1].split(",")[0];
    }

    private String longitude(String data) {
        String result = "";
        String objects[] = null;
        objects = data.split("\\{");
        String object[] = objects[1].split("\",");
        return objects[1].split("\",")[4].split(",")[1].split(":")[1];
    }


    @Override
    protected Void doInBackground(Void... voids) {
        this.codeSearch.split(";");
        switch (this.codeSearch.split(";").length) {
            case 1:
                result1 = searchAeroport(retrieveData(this.codeSearch.split(";")[0]));
                latitude1 = Double.parseDouble(Latitude(retrieveData(this.codeSearch.split(";")[0])));
                longitude1 = Double.parseDouble(longitude(retrieveData(this.codeSearch.split(";")[0])));
                name1 = name(retrieveData(this.codeSearch.split(";")[0]));
                break;
            case 2:
                result1 = searchAeroport(retrieveData(this.codeSearch.split(";")[0]));
                latitude1 = Double.parseDouble(Latitude(retrieveData(this.codeSearch.split(";")[0])));
                longitude1 = Double.parseDouble(longitude(retrieveData(this.codeSearch.split(";")[0])));
                name1 = name(retrieveData(this.codeSearch.split(";")[0]));
                result2 = searchAeroport(retrieveData(this.codeSearch.split(";")[1]));
                latitude2 = Double.parseDouble(Latitude(retrieveData(this.codeSearch.split(";")[1])));
                longitude2 = Double.parseDouble(longitude(retrieveData(this.codeSearch.split(";")[1])));
                name2 = name(retrieveData(this.codeSearch.split(";")[1]));
                break;
            case 3:
                result1 = searchAeroport(retrieveData(this.codeSearch.split(";")[0]));
                latitude1 = Double.parseDouble(Latitude(retrieveData(this.codeSearch.split(";")[0])));
                longitude1 = Double.parseDouble(longitude(retrieveData(this.codeSearch.split(";")[0])));
                name1 = name(retrieveData(this.codeSearch.split(";")[0]));
                result2 = searchAeroport(retrieveData(this.codeSearch.split(";")[1]));
                latitude2 = Double.parseDouble(Latitude(retrieveData(this.codeSearch.split(";")[1])));
                longitude2 = Double.parseDouble(longitude(retrieveData(this.codeSearch.split(";")[1])));
                name2 = name(retrieveData(this.codeSearch.split(";")[1]));
                result3 = searchAeroport(retrieveData(this.codeSearch.split(";")[2]));
                latitude3 = Double.parseDouble(Latitude(retrieveData(this.codeSearch.split(";")[2])));
                longitude3 = Double.parseDouble(longitude(retrieveData(this.codeSearch.split(";")[2])));
                name3 = name(retrieveData(this.codeSearch.split(";")[2]));
                break;
            case 4:
                result1 = searchAeroport(retrieveData(this.codeSearch.split(";")[0]));
                latitude1 = Double.parseDouble(Latitude(retrieveData(this.codeSearch.split(";")[0])));
                longitude1 = Double.parseDouble(longitude(retrieveData(this.codeSearch.split(";")[0])));
                name1 = name(retrieveData(this.codeSearch.split(";")[0]));
                result2 = searchAeroport(retrieveData(this.codeSearch.split(";")[1]));
                latitude2 = Double.parseDouble(Latitude(retrieveData(this.codeSearch.split(";")[1])));
                longitude2 = Double.parseDouble(longitude(retrieveData(this.codeSearch.split(";")[1])));
                name2 = name(retrieveData(this.codeSearch.split(";")[1]));
                result3 = searchAeroport(retrieveData(this.codeSearch.split(";")[2]));
                latitude3 = Double.parseDouble(Latitude(retrieveData(this.codeSearch.split(";")[2])));
                longitude3 = Double.parseDouble(longitude(retrieveData(this.codeSearch.split(";")[2])));
                name3 = name(retrieveData(this.codeSearch.split(";")[2]));
                result4 = searchAeroport(retrieveData(this.codeSearch.split(";")[3]));
                latitude4 = Double.parseDouble(Latitude(retrieveData(this.codeSearch.split(";")[3])));
                longitude4 = Double.parseDouble(longitude(retrieveData(this.codeSearch.split(";")[3])));
                name4 = name(retrieveData(this.codeSearch.split(";")[3]));
                break;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (result1 != null) {
            SearchFragment.textView1.setText(result1);
            SearchFragment.textView1.setText(SearchFragment.textView1.getText().toString().replace("countryName","•Pays "));
            SearchFragment.textView1.setText(SearchFragment.textView1.getText().toString().replace("countryCode","•Code pays "));
            SearchFragment.textView1.setText(SearchFragment.textView1.getText().toString().replace("airportName","•Airport "));
            SearchFragment.textView1.setText(SearchFragment.textView1.getText().toString().replace("cityName","•Ville "));
            SearchFragment.textView1.setVisibility(View.VISIBLE);
            SearchFragment.logo.setVisibility(View.INVISIBLE);
            SearchFragment.welcome.setVisibility(View.INVISIBLE);
        } else
            SearchFragment.textView1.setText("");
        if (result2 != null) {

            SearchFragment.textView2.setText(result2);
            SearchFragment.textView2.setText(SearchFragment.textView2.getText().toString().replace("countryName","•Pays "));
            SearchFragment.textView2.setText(SearchFragment.textView2.getText().toString().replace("countryCode","•Code pays "));
            SearchFragment.textView2.setText(SearchFragment.textView2.getText().toString().replace("airportName","•Airport "));
            SearchFragment.textView2.setText(SearchFragment.textView2.getText().toString().replace("cityName","•Ville "));
            SearchFragment.textView1.setVisibility(View.VISIBLE);
            SearchFragment.textView2.setVisibility(View.VISIBLE);
            SearchFragment.logo.setVisibility(View.INVISIBLE);
            SearchFragment.welcome.setVisibility(View.INVISIBLE);
        } else
            SearchFragment.textView2.setText("");
        if (result3 != null) {
            SearchFragment.textView3.setText(result3);
            SearchFragment.textView3.setText(SearchFragment.textView3.getText().toString().replace("countryName","•Pays "));
            SearchFragment.textView3.setText(SearchFragment.textView3.getText().toString().replace("countryCode","•Code pays "));
            SearchFragment.textView3.setText(SearchFragment.textView3.getText().toString().replace("airportName","•Airport "));
            SearchFragment.textView3.setText(SearchFragment.textView3.getText().toString().replace("cityName","•Ville "));
            SearchFragment.textView1.setVisibility(View.VISIBLE);
            SearchFragment.textView2.setVisibility(View.VISIBLE);
            SearchFragment.textView3.setVisibility(View.VISIBLE);
            SearchFragment.logo.setVisibility(View.INVISIBLE);
            SearchFragment.welcome.setVisibility(View.INVISIBLE);
        } else
            SearchFragment.textView3.setText("");
        if (result4 != null) {
            SearchFragment.textView4.setText(result4);
            SearchFragment.textView4.setText(SearchFragment.textView4.getText().toString().replace("countryName","•Pays "));
            SearchFragment.textView4.setText(SearchFragment.textView4.getText().toString().replace("countryCode","•Code pays "));
            SearchFragment.textView4.setText(SearchFragment.textView4.getText().toString().replace("airportName","•Airport "));
            SearchFragment.textView4.setText(SearchFragment.textView4.getText().toString().replace("cityName","•Ville "));
            SearchFragment.textView1.setVisibility(View.VISIBLE);
            SearchFragment.textView2.setVisibility(View.VISIBLE);
            SearchFragment.textView3.setVisibility(View.VISIBLE);
            SearchFragment.textView4.setVisibility(View.VISIBLE);
            SearchFragment.logo.setVisibility(View.INVISIBLE);
            SearchFragment.welcome.setVisibility(View.INVISIBLE);
        } else
            SearchFragment.textView4.setText("");

    }

    public void setCodeSearch(String codeSearch) {
        this.codeSearch = codeSearch;
    }

    public String getCodeSearch() {
        return codeSearch;
    }

}