package com.diary.jimin.newmaildairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CheckLetterActivity extends YouTubeBaseActivity {

    YouTubePlayerView youtubeView;
    YouTubePlayer.OnInitializedListener listener;
    Button button;
    String link;

    private ImageView checkLetterImg;
    private TextView checkLetterDate;
    private TextView checkLetterContent;

    private FirebaseFirestore db;
    private FirebaseUser firebaseUser;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_letter);

        init();


        String extras;
        Intent intent = getIntent();

        extras = intent.getStringExtra("dateStr");
        checkLetterDate.setText(extras);
        Log.d("dateStr","checkLetterActivity = "+extras);

        /** Get User Id **/
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            userId = firebaseUser.getUid();
        }

        db = FirebaseFirestore.getInstance();
        db.collection(userId).document(extras)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        checkLetterContent.setText(""+documentSnapshot.get("letter"));
                        link = ""+documentSnapshot.get("link");
                    }
                }
            }
        });

        extras = intent.getStringExtra("emojiStr");
        if(extras.equals("angry"))
            checkLetterImg.setImageResource(R.drawable.emo_diary_angry);
        else if(extras.equals("cry"))
            checkLetterImg.setImageResource(R.drawable.emo_diary_cry);
        else if(extras.equals("good"))
            checkLetterImg.setImageResource(R.drawable.emo_diary_good);
        else if(extras.equals("happy"))
            checkLetterImg.setImageResource(R.drawable.emo_diary_happy);
        else if(extras.equals("sad"))
            checkLetterImg.setImageResource(R.drawable.emo_diary_sad);
        else if(extras.equals("soso"))
            checkLetterImg.setImageResource(R.drawable.emo_diary_soso);

        listener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                youTubePlayer.loadVideo("57sYinArEC4"); //일단 NCT 영상 해놨는데 추후에 바꿀 것
                youTubePlayer.loadVideo(link);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                youtubeView.initialize("AIzaSyCydLBimEAQ23U42DWVqUAs5HNqiVhbFcM", listener);
            }
        });
    }



    private void init() {
        checkLetterImg = findViewById(R.id.check_letter_emoji);
        checkLetterDate = findViewById(R.id.check_letter_date);
        checkLetterContent = findViewById(R.id.check_letter_content);
        button = (Button) findViewById(R.id.youtubeButton);
        youtubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
    }
}
