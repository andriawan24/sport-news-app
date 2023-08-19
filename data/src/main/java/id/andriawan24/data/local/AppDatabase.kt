package id.andriawan24.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.andriawan24.data.local.converter.EntityTypeConverter
import id.andriawan24.data.local.dao.FavoriteNewsDao
import id.andriawan24.domain.models.NewsModel

@Database(
    entities = [NewsModel::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(EntityTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteNewsDao(): FavoriteNewsDao
}