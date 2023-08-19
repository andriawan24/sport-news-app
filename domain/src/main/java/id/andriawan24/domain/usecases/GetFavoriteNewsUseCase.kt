package id.andriawan24.domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import id.andriawan24.domain.models.NewsModel
import id.andriawan24.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetFavoriteNewsUseCase @Inject constructor(
    private val newsRepository: INewsRepository
) {

    fun execute(title: String): Flow<List<NewsModel>> {
        return newsRepository.getFavoriteNews(title = title)
    }
}