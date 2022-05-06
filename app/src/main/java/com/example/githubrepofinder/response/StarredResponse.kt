package com.example.githubrepofinder.response

import com.google.gson.annotations.SerializedName

data class StarredResponse(

    @SerializedName("name"              ) var name             : String?           = null,
    @SerializedName("owner"             ) var owner            : Owner?            = Owner(),
    @SerializedName("stargazers_count"  ) var stargazersCount  : Int?              = null,
    @SerializedName("language") var language : String? = null
)
