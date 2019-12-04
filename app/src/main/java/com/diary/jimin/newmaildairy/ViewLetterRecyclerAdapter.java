package com.diary.jimin.newmaildairy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewLetterRecyclerAdapter extends RecyclerView.Adapter<ViewLetterRecyclerAdapter.ViewHolder>{
    private ArrayList<String> mData;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        protected TextView name;
        public ViewHolder (View view){
            super(view);
            this.name=(TextView) view.findViewById(R.id.view_letter_text);
        }
    }

    public ViewLetterRecyclerAdapter (ArrayList<String> list){
        this.mData=list;
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
        String text = mData.get(position);
        holder.name.setText(text);
    }

    @Override
    public int getItemCount() {

        return (null != mData ? mData.size() : 0);
    }

}
