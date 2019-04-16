package com.amol.weatherlist.model;

import android.os.AsyncTask;

import com.amol.weatherlist.Controller.IDataProvider;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadWeatherInfo extends AsyncTask<String, Integer, Data[]> {

    private final static String BASE_ADDR = "http://api.openweathermap.org/data/2.5/weather";
    private final static String API_KEY = "17fca6383cdcb57131ef142dd44c7b05";

    IDataProvider mDataProvider;

    public LoadWeatherInfo(IDataProvider dataProvider) {
        mDataProvider = dataProvider;
    }

    @Override
    protected Data[] doInBackground(String... cityName) {
        Data[] allCitiesData = new Data[cityName.length];
        int i = 0;
        for (String city :cityName) {
            try {
                URL url = new URL(BASE_ADDR+"?q="+city+"&APPID="+API_KEY);
                // http://api.openweathermap.org/data/2.5/weather?q=Tokyo&APPID=17fca6383cdcb57131ef142dd44c7b05
                Reader reader = new InputStreamReader(url.openStream());
                Data data = new Gson().fromJson(reader, Data.class);
                allCitiesData[i++] = data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return allCitiesData;
    }

    @Override
    protected void onPostExecute(Data[] data) {
        super.onPostExecute(data);
        mDataProvider.provideData(data);
        DataCache.getInstance().setmDataCache(data);
    }
}
