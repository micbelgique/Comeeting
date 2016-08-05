package com.les4elefantastiq.les4elefantcowork.dataaccess;

import com.les4elefantastiq.les4elefantcowork.models.Coworker;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Math on 05/08/16.
 */
public class CoworkspaceDataAccess {
    public static final String API_URL = "http://comeetingapi.azurewebsites.net";

    public static List<Coworkspace> getAllCoworkspace() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CoworkspacesInterface coworkspacesInterface = retrofit.create(CoworkspacesInterface.class);
        Call<List<Coworkspace>> coworkspaces = coworkspacesInterface.coworkspaces();

        try {
            return coworkspaces.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static List<Coworker> getCoworkers(Coworkspace coworkspace) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CoworkspaceInterface coworkspaceInterface = retrofit.create(CoworkspaceInterface.class);
        Call<List<Coworker>> coworkers = coworkspaceInterface.cowokers(coworkspace.id);

        try {
            return coworkers.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void setCheckIn(Coworkspace coworkspace, Coworker coworker, Boolean checkIn) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CoworkspaceCheckInCheckOutInterface checkInInterface = retrofit.create(CoworkspaceCheckInCheckOutInterface.class);

        Call<Void> checkInRequest;
        if (checkIn) {
            checkInRequest = checkInInterface.checkin(coworkspace.id, coworker.linkedinId);
        } else {
            checkInRequest = checkInInterface.checkout(coworkspace.id, coworker.linkedinId);
        }

        try {
            checkInRequest.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface CoworkspacesInterface {
        @GET("/api/coworkspaces")
        Call<List<Coworkspace>> coworkspaces();
    }

    public interface CoworkspaceInterface {
        @GET("/api/coworkspace/{coworkspaceId}/coworkers")
        Call<List<Coworker>> cowokers(@Path("coworkspaceId") int coworkspaceId);
    }

    public interface CoworkspaceCheckInCheckOutInterface {
        @DELETE("/api/coworkspace/{coworkspaceId}/coworker/{coworkerId}")
        Call<Void> checkout(
                @Path("coworkspaceId") int coworkspaceId,
                @Path("coworkerId") int coworkerId);

        @POST("/api/coworkspace/{coworkspaceId}/coworker/{coworkerId}")
        Call<Void> checkin(
                @Path("coworkspaceId") int coworkspaceId,
                @Path("coworkerId") int coworkerId);
    }
}
