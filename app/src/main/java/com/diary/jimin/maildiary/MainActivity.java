package com.diary.jimin.maildiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private MaterialCalendarView mainCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainCalendarView = findViewById(R.id.main_calendar);

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM",Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        int day = Integer.parseInt(dayFormat.format(currentTime));
        int month = Integer.parseInt(monthFormat.format(currentTime));
        int year = Integer.parseInt(yearFormat.format(currentTime));

        Log.d("day : ", year+","+month+","+day);

        mainCalendarView.state().edit()
                .setMaximumDate(CalendarDay.from(year,month-1,day))
                .commit();
//        mainCalendarView.setTitleFormatter(new MonthArrayTitleFormatter());
    }

}
