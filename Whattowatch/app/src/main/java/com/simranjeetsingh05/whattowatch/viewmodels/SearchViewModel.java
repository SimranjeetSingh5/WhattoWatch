package com.simranjeetsingh05.whattowatch.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.simranjeetsingh05.whattowatch.repository.SearchTVShowRepository;
import com.simranjeetsingh05.whattowatch.responses.TVShowsResponses;

public class SearchViewModel extends ViewModel {

    private SearchTVShowRepository searchTVShowRepository;

    public SearchViewModel() {
        searchTVShowRepository = new SearchTVShowRepository();
    }

    public LiveData<TVShowsResponses> searchTVShow(String query, int page){
        return searchTVShowRepository.searchTVShow(query, page);
    }
}
