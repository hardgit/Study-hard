package com.android.myview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.myview.practice.LikeView;
import com.android.myview.practice.MyLikenum;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_run;
    private MyView9 donghua;
    private MyLikenum like_num;
    private LikeView likeview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        findViewById(R.id.btn_run).setOnClickListener(this);
        findViewById(R.id.btn_run2).setOnClickListener(this);
        findViewById(R.id.btn_run3).setOnClickListener(this);
        findViewById(R.id.btn_run4).setOnClickListener(this);
        findViewById(R.id.btn_run5).setOnClickListener(this);
        findViewById(R.id.btn_run6).setOnClickListener(this);
        findViewById(R.id.btn_run7).setOnClickListener(this);
        findViewById(R.id.btn_run8).setOnClickListener(this);
        findViewById(R.id.btn_run9).setOnClickListener(this);
        findViewById(R.id.btn_run10).setOnClickListener(this);
        findViewById(R.id.btn_run11).setOnClickListener(this);
        like_num = findViewById(R.id.mylikenum);
        likeview = findViewById(R.id.likeview);
        like_num.setOnClickListener(this);
        likeview.setOnClickListener(this);
        donghua = findViewById(R.id.donghua);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_run:
                donghua.startAnimator(1);
                break;
            case R.id.btn_run2:
                donghua.startAnimator(2);
                break;
            case R.id.btn_run3:
                donghua.startAnimator(3);
                break;
            case R.id.btn_run4:
                donghua.startAnimator(4);
                break;
            case R.id.btn_run5:
                donghua.startAnimator(5);
                break;
            case R.id.btn_run6:
                donghua.startAnimator(6);
                break;
            case R.id.btn_run7:
                donghua.startAnimator(7);
                break;
            case R.id.btn_run8:
                donghua.startAnimator(8);
                break;
            case R.id.btn_run9:
                donghua.startAnimator(9);
                break;
            case R.id.btn_run10:
                donghua.startAnimator(10);
                break;
            case R.id.btn_run11:
                donghua.startAnimator(11);
                break;
            case R.id.mylikenum://点赞
                like_num.onClick();
                break;
            case R.id.likeview://点赞2
                likeview.openAnimator();
                break;
        }
    }
}
