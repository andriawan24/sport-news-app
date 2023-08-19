package id.andriawan24.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.andriawan24.domain.models.NewsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteNewsDao {

    @Query("SELECT * FROM NewsModel")
    fun getFavoriteNews(): Flow<List<NewsModel>>

    @Query("SELECT * FROM NewsModel WHERE title = :title")
    fun getFavoriteNews(title: String): Flow<List<NewsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteNews(newsModel: NewsModel)

    @Query("DELETE FROM NewsModel WHERE id = :id")
    suspend fun deleteFavoriteNews(id: Int)
}