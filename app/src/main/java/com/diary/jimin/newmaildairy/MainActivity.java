package com.diary.jimin.newmaildairy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private CompactCalendarView calendarView;
    private TextView calendarYearTV;
    private TextView calendarMonthTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();



        /** week language change **/
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();

        calendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        calendarView.setLocale(tz, Locale.KOREAN);
        calendarView.setUseThreeLetterAbbreviation(true);
        /************************/


        /** MonthChange **/
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                SimpleDateFormat year = new SimpleDateFormat("yyyy",Locale.getDefault());
                SimpleDateFormat month = new SimpleDateFormat("MM",Locale.getDefault());
                calendarYearTV.setText(year.format(firstDayOfNewMonth));
                calendarMonthTV.setText(month.format(firstDayOfNewMonth) + "월");
            }
        });


    }

    private void init() {
        calendarView = findViewById(R.id.calendar_view);
        calendarYearTV = findViewById(R.id.main_calendar_year);
        calendarMonthTV = findViewById(R.id.main_calendar_month);
        /** First Date Setting **/
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat year = new SimpleDateFormat("yyyy",Locale.getDefault());
        SimpleDateFormat month = new SimpleDateFormat("MM",Locale.getDefault());
        String formatYear = year.format(date);
        String formatMonth = month.format(date);
        calendarYearTV.setText(formatYear);
        calendarMonthTV.setText(formatMonth + "월");
        /************************/

        /** Date Interval setting**/
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        int height = dm.heightPixels;
        calendarView.setTargetHeight(height/2);
        /*************************/

    }
}
