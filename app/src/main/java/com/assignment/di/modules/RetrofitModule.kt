package com.assignment.di.modules

import com.assignment.di.scopes.ApplicationScope
import com.assignment.retrofit.APIInterface
import com.assignment.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [OkHttpClientModule::class])
class RetrofitModule {

    @Provides
    @ApplicationScope
    fun getApiInterface(retroFit: Retrofit): APIInterface {
        return retroFit.create(APIInterface::class.java)
    }

    @Provides
    @ApplicationScope
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}