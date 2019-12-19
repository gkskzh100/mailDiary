package com.diary.jimin.newmaildairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.Write;

import java.util.HashMap;
import java.util.Map;

public class WriteMail extends AppCompatActivity implements View.OnClickListener{
    int i = 0;
    private Button HappySelect,GoodSelect, CrySelect, SadSelect, AngrySelect, SosoSelect;
    private Button  EmoHappy, EmoGood, EmoCry, EmoSad, EmoAngry, EmoSoso;
    private Button SaveBtn;
    private EditText editMail, addLink;

    private FirebaseFirestore db;
    private FirebaseUser firebaseUser;
    private String userId;
    private String selectedEmo = null;
    private String clickDateStr;

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

        SaveBtn = findViewById(R.id.mail_save_btn);
        editMail = findViewById(R.id.edit_mail);
        addLink = findViewById(R.id.write_mail_link);

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

        /** Get User Id **/
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            userId = firebaseUser.getUid();
        }
        /** 선택한 날짜로 setText **/
        Intent intent = getIntent();
        clickDateStr = intent.getStringExtra("clickDateStr");

//        final int year = Integer.parseInt(clickDateStr)/10000;
//        final int month = (Integer.parseInt(clickDateStr) - (year * 10000)) / 100;
//        final int day = Integer.parseInt(clickDateStr) % 100;

        //firebase setting
        db = FirebaseFirestore.getInstance();

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, String> mail = new HashMap<>();
                mail.put("content", String.valueOf(editMail.getText()));
                mail.put("link", String.valueOf(addLink.getText()));

                //감정 선택한거 반영시킬수 있도록 만들기
                if(selectedEmo == null){
                    Toast.makeText(getApplicationContext(),"감정을 선택해주세요!", Toast.LENGTH_SHORT);
                }
                else{
                    db.collection(userId).document(selectedEmo).collection("letter").document().set(mail)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Log.d("firebase","success");
                                }
                            });
                    /** 여기에 팝업창 띄우쟈 **/

                    show();
                }
            }
        });

    }

    private void show(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(WriteMail.this);
        dialog  .setTitle("주의사항")
                .setMessage("편지를 보내면 수정하거나 다시 볼 수 없습니다. 그래도 전송할까요?")
                .setPositiveButton("전송", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create().show();
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

                selectedEmo = "happy";
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

                selectedEmo = "good";
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

                selectedEmo = "cry";
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

                selectedEmo = "sad";
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

                selectedEmo = "angry";
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

                selectedEmo = "soso";
                break;
        }
    }
}
