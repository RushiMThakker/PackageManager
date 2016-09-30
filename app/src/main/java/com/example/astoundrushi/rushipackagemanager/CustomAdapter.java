package com.example.astoundrushi.rushipackagemanager;

import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Astound Rushi on 9/30/2016.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{
    ArrayList<String> packagesList = new ArrayList<>();
    Drawable[] icons=new Drawable[20];
    ArrayList<String> apps = new ArrayList<>();

    public CustomAdapter(ArrayList packages, Drawable[] icon_path, ArrayList apps)
    {
        this.packagesList = packages;
        this.icons = icon_path;
        this.apps = apps;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgIcon;
        TextView txtApp;
        TextView txtPackage;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgPackageIcon);
            txtApp = (TextView) itemView.findViewById(R.id.txtAppName);
            txtPackage = (TextView) itemView.findViewById(R.id.txtPackageName);
        }
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_row, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.imgIcon.setImageDrawable(icons[position]);
        holder.txtApp.setText(apps.get(position));
        holder.txtPackage.setText(packagesList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return packagesList.size();
    }
}
