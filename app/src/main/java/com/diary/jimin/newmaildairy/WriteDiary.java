package com.diary.jimin.newmaildairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
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

public class WriteDiary extends AppCompatActivity implements View.OnClickListener{
    private EditText editDiary;
    private Button SaveBtn;
    private TextView DatePickTV;
    private Button HappySelect,GoodSelect, CrySelect, SadSelect, AngrySelect, SosoSelect;
    private Button  EmoHappy, EmoGood, EmoCry, EmoSad, EmoAngry, EmoSoso;
    int i = 0;

    private FirebaseFirestore db;
    private String clickDateStr;
    private FirebaseUser firebaseUser;
    private String userId;
    private String selectedEmo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        //findViewById
        editDiary = findViewById(R.id.edit_diary);
        SaveBtn = findViewById(R.id.diary_save_btn);
        DatePickTV = findViewById(R.id.viewDatePick);

        /**findViewById**/
        HappySelect = findViewById(R.id.diary_happy_select_btn);
        GoodSelect = findViewById(R.id.diary_good_select_btn);
        CrySelect = findViewById(R.id.diary_cry_select_btn);
        SadSelect = findViewById(R.id.diary_sad_select_btn);
        AngrySelect = findViewById(R.id.diary_angry_select_btn);
        SosoSelect = findViewById(R.id.diary_soso_select_btn);

        EmoHappy = findViewById(R.id.write_diary_happy);
        EmoGood = findViewById(R.id.write_diary_good);
        EmoCry = findViewById(R.id.write_diary_cry);
        EmoSad = findViewById(R.id.write_diary_sad);
        EmoAngry = findViewById(R.id.write_diary_angry);
        EmoSoso = findViewById(R.id.write_diary_soso);

        EmoHappy.setOnClickListener(this);
        EmoGood.setOnClickListener(this);
        EmoCry.setOnClickListener(this);
        EmoSad.setOnClickListener(this);
        EmoAngry.setOnClickListener(this);
        EmoSoso.setOnClickListener(this);

        /**Select 버튼 위로 올리기**/
        ViewCompat.setTranslationZ(HappySelect, 1);
        ViewCompat.setTranslationZ(GoodSelect, 1);
        ViewCompat.setTranslationZ(CrySelect, 1);
        ViewCompat.setTranslationZ(SadSelect, 1);
        ViewCompat.setTranslationZ(AngrySelect, 1);
        ViewCompat.setTranslationZ(SosoSelect, 1);



        /** Get User Id **/
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            userId = firebaseUser.getUid();
        }

        //firebase setting
        db = FirebaseFirestore.getInstance();


        /** 선택한 날짜로 setText **/
        Intent intent = getIntent();
        clickDateStr = intent.getStringExtra("clickDateStr");

        final int year = Integer.parseInt(clickDateStr)/10000;
        final int month = (Integer.parseInt(clickDateStr) - (year * 10000)) / 100;
        final int day = Integer.parseInt(clickDateStr) % 100;

        DatePickTV.setText(year + "년 " + month + "월 " + day + "일");


        /** 일기쓰기 할 때 DB에 데이터가 있는지 없는지 확인 **/
        db.collection(userId).document(clickDateStr)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()) {     //DB에 데이터가 있다면
                        editDiary.setText(""+documentSnapshot.get("content"));  //데이터 가져와서 Text에 넣어줌

                        //저장된 감정 불러오기
                        if(documentSnapshot.get("emoji").equals("good")){
                            selectedEmo = "good";
                            GoodSelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                            ViewCompat.setTranslationZ(GoodSelect, 1);
                        }
                        else if(documentSnapshot.get("emoji").equals("happy")){
                            selectedEmo = "happy";
                            HappySelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                            ViewCompat.setTranslationZ(HappySelect, 1);
                        }
                        else if(documentSnapshot.get("emoji").equals("cry")){
                            selectedEmo = "cry";
                            CrySelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                            ViewCompat.setTranslationZ(CrySelect, 1);
                        }
                        else if(documentSnapshot.get("emoji").equals("sad")){
                            selectedEmo = "sad";
                            SadSelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                            ViewCompat.setTranslationZ(SadSelect, 1);
                        }
                        else if(documentSnapshot.get("emoji").equals("angry")){
                            selectedEmo = "angry";
                            AngrySelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                            ViewCompat.setTranslationZ(AngrySelect, 1);
                        }
                        else if(documentSnapshot.get("emoji").equals("soso")){
                            selectedEmo = "soso";
                            SosoSelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                            ViewCompat.setTranslationZ(SosoSelect, 1);
                        }
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

                //감정 선택한거 반영시킬수 있도록 만들기
                if(selectedEmo == null){
                    Toast.makeText(getApplicationContext(),"오늘의 감정을 선택해주세요!", Toast.LENGTH_SHORT);
                }
                else{
                    diary.put("emoji",selectedEmo);
                    db.collection(userId).document(clickDateStr).set(diary)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Log.d("firebase", "success");
                                }
                            });

                    finish();
                }
            }
        });
    }

    @Override
    public void onClick(View v){
        i = 1 - i;

        /**select 이미지 전환**/
        switch(v.getId()){
            case R.id.write_diary_happy :
                HappySelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                GoodSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                CrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SadSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                AngrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SosoSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);

                ViewCompat.setTranslationZ(HappySelect, 1);
                ViewCompat.setTranslationZ(GoodSelect, 1);
                ViewCompat.setTranslationZ(CrySelect, 1);
                ViewCompat.setTranslationZ(SadSelect, 1);
                ViewCompat.setTranslationZ(AngrySelect, 1);
                ViewCompat.setTranslationZ(SosoSelect, 1);

                selectedEmo = "happy";
                break;
            case R.id.write_diary_good :
                HappySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                GoodSelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                CrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SadSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                AngrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SosoSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);

                ViewCompat.setTranslationZ(HappySelect, 1);
                ViewCompat.setTranslationZ(GoodSelect, 1);
                ViewCompat.setTranslationZ(CrySelect, 1);
                ViewCompat.setTranslationZ(SadSelect, 1);
                ViewCompat.setTranslationZ(AngrySelect, 1);
                ViewCompat.setTranslationZ(SosoSelect, 1);

                selectedEmo = "good";
                break;

            case R.id.write_diary_cry :
                HappySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                GoodSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                CrySelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                SadSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                AngrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SosoSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);

                ViewCompat.setTranslationZ(HappySelect, 1);
                ViewCompat.setTranslationZ(GoodSelect, 1);
                ViewCompat.setTranslationZ(CrySelect, 1);
                ViewCompat.setTranslationZ(SadSelect, 1);
                ViewCompat.setTranslationZ(AngrySelect, 1);
                ViewCompat.setTranslationZ(SosoSelect, 1);

                selectedEmo = "cry";
                break;

            case R.id.write_diary_sad :
                HappySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                GoodSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                CrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SadSelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                AngrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SosoSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);

                ViewCompat.setTranslationZ(HappySelect, 1);
                ViewCompat.setTranslationZ(GoodSelect, 1);
                ViewCompat.setTranslationZ(CrySelect, 1);
                ViewCompat.setTranslationZ(SadSelect, 1);
                ViewCompat.setTranslationZ(AngrySelect, 1);
                ViewCompat.setTranslationZ(SosoSelect, 1);

                selectedEmo = "sad";
                break;

            case R.id.write_diary_angry :
                HappySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                GoodSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                CrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SadSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                AngrySelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                SosoSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);

                ViewCompat.setTranslationZ(HappySelect, 1);
                ViewCompat.setTranslationZ(GoodSelect, 1);
                ViewCompat.setTranslationZ(CrySelect, 1);
                ViewCompat.setTranslationZ(SadSelect, 1);
                ViewCompat.setTranslationZ(AngrySelect, 1);
                ViewCompat.setTranslationZ(SosoSelect, 1);

                selectedEmo = "angry";
                break;

            case R.id.write_diary_soso :
                HappySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                GoodSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                CrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SadSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                AngrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SosoSelect.setBackgroundResource(R.drawable.emo_diary_select_btn);

                ViewCompat.setTranslationZ(HappySelect, 1);
                ViewCompat.setTranslationZ(GoodSelect, 1);
                ViewCompat.setTranslationZ(CrySelect, 1);
                ViewCompat.setTranslationZ(SadSelect, 1);
                ViewCompat.setTranslationZ(AngrySelect, 1);
                ViewCompat.setTranslationZ(SosoSelect, 1);

                selectedEmo = "soso";
                break;
        }
    }


}
