package dev.jeonghyeonji.imagesearch.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jeonghyeonji on 2017. 4. 6..
 */

class ImageRestClient {
    var retrofit: Retrofit
    var baseUrl = "https://apis.daum.net"

    val client
        get() = retrofit.create(ImageApi::class.java)

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpClient.Builder().build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}