package com.diary.jimin.newmaildairy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CompactCalendarView calendarView;
    private TextView calendarYearTV;
    private TextView calendarMonthTV;

    private Button writeDairyBtn;
    private Button mailBoxBtn;
    private Button collectDiaryBtn;
    private Button writeMailBtn;

    private FirebaseFirestore db;
    private FirebaseUser firebaseUser;

    private String userId;
    private String clickDateStr;
    private Date date;



    private long now = (System.currentTimeMillis() / 100000) * 100000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /** Get User Id **/
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            userId = firebaseUser.getUid();
        } else {
//            Log.d("idch", userId);
        }

        init();


        /** week language change **/
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();

        calendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        calendarView.setLocale(tz, Locale.KOREAN);
        calendarView.setUseThreeLetterAbbreviation(true);


        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                /** click 한 날짜 String 으로 바꿈 **/
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
                clickDateStr = dateFormat.format(dateClicked);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                /** MonthChange **/
                SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.getDefault());
                SimpleDateFormat month = new SimpleDateFormat("MM", Locale.getDefault());
                calendarYearTV.setText(year.format(firstDayOfNewMonth));
                calendarMonthTV.setText(month.format(firstDayOfNewMonth) + "월");
            }
        });

        /** Write Diary Activity Intent **/
        writeDairyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), WriteDiary.class);
                if (clickDateStr != null)
                    intent.putExtra("clickDateStr",clickDateStr);
                else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
                    date = new Date(now);
                    clickDateStr = dateFormat.format(date);
                    intent.putExtra("clickDateStr",clickDateStr);
                }
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
                Intent intent = new Intent(getApplicationContext(), CollectDiaryActivity.class);
                startActivity(intent);
            }
        });
        /** Write Mail Activity Intent **/
        writeMailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteMail.class);
                if (clickDateStr != null)
                    intent.putExtra("clickDateStr",clickDateStr);
                else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
                    date = new Date(now);
                    clickDateStr = dateFormat.format(date);
                    intent.putExtra("clickDateStr",clickDateStr);
                }
                startActivity(intent);
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


        /** Firebase setting **/
        db = FirebaseFirestore.getInstance();

        final List<Event> eventList = new ArrayList<>();


        if (db != null) {
            /** Read Firebase **/
            db.collection(userId)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("dbSuccess", document.getId() + " => " + document.getData());

                                    DateFormat df = new SimpleDateFormat("yyyyMMdd");
                                    Date d;
                                    String emoji = ""+document.get("emoji");
                                    try {
                                        d = df.parse(document.getId());
                                        /** Event Color Change**/
                                        if(emoji.equals("angry"))
                                            eventList.add(new Event(getResources().getColor(R.color.calendarAngryColor),d.getTime()));
                                        else if (emoji.equals("cry"))
                                            eventList.add(new Event(getResources().getColor(R.color.calendarCryColor),d.getTime()));
                                        else if (emoji.equals("good"))
                                            eventList.add(new Event(getResources().getColor(R.color.calendarGoodColor),d.getTime()));
                                        else if (emoji.equals("happy"))
                                            eventList.add(new Event(getResources().getColor(R.color.calendarHappyColor),d.getTime()));
                                        else if (emoji.equals("sad"))
                                            eventList.add(new Event(getResources().getColor(R.color.calendarSadColor),d.getTime()));
                                        else if (emoji.equals("soso"))
                                            eventList.add(new Event(getResources().getColor(R.color.calendarSosoColor),d.getTime()));
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            calendarView.addEvents(eventList);
                        }
                    });
        }

        /** First Date Setting **/
        long now = System.currentTimeMillis();
        date = new Date(now);
        SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.getDefault());
        SimpleDateFormat month = new SimpleDateFormat("MM", Locale.getDefault());
        String formatYear = year.format(date);
        String formatMonth = month.format(date);
        calendarYearTV.setText(formatYear);
        calendarMonthTV.setText(formatMonth + "월");
        /************************/


        /** Date Interval setting**/
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        int height = dm.heightPixels;
        calendarView.setTargetHeight(height / 2);
        /*************************/


        calendarView.shouldDrawIndicatorsBelowSelectedDays(true);
    }


}
