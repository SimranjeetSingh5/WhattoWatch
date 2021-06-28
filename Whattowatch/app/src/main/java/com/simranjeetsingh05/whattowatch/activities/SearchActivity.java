package com.simranjeetsingh05.whattowatch.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;


import com.simranjeetsingh05.whattowatch.R;
import com.simranjeetsingh05.whattowatch.adapters.TVShowsAdapter;
import com.simranjeetsingh05.whattowatch.databinding.ActivitySearchBinding;
import com.simranjeetsingh05.whattowatch.listeners.TVShowsListener;
import com.simranjeetsingh05.whattowatch.models.TvShow;
import com.simranjeetsingh05.whattowatch.viewmodels.SearchViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchActivity extends AppCompatActivity implements TVShowsListener {

    private ActivitySearchBinding activitySearchBinding;
    private SearchViewModel viewModel;
    private List<TvShow> tvShows = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;
    private Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        doInitialization();
    }

    private void doInitialization() {
        activitySearchBinding.imageBack.setOnClickListener(view -> onBackPressed());
        activitySearchBinding.tvshowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        tvShowsAdapter = new TVShowsAdapter(tvShows,this);
        activitySearchBinding.tvshowsRecyclerView.setAdapter(tvShowsAdapter);
        activitySearchBinding.inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (timer != null){
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().trim().isEmpty()) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(() -> {
                                currentPage = 1;
                                totalAvailablePages = 1;
                                searchTVShows(editable.toString());
                            });

                        }
                    }, 800);
                } else {
                    tvShows.clear();
                    tvShowsAdapter.notifyDataSetChanged();
                }
            }

        });
        activitySearchBinding.tvshowsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activitySearchBinding.tvshowsRecyclerView.canScrollVertically(1)) {
                    if (!activitySearchBinding.inputSearch.getText().toString().isEmpty()) {
                        if (currentPage < totalAvailablePages) {
                            currentPage += 1;
                            searchTVShows(activitySearchBinding.inputSearch.getText().toString());
                        }
                    }
                }
            }
        });
        activitySearchBinding.inputSearch.requestFocus();
    }


    private void searchTVShows(String query) {
        toggleLoading();
        viewModel.searchTVShow(query, currentPage).observe(this, tvShowsResponses -> {
            toggleLoading();
            if (tvShowsResponses.getTvShows() != null) {
                int oldCount = tvShows.size();
                tvShows.addAll(tvShowsResponses.getTvShows());
                tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size());
            }

        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            if (activitySearchBinding.getIsLoading() != null && activitySearchBinding.getIsLoading()) {
                activitySearchBinding.setIsLoading(false);
            } else {
                activitySearchBinding.setIsLoading(true);
            }
        } else {
            if (activitySearchBinding.getIsLoading() != null && activitySearchBinding.getIsLoading()) {
                activitySearchBinding.setIsLoading(false);
            } else {
                activitySearchBinding.setIsLoading(true);
            }
        }
    }

    @Override
    public void onTVShowClicked(TvShow tvShow) {
        Intent intent = new Intent(getApplicationContext(), TVShowDetailsActivity.class);
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }
}