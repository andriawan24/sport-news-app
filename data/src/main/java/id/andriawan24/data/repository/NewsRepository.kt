package id.andriawan24.data.repository

import id.andriawan24.data.local.dao.FavoriteNewsDao
import id.andriawan24.data.network.ApiService
import id.andriawan24.domain.models.NewsModel
import id.andriawan24.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val favoriteNewsDao: FavoriteNewsDao
) : INewsRepository {

    override suspend fun getNews(country: String, category: String): List<NewsModel> {
        val response = apiService.getNews(country, category)
        return response.articles?.map {
            NewsModel(
                source = NewsModel.NewsSourceModel(
                    id = it.source?.id.orEmpty(),
                    name = it.source?.name.orEmpty()
                ),
                title = it.title.orEmpty(),
                description = it.description.orEmpty(),
                url = it.url.orEmpty(),
                urlToImage = it.urlToImage.orEmpty(),
                publishedAt = it.publishedAt.orEmpty(),
                content = it.content.orEmpty(),
                author = it.author.orEmpty()
            )
        }.orEmpty()
    }

    override suspend fun insertFavoriteNews(newsModel: NewsModel) {
        favoriteNewsDao.insertFavoriteNews(newsModel)
    }

    override fun getFavoriteNews(title: String): Flow<List<NewsModel>> {
        return favoriteNewsDao.getFavoriteNews(title)
    }

    override fun getFavoriteNews(): Flow<List<NewsModel>> {
        return favoriteNewsDao.getFavoriteNews()
    }

    override suspend fun deleteFavoriteNews(newsId: Int) {
        favoriteNewsDao.deleteFavoriteNews(newsId)
    }
}