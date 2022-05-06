package com.example.githubrepofinder.network

import com.example.githubrepofinder.response.StarredResponse
import retrofit2.Call
import retrofit2.http.*


interface Apis {

    @GET("orgs/{org}/repos")
    fun repoList(
        @Path("org") organization: String)
    : Call<List<StarredResponse>>

}