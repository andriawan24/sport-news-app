package id.andriawan24.domain.usecases

import id.andriawan24.domain.models.NewsModel
import id.andriawan24.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFavoritesNewsUseCase @Inject constructor(private val newsRepository: INewsRepository) {

    fun execute(): Flow<List<NewsModel>> {
        return newsRepository.getFavoriteNews()
    }
}