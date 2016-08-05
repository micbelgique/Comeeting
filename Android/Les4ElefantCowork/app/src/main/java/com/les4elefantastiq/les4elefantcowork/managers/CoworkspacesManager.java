package com.les4elefantastiq.les4elefantcowork.managers;

import com.les4elefantastiq.les4elefantcowork.dataaccess.CoworkspaceDataAccess;
import com.les4elefantastiq.les4elefantcowork.models.Coworker;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;

import java.util.List;

public class CoworkspacesManager {

    // -------------- Objects, Variables -------------- //

    // ---------------- Public Methods ---------------- //

    public static List<Coworkspace> getCoworkspaces(){
        return CoworkspaceDataAccess.getAllCoworkspace();
    }

    public static List<Coworker> getCoworkers(int coworkspaceId){
        return CoworkspaceDataAccess.getCoworkers(coworkspaceId);
    }

    public static void checkIn(int coworkspaceId, int coworkerId) {
            CoworkspaceDataAccess.setCheckIn(coworkspaceId, coworkerId, true);
    }

    public static void checkOut(int coworkspaceId, int coworkerId) {
        CoworkspaceDataAccess.setCheckIn(coworkspaceId, coworkerId, false);
    }

    // ---------------- Private Methods --------------- //

}