package dev.jeonghyeonji.imagesearch.network

import dev.jeonghyeonji.imagesearch.model.Channel
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
* Created by jeonghyeonji on 2017. 4. 6..
*/
interface ImageApi {

    //발급된 key도 넘긴다
    @GET("/search/image")
    fun getImage(
            @Query("apikey") apikey: String,
            @Query("q") searchword: String, @Query("output") output: String = "json"
    ): Flowable<Channel>

}