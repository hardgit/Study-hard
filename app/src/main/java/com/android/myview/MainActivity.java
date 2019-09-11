package com.android.myview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView text = findViewById(R.id.btn_text);
//        text.setTextColor(getResources().getColor(R.color.colorAccent));
        init();
    }

    public void init(){

    }
}
