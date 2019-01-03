package com.startingandroid.sqlitedatabasetutorial.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.startingandroid.sqlitedatabasetutorial.R;

public class AboutUs extends AppCompatActivity
{
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.about_us);
    toolbar=findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("About us");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
