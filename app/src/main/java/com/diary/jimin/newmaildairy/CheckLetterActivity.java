package com.diary.jimin.newmaildairy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class CheckLetterActivity extends AppCompatActivity {

    private ImageView checkLetterImg;
    private TextView checkLetterDate;
    private TextView checkLetterContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_letter);

        init();

        String extras;
        Intent intent = getIntent();

        extras = intent.getStringExtra("dateStr");
        checkLetterDate.setText(extras);
        Log.d("dateStr","checkLetterActivity = "+extras);

        extras = intent.getStringExtra("LetterStr");
        checkLetterContent.setText(extras);

        extras = intent.getStringExtra("emojiStr");
        if(extras.equals("angry"))
            checkLetterImg.setImageResource(R.drawable.emo_diary_angry);
        else if(extras.equals("cry"))
            checkLetterImg.setImageResource(R.drawable.emo_diary_cry);
        else if(extras.equals("good"))
            checkLetterImg.setImageResource(R.drawable.emo_diary_good);
        else if(extras.equals("happy"))
            checkLetterImg.setImageResource(R.drawable.emo_diary_happy);
        else if(extras.equals("sad"))
            checkLetterImg.setImageResource(R.drawable.emo_diary_sad);
        else if(extras.equals("soso"))
            checkLetterImg.setImageResource(R.drawable.emo_diary_soso);

    }


    private void init(){
        checkLetterImg = findViewById(R.id.check_letter_emoji);
        checkLetterDate = findViewById(R.id.check_letter_date);
        checkLetterContent = findViewById(R.id.check_letter_content);
    }
}
