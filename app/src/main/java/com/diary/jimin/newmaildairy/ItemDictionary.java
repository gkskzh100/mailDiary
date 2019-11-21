package com.diary.jimin.newmaildairy;

import android.widget.ImageView;

public class ItemDictionary {

    private ImageView emoji;
    private String date;
    private String content;

    public ItemDictionary(ImageView emoji, String date, String content) {
        this.emoji = emoji;
        this.date = date;
        this.content = content;
    }

    public ImageView getEmoji() {
        return emoji;
    }

    public void setEmoji(ImageView emoji) {
        this.emoji = emoji;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
