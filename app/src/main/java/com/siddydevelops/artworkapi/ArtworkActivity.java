package com.siddydevelops.artworkapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ArtworkActivity extends AppCompatActivity {

    TextView titileN;
    TextView artistN;
    TextView yearOrigin;
    TextView provenanceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork);

        titileN = findViewById(R.id.titleN);
        artistN = findViewById(R.id.artistN);
        yearOrigin = findViewById(R.id.yearOrigin);
        provenanceText = findViewById(R.id.provenanceText);

        titileN.setText(getIntent().getStringExtra("TITLE"));
        artistN.setText(getIntent().getStringExtra("ARTIST"));
        yearOrigin.setText(getIntent().getStringExtra("DATE"));
        provenanceText.setText(getIntent().getStringExtra("PROVENANCE"));

    }
}