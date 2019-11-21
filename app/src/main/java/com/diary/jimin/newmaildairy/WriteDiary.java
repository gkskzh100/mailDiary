package com.diary.jimin.newmaildairy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Calendar;

public class WriteDiary extends AppCompatActivity implements View.OnClickListener {
    EditText editDiary;
    Button SaveBtn;
    TextView DatePickTV;
    FrameLayout EmoHappyFL;
    ImageView SelectIV, UnselectIV;
    int i = 0;

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

        //날짜 출력
        Calendar c = Calendar.getInstance();
        int cYear = c.get(Calendar.YEAR);
        int cMonth = c.get(Calendar.MONTH)+1;
        int cDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickTV.setText(cYear + "년 " + cMonth + "월 " + cDay + "일");
    }


    @Override
    public void onClick(View v){
        //일기 저장 (지금 안됨)
        switch (v.getId()){
            case R.id.btn_Save:
                try{
                    String txt = editDiary.getText().toString();

                    FileOutputStream outstream = openFileOutput("test.txt", Activity.MODE_WORLD_WRITEABLE);

                    outstream.write(txt.getBytes());

                    Toast.makeText(this, "저장", Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(this, "저장실패", Toast.LENGTH_LONG).show();
                }
        }

    }

}
