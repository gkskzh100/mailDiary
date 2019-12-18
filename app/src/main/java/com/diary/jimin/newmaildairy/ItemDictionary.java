package com.diary.jimin.newmaildairy;

import android.widget.ImageView;

public class ItemDictionary {

    private int emoji;
    private String date;
    private String content;
    private String letter;
    private String kordate;

    public ItemDictionary(int emoji, String date, String content) {
        this.emoji = emoji;
        this.date = date;
        this.content = content;
    }

    public ItemDictionary(String date, String kordate) {
        this.date = date;
        this.kordate = kordate;
    }

    public int getEmoji() {
        return emoji;
    }

    public void setEmoji(int emoji) {
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

    public String getLetter() {
        return letter;
    }

    public void setLetter(int emoji) {
        this.letter = letter;
    }

    public String getKordate() {
        return kordate;
    }

    public void setKordate(int kordate) {
        this.emoji = kordate;
    }
}

