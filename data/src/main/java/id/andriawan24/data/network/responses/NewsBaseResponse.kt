package id.andriawan24.data.network.responses

data class NewsBaseResponse(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<NewsResponse>? = null
)
