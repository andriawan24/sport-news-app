package id.andriawan24.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.andriawan24.domain.models.NewsModel
import id.andriawan24.domain.usecases.GetFavoritesNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesNews: GetFavoritesNewsUseCase
): ViewModel() {

    private val _news = MutableStateFlow(emptyList<NewsModel>())
    val news = _news.asStateFlow()

    fun initData() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoritesNews.execute().collectLatest {
                _news.emit(it)
            }
        }
    }
}