package com.diary.jimin.newmaildairy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

public class CheckDiaryActivity extends AppCompatActivity {

    private TextView checkDiaryTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_diary);
        init();

    }

    private void init() {
        checkDiaryTv = findViewById(R.id.check_diary_tv);
        /** textView에 밑줄긋기 해논건데 고쳐야됨**/
        checkDiaryTv.setPaintFlags(checkDiaryTv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }
}
