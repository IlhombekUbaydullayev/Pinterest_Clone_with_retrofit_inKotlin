package com.example.pinterest_clone_with_retrofit_inkotlin.activity.network.service

import com.example.pinterest_clone_with_retrofit_inkotlin.activity.model.Collection
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.model.Photo
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.model.RelatedPhotos
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.model.Search
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.model.Topic
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

@JvmSuppressWildcards
interface PhotosService {

    companion object {
        private const val ACCESS_KEY = "S2H10fWyKRdyT0ggNtJz6AgdhnVD5cXKC6w9yKyWq9s"
//          private const val ACCESS_KEY = "t_zjuLnwQkkPC3MSejZgQ0ufz9DHDWBw2Ku_eqvlwnE"
    }

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("photos")
    fun listPhotos(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = 20,
    ): Call<ArrayList<Photo>>

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("photos/{id}/related")
    fun getRelatedPhotos(@Path("id") id: String): Call<RelatedPhotos>

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("search/photos")
    fun searchPhoto(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 20,
    ): Call<Search>

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("topics")
    fun getTopics(
        @Query("page") page: Int = 2,
        @Query("per_page") per_page: Int = 10,
    ): Call<ArrayList<Topic>>

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("collections")
    fun getCollections(
        @Query("page") page: Int = 2,
        @Query("per_page") per_page: Int = 10,
    ): Call<ArrayList<Collection>>


}