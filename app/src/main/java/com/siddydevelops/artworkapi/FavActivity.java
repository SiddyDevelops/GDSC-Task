package com.siddydevelops.artworkapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.siddydevelops.artworkapi.API.ApiInterface;
import com.siddydevelops.artworkapi.API.ArtworkItem;
import com.siddydevelops.artworkapi.API.JSONResponse;
import com.siddydevelops.artworkapi.RVAdapters.ArtworkRVAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavActivity extends AppCompatActivity {

    RecyclerView favRV;
    List<ArtworkItem> artworkItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        favRV = findViewById(R.id.favartworkRV);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.artic.edu/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<JSONResponse> call = apiInterface.getArtworks();

        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                artworkItemList = new ArrayList<>(Arrays.asList(jsonResponse.getArtworkItems()));
//                for(ArtworkItem artworkItem : artworkItemList)
//                {
//                    Log.i("DATA->",artworkItem.getId() + "");
//                }
                favRV.setLayoutManager(new LinearLayoutManager(FavActivity.this));
                favRV.setAdapter(new ArtworkRVAdapter(artworkItemList));
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.i("ERROR->",t+"");
            }
        });

    }

}