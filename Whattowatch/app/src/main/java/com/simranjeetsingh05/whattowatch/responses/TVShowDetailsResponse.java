package com.simranjeetsingh05.whattowatch.responses;

import com.google.gson.annotations.SerializedName;
import com.simranjeetsingh05.whattowatch.models.TVShowDetails;

public class TVShowDetailsResponse {

    @SerializedName("tvShow")
    private TVShowDetails tvShowDetails;

    public TVShowDetails getTvShowDetails() {
        return tvShowDetails;
    }
}
