package com.les4elefantastiq.les4elefantcowork.managers;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.les4elefantastiq.les4elefantcowork.dataaccess.CoworkerDataAccess;
import com.les4elefantastiq.les4elefantcowork.models.Coworker;

public class CoworkerManager {

    // -------------- Objects, Variables -------------- //

    // ---------------- Public Methods ---------------- //

    @Nullable
    @WorkerThread
    public static Coworker getCoworker(String linkedInId) {
        return CoworkerDataAccess.getCoworker(linkedInId);
    }

    @Nullable
    @WorkerThread
    public static void login(Coworker coworker) {
        CoworkerDataAccess.login(coworker);
    }


    // ---------------- Private Methods --------------- //

}