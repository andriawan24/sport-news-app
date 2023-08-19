package id.andriawan24.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import id.andriawan24.domain.models.NewsModel

class EntityTypeConverter {

    @TypeConverter
    fun convertSourceToString(model: NewsModel.NewsSourceModel): String {
        return Gson().toJson(model)
    }

    @TypeConverter
    fun convertStringToModel(modelString: String): NewsModel.NewsSourceModel {
        return Gson().fromJson(modelString, NewsModel.NewsSourceModel::class.java)
    }
}