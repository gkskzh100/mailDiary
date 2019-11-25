package com.diary.jimin.newmaildairy;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.CustomViewHolder>{

    private Context mContext;
    private ArrayList<ItemDictionary> mData;

    private OnItemClickListener mListener = null;

    private FirebaseFirestore db;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView emoji;
        protected TextView date;
        protected TextView content;
        protected LinearLayout layout;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.emoji = itemView.findViewById(R.id.item_emo_image);
            this.date = itemView.findViewById(R.id.item_date_text);
            this.content = itemView.findViewById(R.id.item_content_text);
            this.layout = itemView.findViewById(R.id.item_layout);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        /** 리스너 객체의 메소드 호출 **/
                        if (mListener != null) {
                            mListener.onItemClick(view,pos);
                            /** 여기에다가 intent 넣어주면됨 **/
                            String dateStr = ""+date.getText();

                            db = FirebaseFirestore.getInstance();
                            db.collection("diaries").document(dateStr)
                                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(view.getContext(),CheckDiaryActivity.class);
                                        DocumentSnapshot documentSnapshot = task.getResult();
                                        if (documentSnapshot.exists()) {
                                            intent.putExtra("dateStr",""+documentSnapshot.get("date"));
                                            intent.putExtra("contentStr",""+documentSnapshot.get("content"));
                                            Log.d("dateStr",""+documentSnapshot.get("date"));

                                            if (documentSnapshot.get("emoji").equals("good")) {
                                                intent.putExtra("emojiStr","good");
                                            } else if (documentSnapshot.get("emoji").equals("happy")) {
                                                intent.putExtra("emojiStr","happy");
                                            } else if (documentSnapshot.get("emoji").equals("angry")) {
                                                intent.putExtra("emojiStr","angry");
                                            } else if (documentSnapshot.get("emoji").equals("cry")) {
                                                intent.putExtra("emojiStr","cry");
                                            } else if (documentSnapshot.get("emoji").equals("sad")) {
                                                intent.putExtra("emojiStr","sad");
                                            } else if (documentSnapshot.get("emoji").equals("soso")) {
                                                intent.putExtra("emojiStr","soso");
                                            }
                                        }
                                        mContext.startActivity(intent);
                                    }
                                }
                            });

                        }
                    }
                }
            });

        }
    }

    public RecycleAdapter (Context mContext, ArrayList<ItemDictionary> list) {
        this.mData = list;
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {


        holder.layout.setMinimumHeight(360);//조절해야된다

        holder.emoji.setScaleType(ImageView.ScaleType.FIT_CENTER);
        holder.date.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        holder.content.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);

        holder.emoji.setImageResource(mData.get(position).getEmoji());
        holder.date.setText(mData.get(position).getDate());
        holder.content.setText(mData.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return (null != mData ? mData.size() : 0);
    }


    /** Custom Listener 정의 **/
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    /** OnItemClickListener 객체 참조를 어댑터에 전달하는 메소드 **/
    public void setOnItemClickListener (OnItemClickListener listener) {
        this.mListener = listener;
    }





}
