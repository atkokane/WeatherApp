package com.amol.weatherlist.model;

public class DataCache {
    private DataCache() {}

    private static DataCache mInstance;

    private Data[] mDataCache;

    public static DataCache getInstance() {
        if (mInstance == null)
            mInstance = new DataCache();
        return mInstance;
    }

    public void setmDataCache(Data[] dataArray) {
        mDataCache = dataArray;
    }

    public Data[] getmDataCache() {
        return mDataCache;
    }

}
