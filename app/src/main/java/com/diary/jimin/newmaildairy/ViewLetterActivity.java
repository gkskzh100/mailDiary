package com.diary.jimin.newmaildairy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ViewLetterActivity extends AppCompatActivity {

    private ArrayList<String> dataSet;
    private RecyclerView mRecyclerView;
    private ViewLetterRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_letter);

        init();
    }

    private void init(){


        mRecyclerView = (RecyclerView) findViewById(R.id.view_letter_recycler);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        dataSet = new ArrayList<>();

        dataSet.add("2019년 10월 12일");
        dataSet.add("2019년 10월 22일");
        dataSet.add("2019년 11월 18일");
        dataSet.add("2019년 11월 19일");

        mAdapter=new ViewLetterRecyclerAdapter(dataSet);
        mRecyclerView.setAdapter(mAdapter);

    }
}
