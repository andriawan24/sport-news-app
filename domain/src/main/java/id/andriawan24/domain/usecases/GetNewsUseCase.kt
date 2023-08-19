package id.andriawan24.domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import id.andriawan24.domain.models.NewsModel
import id.andriawan24.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class GetNewsUseCase @Inject constructor(private val repository: INewsRepository) {

    fun execute(country: String, category: String): Flow<Result<List<NewsModel>>> {
        return flow {
            try {
                val result = repository.getNews(country, category)
                emit(Result.success(result))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }
}