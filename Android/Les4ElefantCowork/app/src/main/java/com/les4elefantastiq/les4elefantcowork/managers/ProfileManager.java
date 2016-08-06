package com.les4elefantastiq.les4elefantcowork.managers;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.les4elefantastiq.les4elefantcowork.activities.SignInActivity;
import com.les4elefantastiq.les4elefantcowork.dataaccess.CoworkerDataAccess;
import com.les4elefantastiq.les4elefantcowork.models.Coworker;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;
import com.linkedin.platform.LISessionManager;

public class ProfileManager {

    // -------------- Objects, Variables -------------- //

    public static String linkedInId;
    private static Coworker mCoworker;


    // ---------------- Public Methods ---------------- //

    public static boolean isLogged() {
        return linkedInId != null;
    }

    public static void signWithLinkedIn(final SignInActivity context) {
        LISessionManager.getInstance(context).init(context, context.buildScope(), context, true);
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