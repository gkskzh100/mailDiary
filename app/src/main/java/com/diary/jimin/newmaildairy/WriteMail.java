package com.diary.jimin.newmaildairy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class WriteMail extends AppCompatActivity implements View.OnClickListener{
    int i = 0;
    Button HappySelect,GoodSelect, CrySelect, SadSelect, AngrySelect, SosoSelect;
    Button  EmoHappy, EmoGood, EmoCry, EmoSad, EmoAngry, EmoSoso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_mail);

        /**findViewById**/
        HappySelect = findViewById(R.id.mail_happy_select_btn);
        GoodSelect = findViewById(R.id.mail_good_select_btn);
        CrySelect = findViewById(R.id.mail_cry_select_btn);
        SadSelect = findViewById(R.id.mail_sad_select_btn);
        AngrySelect = findViewById(R.id.mail_angry_select_btn);
        SosoSelect = findViewById(R.id.mail_soso_select_btn);

        EmoHappy = findViewById(R.id.write_mail_happy);
        EmoGood = findViewById(R.id.write_mail_good);
        EmoCry = findViewById(R.id.write_mail_cry);
        EmoSad = findViewById(R.id.write_mail_sad);
        EmoAngry = findViewById(R.id.write_mail_angry);
        EmoSoso = findViewById(R.id.write_mail_soso);

        EmoHappy.setOnClickListener(this);
        EmoGood.setOnClickListener(this);
        EmoCry.setOnClickListener(this);
        EmoSad.setOnClickListener(this);
        EmoAngry.setOnClickListener(this);
        EmoSoso.setOnClickListener(this);

        /**Select 버튼 위로 올리기**/
        ViewCompat.setTranslationZ(HappySelect, 1);
        ViewCompat.setTranslationZ(GoodSelect, 1);
        ViewCompat.setTranslationZ(CrySelect, 1);
        ViewCompat.setTranslationZ(SadSelect, 1);
        ViewCompat.setTranslationZ(AngrySelect, 1);
        ViewCompat.setTranslationZ(SosoSelect, 1);

    }

    @Override
    public void onClick(View v){
        i = 1 - i;

        /**select 이미지 전환**/
        switch(v.getId()){
            case R.id.write_mail_happy :
                HappySelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                GoodSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                CrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SadSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                AngrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SosoSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);

                ViewCompat.setTranslationZ(HappySelect, 1);
                ViewCompat.setTranslationZ(GoodSelect, 1);
                ViewCompat.setTranslationZ(CrySelect, 1);
                ViewCompat.setTranslationZ(SadSelect, 1);
                ViewCompat.setTranslationZ(AngrySelect, 1);
                ViewCompat.setTranslationZ(SosoSelect, 1);
                break;
            case R.id.write_mail_good :
                HappySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                GoodSelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                CrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SadSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                AngrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SosoSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);

                ViewCompat.setTranslationZ(HappySelect, 1);
                ViewCompat.setTranslationZ(GoodSelect, 1);
                ViewCompat.setTranslationZ(CrySelect, 1);
                ViewCompat.setTranslationZ(SadSelect, 1);
                ViewCompat.setTranslationZ(AngrySelect, 1);
                ViewCompat.setTranslationZ(SosoSelect, 1);
                break;

            case R.id.write_mail_cry :
                HappySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                GoodSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                CrySelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                SadSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                AngrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SosoSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);

                ViewCompat.setTranslationZ(HappySelect, 1);
                ViewCompat.setTranslationZ(GoodSelect, 1);
                ViewCompat.setTranslationZ(CrySelect, 1);
                ViewCompat.setTranslationZ(SadSelect, 1);
                ViewCompat.setTranslationZ(AngrySelect, 1);
                ViewCompat.setTranslationZ(SosoSelect, 1);
                break;

            case R.id.write_mail_sad :
                HappySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                GoodSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                CrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SadSelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                AngrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SosoSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);

                ViewCompat.setTranslationZ(HappySelect, 1);
                ViewCompat.setTranslationZ(GoodSelect, 1);
                ViewCompat.setTranslationZ(CrySelect, 1);
                ViewCompat.setTranslationZ(SadSelect, 1);
                ViewCompat.setTranslationZ(AngrySelect, 1);
                ViewCompat.setTranslationZ(SosoSelect, 1);
                break;

            case R.id.write_mail_angry :
                HappySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                GoodSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                CrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SadSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                AngrySelect.setBackgroundResource(R.drawable.emo_diary_select_btn);
                SosoSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);

                ViewCompat.setTranslationZ(HappySelect, 1);
                ViewCompat.setTranslationZ(GoodSelect, 1);
                ViewCompat.setTranslationZ(CrySelect, 1);
                ViewCompat.setTranslationZ(SadSelect, 1);
                ViewCompat.setTranslationZ(AngrySelect, 1);
                ViewCompat.setTranslationZ(SosoSelect, 1);
                break;

            case R.id.write_mail_soso :
                HappySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                GoodSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                CrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SadSelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                AngrySelect.setBackgroundResource(R.drawable.emo_diary_unselect_btn);
                SosoSelect.setBackgroundResource(R.drawable.emo_diary_select_btn);

                ViewCompat.setTranslationZ(HappySelect, 1);
                ViewCompat.setTranslationZ(GoodSelect, 1);
                ViewCompat.setTranslationZ(CrySelect, 1);
                ViewCompat.setTranslationZ(SadSelect, 1);
                ViewCompat.setTranslationZ(AngrySelect, 1);
                ViewCompat.setTranslationZ(SosoSelect, 1);
                break;
        }
    }
}
