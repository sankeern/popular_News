package com.example.popular_news.api;

import com.example.popular_news.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<News> getNews(
            @Query("country") String country ,
            @Query("category") String category,
            @Query("apiKey") String apiKey

            );

    @GET("everything")
    Call<News> getNewsSearch(
            @Query("q") String keyword,
             @Query("from") String fromDate,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );


}
