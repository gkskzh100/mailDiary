package com.diary.jimin.newmaildairy;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CheckDiaryActivity extends AppCompatActivity {

    private ImageView checkDiaryImg;
    private TextView checkDiaryDate;
    private TextView checkDiaryTv;
    private Button checkDiaryBtn;
    private String dateStr;
    private String emojiStr = null;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_diary);

        init();

        String extras;
        Intent intent = getIntent();


        extras = intent.getStringExtra("dateStr");
        checkDiaryDate.setText(extras);
        Log.d("dateStr","checkDiaryActivity = "+extras);
        dateStr = extras;


        extras = intent.getStringExtra("contentStr");
        checkDiaryTv.setText(extras);

        extras = intent.getStringExtra("emojiStr");
        if(extras.equals("angry")) {
            checkDiaryImg.setImageResource(R.drawable.emo_diary_angry);
            emojiStr = "angry";
        } else if(extras.equals("cry")) {
            checkDiaryImg.setImageResource(R.drawable.emo_diary_cry);
            emojiStr = "cry";
        } else if(extras.equals("good")) {
            checkDiaryImg.setImageResource(R.drawable.emo_diary_good);
            emojiStr = "good";
        } else if(extras.equals("happy")) {
            checkDiaryImg.setImageResource(R.drawable.emo_diary_happy);
            emojiStr = "happy";
        } else if(extras.equals("sad")) {
            checkDiaryImg.setImageResource(R.drawable.emo_diary_sad);
            emojiStr = "sad";
        } else if(extras.equals("soso")) {
            checkDiaryImg.setImageResource(R.drawable.emo_diary_soso);
            emojiStr = "soso";
        }



        checkDiaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),CheckLetterActivity.class);
                intent1.putExtra("emojiStr",emojiStr);
                intent1.putExtra("dateStr",dateStr);
                startActivity(intent1);
            }
        });

    }

    private void init() {
        checkDiaryImg = findViewById(R.id.check_diary_img);
        checkDiaryDate = findViewById(R.id.check_diary_date);
        checkDiaryTv = findViewById(R.id.check_diary_tv);
        checkDiaryBtn = findViewById(R.id.check_diary_Btn);


    }
}
