package com.siddydevelops.artworkapi.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("artworks")
    Call<JSONResponse> getArtworks();

    @GET("artworks/search")
    Call<JSONResponse> getSearchResponse(@Query("q") String searchTopic);

}
