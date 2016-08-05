package com.les4elefantastiq.les4elefantcowork.models;

public class LiveFeedMessage {

    // -------------- Objects, Variables -------------- //

    public String text;
    public int type;
    public String dateTime; // Date ?
    public String tweetLink;
    public String sender;
    public String coworkerLinkedInId;
    public Boolean isBirthday;


    // ----------------- Constructor ------------------ //

    public LiveFeedMessage(String text, int type, String dateTime, String tweetLink, String sender, String coworkerLinkedInId, Boolean isBirthday) {
        this.text = text;
        this.type = type;
        this.dateTime = dateTime;
        this.tweetLink = tweetLink;
        this.sender = sender;
        this.coworkerLinkedInId = coworkerLinkedInId;
        this.isBirthday = isBirthday;
    }

    // ---------------- Public Methods ---------------- //

    // ---------------- Private Methods --------------- //

    // ----------------- Miscellaneous ---------------- //

}