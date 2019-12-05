package com.diary.jimin.newmaildairy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    }

    private void init(){
        checkLetterImg = findViewById(R.id.check_letter_emoji);
        checkLetterDate = findViewById(R.id.check_letter_date);
        checkLetterContent = findViewById(R.id.check_letter_content);
    }
}
