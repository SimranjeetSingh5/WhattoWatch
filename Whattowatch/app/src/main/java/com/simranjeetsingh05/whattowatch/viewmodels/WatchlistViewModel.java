package com.simranjeetsingh05.whattowatch.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.simranjeetsingh05.whattowatch.database.TVShowDatabase;
import com.simranjeetsingh05.whattowatch.models.TvShow;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class WatchlistViewModel extends AndroidViewModel {

    private TVShowDatabase tvShowDatabase;
    public WatchlistViewModel(@NonNull Application application) {
        super(application);
        tvShowDatabase = TVShowDatabase.getTvShowDatabase(application);
    }

    public Flowable<List<TvShow>> loadWatchlist(){
        return tvShowDatabase.tvShowDao().getWatchList();
    }

    public Completable removeTVShowWatchlist(TvShow tvShow){
        return tvShowDatabase.tvShowDao().removeFromWatchlist(tvShow);
    }

}
