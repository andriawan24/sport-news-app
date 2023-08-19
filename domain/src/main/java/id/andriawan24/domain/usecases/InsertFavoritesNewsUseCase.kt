package id.andriawan24.domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import id.andriawan24.domain.models.NewsModel
import id.andriawan24.domain.repository.INewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class InsertFavoritesNewsUseCase @Inject constructor(
    private val newsRepository: INewsRepository
) {

    fun execute(news: NewsModel): Flow<Result<Boolean>> {
        return flow {
            newsRepository.insertFavoriteNews(news)
            emit(Result.success(true))
        }.flowOn(Dispatchers.IO)
    }
}