package com.assignment.retrofit

import com.assignment.pojo.ListResponse
import com.assignment.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET(Constants.ROWS)
    fun getRows(): Call<ListResponse>
}