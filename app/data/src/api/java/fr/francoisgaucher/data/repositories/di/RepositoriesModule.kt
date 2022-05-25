package fr.francoisgaucher.data.repositories.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.francoisgaucher.data.repositories.meteo.AirRepositoryImpl
import fr.francoisgaucher.data.repositories.meteo.AirRepositoryMockedImpl
import fr.francoisgaucher.domain.repositories.MeteoAirRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun bindsAirRepositoryImpl(
        airRepositoryImpl: AirRepositoryImpl
    ): MeteoAirRepository
}
