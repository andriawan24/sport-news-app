package id.andriawan24.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.andriawan24.data.local.AppDatabase
import id.andriawan24.data.local.dao.FavoriteNewsDao
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        val passphrase = SQLiteDatabase.getBytes("footballwiki_secret".toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "sport_news_db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Singleton
    @Provides
    fun providesFavoriteNewsDao(appDatabase: AppDatabase): FavoriteNewsDao {
        return appDatabase.favoriteNewsDao()
    }
}