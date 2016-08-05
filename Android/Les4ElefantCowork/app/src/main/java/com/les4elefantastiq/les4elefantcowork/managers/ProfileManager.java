package com.les4elefantastiq.les4elefantcowork.managers;

public class ProfileManager {

    // -------------- Objects, Variables -------------- //

    private static boolean isLogged;


    // ---------------- Public Methods ---------------- //

    public static boolean isLogged() {
        return isLogged;
    }

    public static void signWithLinkedIn() {
        // TODO : Do request

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isLogged = true;
    }


    // ---------------- Private Methods --------------- //

}