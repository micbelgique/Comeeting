package com.les4elefantastiq.les4elefantcowork.dataaccess;

import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by Math on 05/08/16.
 */
public class CoworkspaceDataAccess {
    public static final String API_URL = "http://www.api.blop.com";

    public static void doSomeStuff() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                //.addConverterFactory(GsonConverterFactory.create()) Json ?
                .build();
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
