package com.example.astoundrushi.rushipackagemanager;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    /*ArrayList*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Process process = null;
        String line;
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> applicationInfos;
        try
        {
            process = Runtime.getRuntime().exec("pm list packages");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        try
        {
            while ((line = bufferedReader.readLine()) != null)
            {
                Log.d("<<SHELL OUTPUT>>", line);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        applicationInfos = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo applicationInfo : applicationInfos)
        {
            Log.d("<<APPLICATION ICON>>", (applicationInfo.icon) + "");
            Log.d("<<APPLICATION NAME>>", applicationInfo.name + "");
            Log.d("<<APPLICATION PACKAGE>>", applicationInfo.packageName + "");
        }

        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);

        Drawable[] icons = new Drawable[packs.size()];
        ArrayList<String> packageList=new ArrayList<>(packs.size());
        ArrayList<String> appsList=new ArrayList<>(packs.size());

        for (int i = 0; i < packs.size(); i++)
        {
            PackageInfo p = packs.get(i);
            icons[i] = p.applicationInfo.loadIcon(getPackageManager());
            packageList.add(i,p.applicationInfo.packageName);
            appsList.add(i,p.applicationInfo.loadLabel(getPackageManager()).toString());
        }

        CustomAdapter packageAdapter=new CustomAdapter(packageList,icons,appsList);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerPackageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(packageAdapter);

    }
}
