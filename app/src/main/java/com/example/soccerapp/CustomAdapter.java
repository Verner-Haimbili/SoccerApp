package com.example.soccerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Player> {

    private Context mContext;
    private int mResource;

    public CustomAdapter(Context context, int resource, List<Player> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            listItemView = inflater.inflate(mResource, parent, false);
        }

        TextView tvId = listItemView.findViewById(R.id.tvId);
        TextView tvName = listItemView.findViewById(R.id.tvName);
        TextView tvClub = listItemView.findViewById(R.id.tvClub);
        TextView tvAge = listItemView.findViewById(R.id.tvAge);
        TextView tvDob = listItemView.findViewById(R.id.tvDob);
        TextView tvExperience = listItemView.findViewById(R.id.tvExperience);

        Player player = getItem(position);

        // Set data to TextViews
        tvId.setText(player.getId());
        tvName.setText(player.getName());
        tvClub.setText(player.getClub());
        tvAge.setText(String.valueOf(player.getAge()));
        tvDob.setText(player.getDob());
        tvExperience.setText(player.getExperience());

        return listItemView;
    }
}
