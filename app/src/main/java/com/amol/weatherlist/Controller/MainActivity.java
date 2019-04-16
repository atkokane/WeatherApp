package com.amol.weatherlist.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.amol.weatherlist.R;
import com.amol.weatherlist.model.Data;
import com.amol.weatherlist.model.DataCache;
import com.amol.weatherlist.model.LoadWeatherInfo;

public class MainActivity extends AppCompatActivity implements IDataProvider{

    String[] CITIES = new String[]{"Tokyo", "London", "Moscow",
            "Ottawa", "Madrid", "Lisboa", "Zurich"};

    LoadWeatherInfo loadWeatherInfo;

    CitiesAdapter mDataAdapter;
    ListView mCitiesListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCitiesListView = findViewById(R.id.list_of_cities);
        mDataAdapter = new CitiesAdapter(this);

        DataCache dataCache = DataCache.getInstance();
        if (dataCache.getmDataCache() == null) {
            loadWeatherInfo = new LoadWeatherInfo(this);
            loadWeatherInfo.execute(CITIES);
        } else {
            mDataAdapter.add(dataCache.getmDataCache());
        }
        mCitiesListView.setAdapter(mDataAdapter);
    }

    @Override
    public void provideData(Data[] data) {
        Log.d("Amol", "Downloaded");
        if (mDataAdapter != null) {
            mDataAdapter.add(data);
        }
    }
}
