package com.simranjeetsingh05.whattowatch.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.simranjeetsingh05.whattowatch.R;
import com.simranjeetsingh05.whattowatch.adapters.TVShowsAdapter;
import com.simranjeetsingh05.whattowatch.databinding.ActivityMainBinding;
import com.simranjeetsingh05.whattowatch.listeners.TVShowsListener;
import com.simranjeetsingh05.whattowatch.models.TvShow;
import com.simranjeetsingh05.whattowatch.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TVShowsListener {

    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private List<TvShow> tvShows = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        doInitialization();
    }


    private  void doInitialization(){
        activityMainBinding.tvshowsRecyclerView.setHasFixedSize(true);
        viewModel  = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter = new TVShowsAdapter(tvShows, this);
        activityMainBinding.tvshowsRecyclerView.setAdapter(tvShowsAdapter);
        activityMainBinding.tvshowsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!activityMainBinding.tvshowsRecyclerView.canScrollVertically(1)){
                    if (currentPage <= totalAvailablePages){
                        currentPage += 1;
                        getMostPopularTVShows();
                    }
                }
            }
        });
        getMostPopularTVShows();
    }
    private void getMostPopularTVShows(){
        toggleLoading();
        viewModel.getMostPopularTVShows(currentPage).observe(this, mostPopularTVShowsResponses ->{
                    toggleLoading();
                    if(mostPopularTVShowsResponses != null){
                        totalAvailablePages = mostPopularTVShowsResponses.getPages();
                        if(mostPopularTVShowsResponses.getTvShows() != null){
                            int oldCount = tvShows.size();
                            tvShows.addAll((mostPopularTVShowsResponses.getTvShows()));
                            tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size());

                        }
                    }
                });
    }


    private  void toggleLoading() {
        if(currentPage == 1){
            if(activityMainBinding.getIsLoading() != null && activityMainBinding.getIsLoading()){
                activityMainBinding.setIsLoading(false);
            }else{
                activityMainBinding.setIsLoading(true);
            }
        }else{
            if(activityMainBinding.getIsLoading() != null && activityMainBinding.getIsLoading()){
                activityMainBinding.setIsLoading(false);
            }else{
                activityMainBinding.setIsLoading(true);
            }


        }
    }

    @Override
    public void onTVShowClicked(TvShow tvShow) {
        Intent intent = new Intent(getApplicationContext(), TVShowDetailsActivity.class);
        intent.putExtra("id",tvShow.getId());
        intent.putExtra("name",tvShow.getName());
        intent.putExtra("startDate",tvShow.getStartDate());
        intent.putExtra("country",tvShow.getCountry());
        intent.putExtra("network",tvShow.getNetwork());
        intent.putExtra("status",tvShow.getStatus());
        startActivity(intent);
    }
}