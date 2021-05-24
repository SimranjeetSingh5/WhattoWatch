package com.simranjeetsingh05.whattowatch.responses;

import com.google.gson.annotations.SerializedName;
import com.simranjeetsingh05.whattowatch.models.TvShow;

import java.util.List;

public class TVShowsResponses {

    @SerializedName("page")
    private int page;

    @SerializedName("pages")
    private int pages;

    @SerializedName("tv_shows")
    private List<TvShow> tvShows;

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public List<TvShow> getTvShows() {
        return tvShows;
    }
}
