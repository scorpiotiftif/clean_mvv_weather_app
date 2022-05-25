package fr.francoisgaucher.data.repositories.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.francoisgaucher.data.repositories.HttpInterceptor
import fr.francoisgaucher.data.repositories.meteo.AirMeteoService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Wrappers {
        return Wrappers().also {
            it.retrofit = Retrofit.Builder()
                .baseUrl("https://api.ambeedata.com")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(OkHttpClient.Builder().addInterceptor(HttpInterceptor()).build())
                .build()
        }
    }

    @Provides
    @Singleton
    fun providesAirMeteoService(wrapper: Wrappers): AirMeteoService {
        return wrapper.retrofit.create(AirMeteoService::class.java)
    }
}
