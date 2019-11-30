package com.diary.jimin.newmaildairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CollectDiaryActivity extends AppCompatActivity {


    private ArrayList<ItemDictionary> mItemList;
    private RecycleAdapter mAdapter;

    private RecyclerView recyclerView;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_diary);

        init();

        mAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                /** recyclerView Item Click이벤트를 여기서 처리함 **/

//                Intent intent = new Intent(getApplicationContext(),CheckDiaryActivity.class);
//                startActivity(intent);


            }
        });
    }

    private void init () {
        recyclerView = findViewById(R.id.collect_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mItemList = new ArrayList<>();

        mAdapter = new RecycleAdapter(getApplicationContext(),mItemList);
        recyclerView.setAdapter(mAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        db = FirebaseFirestore.getInstance();

        db.collection("diaries")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int i=0;
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.get("emoji").equals("good")) {
                                    ItemDictionary itemDictionary = new ItemDictionary(R.drawable.emo_diary_good,""+document.get("date"),""+document.get("content"));
                                    mItemList.add(i,itemDictionary);
                                    mAdapter.notifyItemInserted(i);
                                } else if(document.get("emoji").equals("angry")) {
                                    ItemDictionary itemDictionary = new ItemDictionary(R.drawable.emo_diary_angry,""+document.get("date"),""+document.get("content"));
                                    mItemList.add(i,itemDictionary);
                                    mAdapter.notifyItemInserted(i);
                                } else if(document.get("emoji").equals("cry")) {
                                    ItemDictionary itemDictionary = new ItemDictionary(R.drawable.emo_diary_cry,""+document.get("date"),""+document.get("content"));
                                    mItemList.add(i,itemDictionary);
                                    mAdapter.notifyItemInserted(i);
                                }  else if(document.get("emoji").equals("happy")) {
                                    ItemDictionary itemDictionary = new ItemDictionary(R.drawable.emo_diary_happy,""+document.get("date"),""+document.get("content"));
                                    mItemList.add(i,itemDictionary);
                                    mAdapter.notifyItemInserted(i);
                                } else if(document.get("emoji").equals("sad")) {
                                    ItemDictionary itemDictionary = new ItemDictionary(R.drawable.emo_diary_sad,""+document.get("date"),""+document.get("content"));
                                    mItemList.add(i,itemDictionary);
                                    mAdapter.notifyItemInserted(i);
                                } else if(document.get("emoji").equals("soso")) {
                                    ItemDictionary itemDictionary = new ItemDictionary(R.drawable.emo_diary_soso,""+document.get("date"),""+document.get("content"));
                                    mItemList.add(i,itemDictionary);
                                    mAdapter.notifyItemInserted(i);
                                }
                                i++;
                            }
                        }
                    }
                });






    }
}
