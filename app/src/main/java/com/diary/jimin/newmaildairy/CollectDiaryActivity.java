package com.diary.jimin.newmaildairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
    }

    private void init () {
        recyclerView = findViewById(R.id.collect_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mItemList = new ArrayList<>();

        mAdapter = new RecycleAdapter(mItemList);
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
//                                    Log.d("dbSuccess", ""+mAdapter.getItemCount());
                                    mAdapter.notifyItemInserted(i);
                                }
                                i++;
                            }
                        }
                    }
                });






    }
}
