package fr.francoisgaucher.template_clean_archi_modulaire.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.BINARY

@Qualifier
@Retention(BINARY)
annotation class CoroutineDispatcherIO

@Qualifier
@Retention(BINARY)
annotation class CoroutineDispatcherDefault

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @CoroutineDispatcherIO
    fun providesCoroutineDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @CoroutineDispatcherDefault
    fun providesCoroutineDispatcherDefault(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}
