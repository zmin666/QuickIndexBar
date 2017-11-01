package com.zmin.quickindexbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuickIndexBar quickBar;
    private List<Model> data;
    private LinearLayoutManager linearLayoutManager;
    private int measuredHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        quickBar = (QuickIndexBar) findViewById(R.id.quick_bar);
    }

    private void initData() {
        data = DataUtil.getData();
        SimpleAdater simpleAdater = new SimpleAdater(this,data );
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(simpleAdater);
        measuredHeight = recyclerView.getMeasuredHeight();
        quickBar.setOnLetterUpdateListener(new QuickIndexBar.OnLetterUpdateListener() {
            @Override
            public void onLetterUpdate(String letter) {
                Log.i("zmin......letter.......","...." + letter );
                int position = getPosition(letter);
                Log.i("zmin.............","...." +  position);
                linearLayoutManager.scrollToPositionWithOffset(position,measuredHeight);
                //  recyclerView.scrollToPositionWithOffset(position);
            }
        });
    }

    private int getPosition(String letter){
        int position = 0;
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).sticky.equals(letter)){
                position = i;
                break;
            }
        }
        return  position;
    }
}
