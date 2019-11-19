package com.diary.jimin.newmaildairy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    Button btnSave;
    TextView tvDatePick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        editDiary = findViewById(R.id.edit_diary);
        btnSave = findViewById(R.id.btn_Save);
        tvDatePick = findViewById(R.id.viewDatePick);

        Calendar c = Calendar.getInstance();
        int cYear = c.get(Calendar.YEAR);
        int cMonth = c.get(Calendar.MONTH)+1;
        int cDay = c.get(Calendar.DAY_OF_MONTH);

        tvDatePick.setText(cYear + "년 " + cMonth + "월 " + cDay + "일");
    }


    @Override
    public void onClick(View v){
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
