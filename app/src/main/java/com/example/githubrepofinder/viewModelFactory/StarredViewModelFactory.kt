package com.example.githubrepofinder.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubrepofinder.repository.StarredRepository
import com.example.githubrepofinder.viewModel.StarredViewModel

@Suppress("UNCHECKED_CAST")
class StarredViewModelFactory(private val starredRepository: StarredRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StarredViewModel(starredRepository) as T
    }}