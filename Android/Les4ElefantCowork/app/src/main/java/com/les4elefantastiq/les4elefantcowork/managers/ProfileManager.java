package com.les4elefantastiq.les4elefantcowork.managers;

public class ProfileManager {

    // -------------- Objects, Variables -------------- //

    private static String linkedInId;


    // ---------------- Public Methods ---------------- //

    public static boolean isLogged() {
        return linkedInId != null;
    }

    public static void signWithLinkedIn() {
        // TODO : Do request

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        linkedInId = "blablabla";
    }


    // ---------------- Private Methods --------------- //

}