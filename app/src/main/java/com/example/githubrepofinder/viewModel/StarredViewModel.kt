package com.example.githubrepofinder.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubrepofinder.repository.StarredRepository
import com.example.githubrepofinder.response.StarredResponse

class StarredViewModel(private val starredRepository: StarredRepository) : ViewModel() {
    fun getRepo(organization:String): LiveData<List<StarredResponse>> {
        return starredRepository.getRepo(organization)
    }
}
