package com.diary.jimin.newmaildairy;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ViewLetterRecyclerAdapter extends RecyclerView.Adapter<ViewLetterRecyclerAdapter.ViewHolder>{
    private ArrayList<ItemDictionary> mData;

    private OnItemClickListener mListener = null;

    private FirebaseFirestore db;
    private FirebaseUser firebaseUser;
    private String userId;
    private String link;

    private Context mContext;

    public void setOnItemClickListener (OnItemClickListener listener) {
        this.mListener =  listener;
    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        protected ImageView emoji;
        protected TextView kordate;
        protected TextView date;
        protected TextView content;

        public ViewHolder (View view){
            super(view);

            this.kordate=(TextView) view.findViewById(R.id.view_letter_text);
            this.content = itemView.findViewById(R.id.item_content_text);
            this.emoji = itemView.findViewById(R.id.item_emo_image);
            Log.d(this.getClass().getName(),"DB1");

            firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
            if (firebaseUser != null) {
                userId = firebaseUser.getUid();
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    Log.d(this.getClass().getName(),"DB2");

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

                        Log.d(this.getClass().getName(),""+mListener);

                        /** 리스너 객체의 메소드 호출 **/
                        if (mListener != null) {
                            mListener.onItemClick(view,pos);
                            Log.d(this.getClass().getName(),"DB3");

                            String dateStr = mData.get(pos).getDate();;
                            Log.d("dateStr",""+dateStr);

                            db = FirebaseFirestore.getInstance();
                            db.collection(userId).document(dateStr)
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(view.getContext(),CheckLetterActivity.class);
                                        DocumentSnapshot documentSnapshot = task.getResult();
                                        if (documentSnapshot.exists()) {
                                            intent.putExtra("dateStr",""+documentSnapshot.get("date"));
                                            intent.putExtra("letterStr",""+documentSnapshot.get("letter"));

                                            //나중에 DB에 link 들어오면 주석 풀 것
                                            //link = getYoutubeIDFromURL((String)documentSnapshot.get("link"));
                                            //intent.putExtra("linkStr",link);
                                            //Log.d("linkStr",""+link);

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

                                        mContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                    }

                                }
                            });

                        }
                    }


                }
            });


        }
    }

    public ViewLetterRecyclerAdapter (Context mContext, ArrayList<ItemDictionary> mData){
        this.mData=mData;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public ViewLetterRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       Context context = parent.getContext();
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.from(parent.getContext()).inflate(R.layout.view_letter_recyclerview_item, parent, false);
        ViewLetterRecyclerAdapter.ViewHolder viewHolder = new ViewLetterRecyclerAdapter.ViewHolder(view);

        return viewHolder;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.kordate.setText(mData.get(position).getKordate());
    }

    @Override
    public int getItemCount() {

        return (null != mData ? mData.size() : 0);
    }

    public String getYoutubeIDFromURL(String temp) {


        if (temp.equals("") || temp == null) {
            return "error";
        } else {
            int idx = 0;
            if (temp.contains("be/")) {
                idx = temp.indexOf("be/");
            }
            else if (temp.contains("?v=")) {
                idx = temp.indexOf("?v=");
            } else {
                return temp;
            }
            String result = temp.substring(idx + 3);
            if (result.length() >= 12) {
                result = result.substring(0, 12).trim();
            }
            return result;
        }
    }
    
}
