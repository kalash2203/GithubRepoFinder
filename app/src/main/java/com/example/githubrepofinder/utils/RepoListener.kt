package com.example.githubrepofinder.utils

import com.example.githubrepofinder.model.UserInfo
import com.example.githubrepofinder.response.StarredResponse


interface RepoListener {

    fun onClick(todaySession: StarredResponse)
}