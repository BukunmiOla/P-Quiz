package io.github.bukunmiola.pquiz.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object QuizClientInstance {
    private fun getHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.writeTimeout(30, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    public  fun getClientInstance(): Retrofit? {
        return Retrofit.Builder()
            .baseUrl("https://bukunmiola.github.io")
            .client(getHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}