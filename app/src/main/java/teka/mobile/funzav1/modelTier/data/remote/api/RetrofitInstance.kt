package teka.mobile.funzav1.modelTier.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import teka.mobile.funzav1.utils.Credentials

class RetrofitInstance {

    companion object{
        fun getRetrofitInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(Credentials.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}