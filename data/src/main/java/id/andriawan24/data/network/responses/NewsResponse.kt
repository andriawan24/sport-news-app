package id.andriawan24.data.network.responses

data class NewsResponse(
    val source: NewsSourceResponse? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
) {
    data class NewsSourceResponse(
        val id: String? = null,
        val name: String? = null
    )
}
