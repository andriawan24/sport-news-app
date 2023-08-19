package id.andriawan24.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.andriawan24.domain.models.NewsModel
import id.andriawan24.domain.usecases.GetFavoriteNewsUseCase
import id.andriawan24.domain.usecases.InsertFavoritesNewsUseCase
import id.andriawan24.domain.usecases.RemoveFavoritesNewsUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getFavoriteNewsUseCase: GetFavoriteNewsUseCase,
    private val insertFavoritesNewsUseCase: InsertFavoritesNewsUseCase,
    private val deleteFavoritesNewsUseCase: RemoveFavoritesNewsUseCase
) : ViewModel() {

    private val _favoriteNews = MutableLiveData<NewsModel?>(null)
    val favoriteNews: LiveData<NewsModel?> = _favoriteNews

    fun checkFavoriteNews(title: String) {
        viewModelScope.launch {
            getFavoriteNewsUseCase.execute(title).collectLatest {
                _favoriteNews.value = it.firstOrNull()
            }
        }
    }

    fun addOrRemoveFromFavorite(news: NewsModel) {
        viewModelScope.launch {
            if (_favoriteNews.value != null) {
                deleteFavoritesNewsUseCase.execute(_favoriteNews.value?.id ?: 0).collectLatest {
                    // no-ops
                }
            } else {
                insertFavoritesNewsUseCase.execute(news).collectLatest {
                    // no-ops
                }
            }
        }
    }
}