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

    public static List<Coworker> getCoworkers(Coworkspace coworkspace){
        return CoworkspaceDataAccess.getCoworkers(coworkspace);
    }

    public static void checkIn(Coworkspace coworkspace, Coworker coworker) {
            CoworkspaceDataAccess.setCheckIn(coworkspace, coworker, true);
    }

    public static void checkOut(Coworkspace coworkspace, Coworker coworker) {
        CoworkspaceDataAccess.setCheckIn(coworkspace, coworker, false);
    }

    // ---------------- Private Methods --------------- //

}