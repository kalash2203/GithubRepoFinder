package com.example.githubrepofinder.network

import com.example.githubrepofinder.response.ReadMeResponse
import com.example.githubrepofinder.response.StarredResponse
import retrofit2.Call
import retrofit2.http.*


interface Apis {

    @GET("orgs/{org}/repos")
    fun repoList(
        @Path("org") organization: String)
    : Call<List<StarredResponse>>

    @GET("repos/{org}/{repo}/readme")
    fun getReadMe(
        @Path("org") organization: String,
        @Path("repo") repo: String)
            : Call<ReadMeResponse>

}