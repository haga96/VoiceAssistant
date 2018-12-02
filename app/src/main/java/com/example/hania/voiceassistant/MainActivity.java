package com.example.hania.voiceassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hania.voiceassistant.dao.Database;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static Database mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDb = new Database(this);
        mDb.open();
    }

}
