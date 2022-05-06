package com.example.githubrepofinder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepofinder.R
import com.example.githubrepofinder.adapter.StarredAdapter
import com.example.githubrepofinder.databinding.FragmentStarredBinding
import com.example.githubrepofinder.model.UserInfo
import com.example.githubrepofinder.repository.StarredRepository
import com.example.githubrepofinder.response.StarredResponse
import com.example.githubrepofinder.utils.RepoListener
import com.example.githubrepofinder.viewModel.StarredViewModel
import com.example.githubrepofinder.viewModelFactory.StarredViewModelFactory

class RepoDetailsFragment : BaseFragment()  {
    private lateinit var binding: FragmentStarredBinding
    private lateinit var starredViewModel: StarredViewModel
    private lateinit var starredViewModelFactory: StarredViewModelFactory
    private lateinit var pastDetailsAdapter: StarredAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentStarredBinding.inflate(layoutInflater,container,false)



        val starredRepository = StarredRepository()
        starredViewModelFactory = StarredViewModelFactory( starredRepository)
        starredViewModel = ViewModelProvider(this,starredViewModelFactory)[StarredViewModel::class.java]
            starredViewModel.getRepo("square").observe(viewLifecycleOwner){
                   response ->




                binding.rvStarredFragment.visibility = View.VISIBLE
                binding.rvStarredFragment.setHasFixedSize(true)
                val linearLayoutManager = LinearLayoutManager(requireContext())
                binding.rvStarredFragment.layoutManager = linearLayoutManager
                pastDetailsAdapter = StarredAdapter(requireContext())
                pastDetailsAdapter.getStarredResponse(response)
                binding.rvStarredFragment.adapter = pastDetailsAdapter
            }


        return binding.root
    }

}