package com.diary.jimin.newmaildairy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class WriteMail extends AppCompatActivity {
    int i = 0;
    ImageView HappySelect, HappyUnselect, GoodSelect, GoodUnselect;
    FrameLayout EmoHappy, EmoGood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_mail);

        //findViewById
        HappySelect = findViewById(R.id.mail_happy_select_btn);
        HappyUnselect = findViewById(R.id.mail_happy_unselect_btn);
        GoodSelect = findViewById(R.id.mail_good_select_btn);
        GoodUnselect = findViewById(R.id.mail_good_unselect_btn);

        EmoHappy = findViewById(R.id.write_mail_happy);
        EmoGood = findViewById(R.id.write_mail_good);

        //select 이미지 visiblity
        HappyUnselect.setVisibility(View.VISIBLE);
        HappySelect.setVisibility(View.INVISIBLE);
        GoodUnselect.setVisibility(View.VISIBLE);
        GoodSelect.setVisibility(View.INVISIBLE);

        //select 이미지 전환
        EmoHappy.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                i = 1 - i;

                if(i == 0){
                    HappySelect.setVisibility(View.VISIBLE);
                    HappyUnselect.setVisibility(View.INVISIBLE);
                }
                else{
                    HappySelect.setVisibility(View.INVISIBLE);
                    HappyUnselect.setVisibility(View.VISIBLE);
                }
            }
        });

        EmoGood.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                i = 1 - i;

                if(i == 0){
                    GoodSelect.setVisibility(View.VISIBLE);
                    GoodUnselect.setVisibility(View.INVISIBLE);
                }
                else{
                    GoodSelect.setVisibility(View.INVISIBLE);
                    GoodUnselect.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
