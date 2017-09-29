package com.yekong.mystatuslayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.yekong.mystatuslayout_master.MyStatusFramlayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout contentview;
    private MyStatusFramlayout statuslayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.statuslayout = (MyStatusFramlayout) findViewById(R.id.status_layout);
        this.contentview = (LinearLayout) findViewById(R.id.content_view);
        statuslayout.childLoadSuccess();
    }
}
