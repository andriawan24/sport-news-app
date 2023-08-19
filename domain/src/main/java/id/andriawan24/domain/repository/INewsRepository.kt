package id.andriawan24.domain.repository

import id.andriawan24.domain.models.NewsModel
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    suspend fun getNews(country: String, category: String): List<NewsModel>
    suspend fun insertFavoriteNews(newsModel: NewsModel)
    fun getFavoriteNews(): Flow<List<NewsModel>>
    fun getFavoriteNews(title: String): Flow<List<NewsModel>>
    suspend fun deleteFavoriteNews(newsId: Int)
}