package com.assignment.retrofit

import com.assignment.pojo.ListResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("2iodh4vg0eortkl/facts.json")
    fun getPeople(): Call<ListResponse>
}