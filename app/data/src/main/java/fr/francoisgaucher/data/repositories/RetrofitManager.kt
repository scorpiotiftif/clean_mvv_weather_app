package fr.francoisgaucher.data.repositories

import retrofit2.Retrofit

class RetrofitManager {
    companion object{
        val retrofitBuilder = Retrofit.Builder()
    }
}