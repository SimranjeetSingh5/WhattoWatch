package com.simranjeetsingh05.whattowatch.network;

import com.simranjeetsingh05.whattowatch.models.TVShowDetails;
import com.simranjeetsingh05.whattowatch.responses.TVShowDetailsResponse;
import com.simranjeetsingh05.whattowatch.responses.TVShowsResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("most-popular")
    Call<TVShowsResponses> getMostPopularTVShows(@Query("page") int page) ;

    //
    @GET("show-details")
    Call<TVShowDetailsResponse> getTVShowDetails(@Query("q") String tvShowId);
}
