package id.andriawan24.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.andriawan24.domain.models.NewsModel
import id.andriawan24.domain.usecases.GetFavoritesNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// @HiltViewModel
class FavoriteViewModel(
    private val getFavoritesNews: GetFavoritesNewsUseCase
) : ViewModel() {

    private val _news = MutableStateFlow(emptyList<NewsModel>())
    val news = _news.asStateFlow()

    fun initData() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoritesNews.execute().collectLatest {
                _news.emit(it)
            }
        }
    }

    companion object {
        fun factory(getFavoritesNews: GetFavoritesNewsUseCase): ViewModelProvider.Factory {
            return viewModelFactory {
                initializer {
                    FavoriteViewModel(getFavoritesNews)
                }
            }
        }
    }
}