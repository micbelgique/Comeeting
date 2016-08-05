package com.les4elefantastiq.les4elefantcowork.managers;

import com.les4elefantastiq.les4elefantcowork.dataaccess.CoworkspaceDataAccess;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;

import java.util.List;

public class CoworkspacesManager {

    // -------------- Objects, Variables -------------- //

    // ---------------- Public Methods ---------------- //

    public static List<Coworkspace> getCoworkspaces(){
        return CoworkspaceDataAccess.getAllCoworkspace();
    }


    // ---------------- Private Methods --------------- //

}