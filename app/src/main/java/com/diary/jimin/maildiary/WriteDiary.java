package com.diary.jimin.maildiary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;

public class WriteDiary extends AppCompatActivity {
    EditText editDiary;
    Spinner emotionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        emotionSpinner = findViewById(R.id.spinner_emotion);
        editDiary = findViewById(R.id.edit_diary);

        ArrayAdapter emotionAdapter = ArrayAdapter.createFromResource(this, R.array.emotion, android.R.layout.simple_spinner_item);
        emotionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emotionSpinner.setAdapter(emotionAdapter);
    }

    @SuppressLint("WrongConstant")
    private void saveDiary(String readDay) {

        FileOutputStream fos = null;

        try {
            fos = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS); //MODE_WORLD_WRITEABLE
            String content = editDiary.getText().toString();

            // String.getBytes() = 스트링을 배열형으로 변환?
            fos.write(content.getBytes());
            //fos.flush();
            fos.close();

            // getApplicationContext() = 현재 클래스.this ?
            Toast.makeText(getApplicationContext(), "일기 저장됨", Toast.LENGTH_SHORT).show();

        } catch (Exception e) { // Exception - 에러 종류 제일 상위 // FileNotFoundException , IOException
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "오류", Toast.LENGTH_SHORT).show();
        }
    }


}
