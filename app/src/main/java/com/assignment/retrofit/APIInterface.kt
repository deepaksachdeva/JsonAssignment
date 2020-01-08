package com.assignment.retrofit

import com.assignment.pojo.ListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface APIInterface {

    @GET("2iodh4vg0eortkl/facts.json")
    fun getPeople(): Call<ListResponse>

    @GET
    fun getFilmData(@Url url: String, @Query("format") format: String): Call<ListResponse>
}