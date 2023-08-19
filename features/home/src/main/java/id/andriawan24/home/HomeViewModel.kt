package id.andriawan24.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.andriawan24.domain.models.NewsModel
import id.andriawan24.domain.usecases.GetNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _showLoading = MutableStateFlow(false)
    val showLoading = _showLoading.asStateFlow()

    private val _news = MutableStateFlow(emptyList<NewsModel>())
    val news = _news.asStateFlow()

    fun initData(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.emit(true)
            _news.emit(emptyList())
            getNewsUseCase.execute(country, "sports").collectLatest {
                try {
                    val result = it.getOrThrow()
                    _news.emit(result)
                    _showLoading.emit(false)
                } catch (e: Exception) {
                    _showLoading.emit(false)
                    Timber.e(e)
                }
            }
        }
    }
}