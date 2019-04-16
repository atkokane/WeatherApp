package com.amol.weatherlist.Controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amol.weatherlist.R;
import com.amol.weatherlist.model.Data;
import com.squareup.picasso.Picasso;

class CitiesAdapter extends BaseAdapter {

    Context mContext;
    Data[] mCitiesData;

    public CitiesAdapter(Context context) {
        mContext = context;
        mCitiesData = new Data[0];
    }

    static class ViewHolder{
        ImageView mCityIcon;
        TextView mCityName;
        TextView mCityDescreption;
        TextView mCityTemp;
    }

    @Override
    public int getCount() {
        return mCitiesData.length;
    }

    @Override
    public Data getItem(int position) {
        return mCitiesData[position];
    }

    @Override
    public long getItemId(int position) {
        return mCitiesData[position].getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, false);
            viewHolder = new ViewHolder();
            viewHolder.mCityDescreption = convertView.findViewById(R.id.city_desc);
            viewHolder.mCityIcon = convertView.findViewById(R.id.city_icon);
            viewHolder.mCityName = convertView.findViewById(R.id.city_name);
            viewHolder.mCityTemp = convertView.findViewById(R.id.city_temp);
            convertView.setTag(viewHolder);
        } else  {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mCityTemp.setText(Float.toString(mCitiesData[position].getTemp()));
        viewHolder.mCityDescreption.setText(mCitiesData[position].getDescription());
        viewHolder.mCityName.setText(mCitiesData[position].getName());
        String path = mCitiesData[position].getIconAddress();
        if (path != null && !path.isEmpty())
            Picasso.get().load(path).into(viewHolder.mCityIcon);

        return convertView;
    }

    public void add(Data[] citiesData) {
        mCitiesData = citiesData;
        notifyDataSetChanged();
        Log.d("Amol", "added");
    }
}
