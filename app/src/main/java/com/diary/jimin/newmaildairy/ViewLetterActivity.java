package com.diary.jimin.newmaildairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

    private ArrayList<String> dataSet;
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

    }



    private void init(){


        mRecyclerView = (RecyclerView) findViewById(R.id.view_letter_recycler);
        mLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        dataSet = new ArrayList<>();

        mAdapter=new ViewLetterRecyclerAdapter(dataSet);
        mRecyclerView.setAdapter(mAdapter);

        //db로 읽어옴 지금은 일기 읽어오는디 편지 읽어오게 수정해야함!
        db = FirebaseFirestore.getInstance();

        db.collection(userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int i = 0;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String oldstring = new String("" + document.get("date"));
                                Date date = null;
                                try {
                                    date = new SimpleDateFormat("yyyyMMdd").parse(oldstring);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                String newstring = new SimpleDateFormat("yyyy년 MM월 dd일의 편지").format(date);
                                dataSet.add(i, newstring);
                                mAdapter.notifyItemInserted(i);
                                i++;
                            }
                        }
                    }
                });


    }


}



