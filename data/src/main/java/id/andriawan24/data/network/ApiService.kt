package id.andriawan24.data.network

import id.andriawan24.data.network.responses.NewsBaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("category") category: String
    ): NewsBaseResponse
}