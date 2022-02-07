package com.siddydevelops.artworkapi.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONResponse {
    @SerializedName("data")
    @Expose
    private ArtworkItem[] artworkItems;

    public ArtworkItem[] getArtworkItems() {
        return artworkItems;
    }

    public void setArtworkItems(ArtworkItem[] artworkItems) {
        this.artworkItems = artworkItems;
    }
}
