package com.simranjeetsingh05.whattowatch.listeners;

import com.simranjeetsingh05.whattowatch.models.TvShow;

public interface WatchlistListener {

    void onTVShowClicked(TvShow tvShow);

    void removeTVShowFromWatchlist(TvShow tvShow, int position);
}
