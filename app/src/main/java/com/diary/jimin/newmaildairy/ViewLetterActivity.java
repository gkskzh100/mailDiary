package com.diary.jimin.newmaildairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;





public class ViewLetterActivity extends AppCompatActivity {

    private ArrayList<String> dataSet;
    private RecyclerView mRecyclerView;
    private ViewLetterRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_letter);

        init();
    }


    private void init(){


        mRecyclerView = (RecyclerView) findViewById(R.id.view_letter_recycler);
        mLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        db = FirebaseFirestore.getInstance();

        db.collection("letters")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                               int i = 0;
                                               if (task.isSuccessful()) {
                                                   for (QueryDocumentSnapshot document : task.getResult()) {
                                                       dataSet = new ArrayList<>();
                                                       dataSet.add("2019년 10월 12일의 편지"); //이거 year+년+month+월+day+일의 편지로 나오게 바꿔야하는데 그러면 날짜를 다 받아야겠지...?.. 공부해야함 ㄷㄷ
                                                       //그리고 누르면 해당하는 날짜의 편지로 가게 해야함~ 근데 중요한건 편지보기가 없삼 멍미
                                                   }
                                               }
                                           }
                                       });

        dataSet = new ArrayList<>();

        dataSet.add("2019년 10월 12일의 편지");
        dataSet.add("2019년 10월 22일의 편지");
        dataSet.add("2019년 11월 18일의 편지");
        dataSet.add("2019년 11월 19일의 편지");

        mAdapter=new ViewLetterRecyclerAdapter(dataSet);
        mRecyclerView.setAdapter(mAdapter);

    }
}
