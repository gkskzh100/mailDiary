package com.diary.jimin.newmaildairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WriteDiary extends AppCompatActivity {
    EditText editDiary;
    Button SaveBtn;
    TextView DatePickTV;
    FrameLayout EmoHappyFL;
    ImageView SelectIV, UnselectIV;
    int i = 0;

    private FirebaseFirestore db;
    private String clickDateStr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        //findViewById
        editDiary = findViewById(R.id.edit_diary);
        SaveBtn = findViewById(R.id.btn_Save);
        DatePickTV = findViewById(R.id.viewDatePick);
        EmoHappyFL = findViewById(R.id.write_diary_happy);
        SelectIV = findViewById(R.id.happy_select_btn);
        UnselectIV = findViewById(R.id.happy_unselect_btn);


        //firebase setting
        db = FirebaseFirestore.getInstance();

        //select 이미지 visiblity
        UnselectIV.setVisibility(View.VISIBLE);
        SelectIV.setVisibility(View.INVISIBLE);

        //select 이미지 전환
        EmoHappyFL.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                i = 1 - i;

                if(i == 0){
                    SelectIV.setVisibility(View.VISIBLE);
                    UnselectIV.setVisibility(View.INVISIBLE);
                }
                else{
                    SelectIV.setVisibility(View.INVISIBLE);
                    UnselectIV.setVisibility(View.VISIBLE);
                }
            }
        });


        /** 선택한 날짜로 setText **/
        Intent intent = getIntent();
        clickDateStr = intent.getStringExtra("clickDateStr");

        final int year = Integer.parseInt(clickDateStr)/10000;
        final int month = (Integer.parseInt(clickDateStr) - (year * 10000)) / 100;
        final int day = Integer.parseInt(clickDateStr) % 100;

        DatePickTV.setText(year + "년 " + month + "월 " + day + "일");


        /** 일기쓰기 할 때 DB에 데이터가 있는지 없는지 확인 **/
        db.collection("diaries").document(clickDateStr)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()) {     //DB에 데이터가 있다면
                        editDiary.setText(""+documentSnapshot.get("content"));  //데이터 가져와서 Text에 넣어줌
                    }
                }
            }
        });


        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, String> diary = new HashMap<>();
                diary.put("date",clickDateStr);
                diary.put("content", String.valueOf(editDiary.getText()));
                diary.put("emoji","good");  //감정 선택한거 반영시킬수 있도록 만들기

                db.collection("diaries").document(clickDateStr).set(diary)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("firebase", "success");
                            }
                        });
            }
        });
    }


}
