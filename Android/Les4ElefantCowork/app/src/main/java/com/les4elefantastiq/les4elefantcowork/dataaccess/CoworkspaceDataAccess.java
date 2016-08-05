package com.les4elefantastiq.les4elefantcowork.dataaccess;

import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

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

        CoworkspaceInterface coworkspaceInterface = retrofit.create(CoworkspaceInterface.class);
        Call<List<Coworkspace>> coworkspaces = coworkspaceInterface.coworkspaces();

        try {
            return coworkspaces.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public interface CoworkspaceInterface {
        @GET("/api/coworkspaces")
        Call<List<Coworkspace>> coworkspaces();
    }


    /*
    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }
    */
}
