package com.diary.jimin.maildiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private MaterialCalendarView mainCalendarView;
    private Button mainWriteDiaryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainCalendarView = findViewById(R.id.main_calendar);
        mainWriteDiaryBtn = findViewById(R.id.main_write_diary_btn);

        mainWriteDiaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteDiary.class);
                startActivity(intent);
            }
        });

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        int day = Integer.parseInt(dayFormat.format(currentTime));
        int month = Integer.parseInt(monthFormat.format(currentTime));
        int year = Integer.parseInt(yearFormat.format(currentTime));

        Log.d("day : ", year + "," + month + "," + day);

//        mainCalendarView.state().edit()
//                .setMaximumDate(CalendarDay.from(year,month-1,day))
//                .commit();
        mainCalendarView.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                Date currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat headerFormat = new SimpleDateFormat("yyyy MM",Locale.getDefault());
                String monthAndYear = headerFormat.format(currentTime.getTime());
                Log.d("monthAndYear : ", monthAndYear);
                return monthAndYear;
            }
        });
//        Log.d("day:", mainCalendarView.get);
    }

}
