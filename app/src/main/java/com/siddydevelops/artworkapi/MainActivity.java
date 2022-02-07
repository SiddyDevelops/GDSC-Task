package com.siddydevelops.artworkapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ArtworkItem> artworkItemList;

    EditText searchEditText;
    ImageView searchIV;

    Button favButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.artworkRV);
        searchIV = findViewById(R.id.searchImageView);
        searchEditText = findViewById(R.id.searchEditText);

        favButton = findViewById(R.id.favButton);

        searchIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchTopic = searchEditText.getText().toString().toLowerCase().replace(" ","");
                getArtworkBySearch(searchTopic);
            }
        });

        favButton.setVisibility(View.INVISIBLE);
//        favButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),FavActivity.class);
//                startActivity(intent);
//            }
//        });

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
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(new ArtworkRVAdapter(artworkItemList));
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.i("ERROR->",t+"");
            }
        });
        
    }

    public void getArtworkBySearch(String searchTopic)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.artic.edu/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<JSONResponse> call = apiInterface.getSearchResponse(searchTopic);

        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                artworkItemList = new ArrayList<>(Arrays.asList(jsonResponse.getArtworkItems()));
//                for(ArtworkItem artworkItem : artworkItemList)
//                {
//                    Log.i("DATA->",artworkItem.getId() + "");
//                }
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(new ArtworkRVAdapter(artworkItemList));
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.i("ERROR->",t+"");
            }
        });
    }

}