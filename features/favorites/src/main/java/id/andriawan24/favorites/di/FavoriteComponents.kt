package id.andriawan24.favorites.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import id.andriawan24.domain.di.UseCasesModule
import id.andriawan24.favorites.FavoriteActivity

@Component(dependencies = [UseCasesModule::class])
interface FavoriteComponents {

    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(useCasesModules: UseCasesModule): Builder
        fun build(): FavoriteComponents
    }
}