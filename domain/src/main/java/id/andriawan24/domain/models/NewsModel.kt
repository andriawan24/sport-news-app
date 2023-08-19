package id.andriawan24.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class NewsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val source: NewsSourceModel,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) : Parcelable {

    @Parcelize
    data class NewsSourceModel(
        val id: String,
        val name: String
    ) : Parcelable
}