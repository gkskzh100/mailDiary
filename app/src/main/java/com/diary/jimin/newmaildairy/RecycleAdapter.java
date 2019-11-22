package com.diary.jimin.newmaildairy;


import android.content.ClipData;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.CustomViewHolder>{

    private ArrayList<ItemDictionary> mData;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView emoji;
        protected TextView date;
        protected TextView content;
        protected LinearLayout layout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.emoji = itemView.findViewById(R.id.item_emo_image);
            this.date = itemView.findViewById(R.id.item_date_text);
            this.content = itemView.findViewById(R.id.item_content_text);
            this.layout = itemView.findViewById(R.id.item_layout);

        }
    }

    public RecycleAdapter (ArrayList<ItemDictionary> list) {
        this.mData = list;
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



}
