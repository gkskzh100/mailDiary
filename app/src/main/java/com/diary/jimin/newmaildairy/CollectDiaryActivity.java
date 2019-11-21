package com.diary.jimin.newmaildairy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class CollectDiaryActivity extends AppCompatActivity {


    private ArrayList<ItemDictionary> mItemList;
    private RecycleAdapter mAdapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_diary);

        init();
    }

    private void init () {
        recyclerView = findViewById(R.id.collect_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mItemList = new ArrayList<>();

        mAdapter = new RecycleAdapter(mItemList);
        recyclerView.setAdapter(mAdapter);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);



    }
}
