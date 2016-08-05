package com.les4elefantastiq.les4elefantcowork.dataaccess;

import com.les4elefantastiq.les4elefantcowork.models.Coworker;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;
import com.les4elefantastiq.les4elefantcowork.models.LiveFeedMessage;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Math on 05/08/16.
 */
public class LivefeedDataAccess {
    public static final String API_URL = "http://comeetingapi.azurewebsites.net";

    public static List<LiveFeedMessage> getLivefeedMessages(Coworkspace coworkspace) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LivefeedInterface livefeedInterface = retrofit.create(LivefeedInterface.class);
        Call<List<LiveFeedMessage>> livefeedMessages = livefeedInterface.getLivefeedMessages(coworkspace.id);

        try {
            return livefeedMessages.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public interface LivefeedInterface {
        @GET("/api/coworkspace/{coworkspaceId}/livefeed/messages")
        Call<List<LiveFeedMessage>> getLivefeedMessages(@Path("coworkspaceId") int coworkspaceId);
    }
}
