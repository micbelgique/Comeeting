package com.les4elefantastiq.les4elefantcowork.managers;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.les4elefantastiq.les4elefantcowork.dataaccess.CoworkerDataAccess;
import com.les4elefantastiq.les4elefantcowork.models.Coworker;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;

public class ProfileManager {

    // -------------- Objects, Variables -------------- //

    private static String linkedInId;
    private static Coworker mCoworker;


    // ---------------- Public Methods ---------------- //

    public static boolean isLogged() {
        return linkedInId != null;
    }

    public static void signWithLinkedIn() {
        // TODO : Do request

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        linkedInId = "81920_aezan_aza";
    }

    @Nullable
    @WorkerThread
    public static Coworkspace getCurrentCowerkspace() {
        loadCoworker();

        if (mCoworker != null && mCoworker.currentCoworkspaceId != null)
            return CoworkspacesManager.getCoworkspace(mCoworker.currentCoworkspaceId);
        else
            return null;
    }


    // ---------------- Private Methods --------------- //

    private static void loadCoworker() {
        if (mCoworker == null)
            mCoworker = CoworkerDataAccess.getCoworker(linkedInId);
    }

}