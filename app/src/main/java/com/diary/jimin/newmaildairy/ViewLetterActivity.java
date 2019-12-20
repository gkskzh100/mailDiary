package com.diary.jimin.newmaildairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;





public class ViewLetterActivity extends AppCompatActivity {

    private ArrayList<ItemDictionary> dataSet;
    private RecyclerView mRecyclerView;
    private ViewLetterRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FirebaseFirestore db;
    private FirebaseUser firebaseUser;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_letter);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            userId = firebaseUser.getUid();
        }



        init();

        mAdapter.setOnItemClickListener(new ViewLetterRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
            }
        });
    }



    private void init(){


        mRecyclerView = (RecyclerView) findViewById(R.id.view_letter_recycler);
        mLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        dataSet = new ArrayList<>();

        mAdapter=new ViewLetterRecyclerAdapter(getApplicationContext(),dataSet);
        mRecyclerView.setAdapter(mAdapter);
        db = FirebaseFirestore.getInstance();

        db.collection(userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int i = 0;
                        Log.d(this.getClass().getName(),"DB1");
                        if (task.isSuccessful()) {
                            Log.d(this.getClass().getName(),"DB2");
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String oldstring = "" + document.get("date");
                                Date date = null;
                                try {
                                    date = new SimpleDateFormat("yyyyMMdd").parse(oldstring);
                                    String newstring = new SimpleDateFormat("yyyy년 MM월 dd일의 편지").format(date);
                                    ItemDictionary itemDictionary = new ItemDictionary(oldstring,newstring);
                                    dataSet.add(i,itemDictionary);
                                    mAdapter.notifyItemInserted(i);
                                    i++;
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Log.d(this.getClass().getName(),"DB5");
                            }
                        }
                    }
                });


    }


}



