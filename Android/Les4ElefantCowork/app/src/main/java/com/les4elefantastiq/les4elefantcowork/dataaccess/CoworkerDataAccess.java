package com.les4elefantastiq.les4elefantcowork.dataaccess;

import com.les4elefantastiq.les4elefantcowork.models.Coworker;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Math on 05/08/16.
 */
public class CoworkerDataAccess {

    public static Coworker getCoworker(String linkedInId) {
        CoworkerInterface coworkerInterface = CommonDataAccess.getRetrofit().create(CoworkerInterface.class);
        Call<Coworker> coworkerCall = coworkerInterface.getProfile(linkedInId);

        try {
            return coworkerCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void login(Coworker coworker) {
        CoworkerInterface coworkerInterface = CommonDataAccess.getRetrofit().create(CoworkerInterface.class);
        Call<Void> loginCall = coworkerInterface.login(coworker);

        try {
            loginCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public interface CoworkerInterface {
        @POST("/api/coworker")
        Call<Void> login(@Path("coworker") Coworker coworker);

        @GET("/api/coworker/{linkedInId}")
        Call<Coworker> getProfile(@Path("linkedInId") String linkedInId);
    }

}
