package com.diary.jimin.newmaildairy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Debug;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    private Button writeDairyBtn;
    private Button mailBoxBtn;
    private Button collectDiaryBtn;
    private Button writeMailBtn;



    private long now = (System.currentTimeMillis() / 100000) * 100000;
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


        Event ev1 = new Event(Color.RED, 1572850800000L);
        calendarView.addEvent(ev1);
        Event ev2 = new Event(Color.RED, now);
        calendarView.addEvent(ev2);




        /** MonthChange **/
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
//                Date date = new Date(1574294400000L);
//                Log.d("click", "clickDate = " + dateClicked + "date = " + date);
//                if(dateClicked.toString().compareTo(""+date) == 0) {
//                    Log.d("click", "맞는거");
//                } else {
//                    Log.d("click", "다른거");
//                }

                //21일 0am == 1574294400000
                //21일 1am == 1574298000000
                //21일 2am == 1574301600000
                //3600000
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                SimpleDateFormat year = new SimpleDateFormat("yyyy",Locale.getDefault());
                SimpleDateFormat month = new SimpleDateFormat("MM",Locale.getDefault());
                calendarYearTV.setText(year.format(firstDayOfNewMonth));
                calendarMonthTV.setText(month.format(firstDayOfNewMonth) + "월");
            }
        });

        /** Write Diary Activity Intent **/
        writeDairyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WriteDiary.class);
                startActivity(intent);
            }
        });
        /** Mail Box Activity Intent **/
        mailBoxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        /** Collect Diary Activity Intent **/
        collectDiaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CheckDiaryActivity.class);
                startActivity(intent);
            }
        });
        /** Write Mail Activity Intent **/
        writeMailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


    }

    private void init() {
        calendarView = findViewById(R.id.calendar_view);
        calendarYearTV = findViewById(R.id.main_calendar_year);
        calendarMonthTV = findViewById(R.id.main_calendar_month);
        writeDairyBtn = findViewById(R.id.main_write_diary_btn);
        mailBoxBtn = findViewById(R.id.main_mailbox_btn);
        collectDiaryBtn = findViewById(R.id.main_collect_btn);
        writeMailBtn = findViewById(R.id.main_write_mail_btn);


        /** First Date Setting **/
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        Log.d("click","date : "+date);
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
//        calendarView.shouldSelectFirstDayOfMonthOnScroll(false);
//        calendarView.setCurrentSelectedDayBackgroundColor(Color.TRANSPARENT);
        calendarView.shouldDrawIndicatorsBelowSelectedDays(true);
    }


}
