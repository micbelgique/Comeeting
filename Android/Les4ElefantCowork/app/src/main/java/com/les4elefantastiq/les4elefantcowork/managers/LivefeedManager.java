package com.les4elefantastiq.les4elefantcowork.managers;

import com.les4elefantastiq.les4elefantcowork.dataaccess.LivefeedDataAccess;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;
import com.les4elefantastiq.les4elefantcowork.models.LiveFeedMessage;

import java.util.List;

/**
 * Created by Math on 05/08/16.
 */
public class LivefeedManager {

    // -------------- Objects, Variables -------------- //

    // ---------------- Public Methods ---------------- //

    public static List<LiveFeedMessage> getLiveFeedMessages(Coworkspace coworkspace){
        return LivefeedDataAccess.getLivefeedMessages(coworkspace);
    }


    // ---------------- Private Methods --------------- //
}

