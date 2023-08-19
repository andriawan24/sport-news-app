package id.andriawan24.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryModel(
    val id: String,
    val name: String
) : Parcelable {
    companion object {
        fun getCountries(): List<CountryModel> {
            return listOf(
                CountryModel(
                    id = "id",
                    name = "Indonesia"
                ),
                CountryModel(
                    id = "ae",
                    name = "Uni Arab Emirates"
                ),
                CountryModel(
                    id = "ar",
                    name = "Argentina"
                ),
                CountryModel(
                    id = "gb",
                    name = "United Kingdom"
                ),
                CountryModel(
                    id = "ru",
                    name = "Russia"
                ),
                CountryModel(
                    id = "us",
                    name = "United States (US)"
                ),
                CountryModel(
                    id = "ve",
                    name = "Venezuela"
                )
            )
        }
    }
}
