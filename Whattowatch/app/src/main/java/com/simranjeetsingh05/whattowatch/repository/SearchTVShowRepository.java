package com.simranjeetsingh05.whattowatch.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.simranjeetsingh05.whattowatch.network.ApiClient;
import com.simranjeetsingh05.whattowatch.network.ApiService;
import com.simranjeetsingh05.whattowatch.responses.TVShowsResponses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTVShowRepository {

    private ApiService apiService;

    public SearchTVShowRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<TVShowsResponses> searchTVShow(String query, int page) {

        MutableLiveData<TVShowsResponses> data = new MutableLiveData<>();
        apiService.searchTVShow(query, page).enqueue(new Callback<TVShowsResponses>() {
            @Override
            public void onResponse(@NonNull Call<TVShowsResponses> call, @NonNull Response<TVShowsResponses> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TVShowsResponses> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}
