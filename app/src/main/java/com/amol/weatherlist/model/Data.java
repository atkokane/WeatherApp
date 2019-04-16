package com.amol.weatherlist.model;

import java.util.List;

public class Data {
    private final static String ICON_LOCATION = "http://openweathermap.org/img/w/";

    static class Weather{
        String description;
        String icon;
    }

    static class Main{
        float temp;
    }

    private String name;
    private long id;

    private List<Weather> weather;
    private Main main;

    public String getIconAddress() {
        if (weather != null)
            return ICON_LOCATION + weather.get(0).icon + ".png";
        return "";
    }

    public String getDescription() {
        if (weather != null)
            return weather.get(0).description;
        return "";
    }

    public long getID() {
        return id;
    }

    public float getTemp() {
        if (main != null) {
            return main.temp;
        }
        return 0l;
    }

    public String getName() {
        return name;
    }
}
