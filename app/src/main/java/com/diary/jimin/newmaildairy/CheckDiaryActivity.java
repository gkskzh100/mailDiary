package com.diary.jimin.newmaildairy;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CheckDiaryActivity extends AppCompatActivity {

    private ImageView checkDiaryImg;
    private TextView checkDiaryDate;
    private TextView checkDiaryTv;
    private Button checkDiaryBtn;

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


        extras = intent.getStringExtra("contentStr");
        checkDiaryTv.setText(extras);

        extras = intent.getStringExtra("emojiStr");
        if(extras.equals("angry"))
            checkDiaryImg.setImageResource(R.drawable.emo_diary_angry);
        else if(extras.equals("cry"))
            checkDiaryImg.setImageResource(R.drawable.emo_diary_cry);
        else if(extras.equals("good"))
            checkDiaryImg.setImageResource(R.drawable.emo_diary_good);
        else if(extras.equals("happy"))
            checkDiaryImg.setImageResource(R.drawable.emo_diary_happy);
        else if(extras.equals("sad"))
            checkDiaryImg.setImageResource(R.drawable.emo_diary_sad);
        else if(extras.equals("soso"))
            checkDiaryImg.setImageResource(R.drawable.emo_diary_soso);

    }

    private void init() {
        checkDiaryImg = findViewById(R.id.check_diary_img);
        checkDiaryDate = findViewById(R.id.check_diary_date);
        checkDiaryTv = findViewById(R.id.check_diary_tv);
        checkDiaryBtn = findViewById(R.id.check_diary_Btn);


    }
}
