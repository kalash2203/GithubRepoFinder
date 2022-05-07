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
import com.example.githubrepofinder.repository.StarredRepository
import com.example.githubrepofinder.response.StarredResponse
import com.example.githubrepofinder.utils.Constants.ORGANIZATION
import com.example.githubrepofinder.utils.Constants.REPO
import com.example.githubrepofinder.utils.RepoListener
import com.example.githubrepofinder.viewModel.StarredViewModel
import com.example.githubrepofinder.viewModelFactory.StarredViewModelFactory

class RepoDetailsFragment : BaseFragment(),RepoListener {
    private lateinit var binding: FragmentStarredBinding
    private lateinit var starredViewModel: StarredViewModel
    private lateinit var starredViewModelFactory: StarredViewModelFactory
    private lateinit var pastDetailsAdapter: StarredAdapter
    var organizationName = "Kotlin"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStarredBinding.inflate(layoutInflater, container, false)





        val starredRepository = StarredRepository()
        starredViewModelFactory = StarredViewModelFactory(starredRepository)
        starredViewModel =
            ViewModelProvider(this, starredViewModelFactory)[StarredViewModel::class.java]

        getReponse()

        binding.search.setOnClickListener {
            if (binding.searchView.text.trim().isNotEmpty()) {
                organizationName = binding.searchView.text.toString()
                getReponse()
            }
        }

        return binding.root
    }

    fun getReponse() {
        starredViewModel.getRepo(organizationName).observe(viewLifecycleOwner) { response ->

            val sortedList: List<StarredResponse> = response
                .filter { it.language == "Kotlin" }
                .sortedWith(compareBy { it.stargazersCount })
                .reversed()


            binding.rvStarredFragment.visibility = View.VISIBLE
            binding.rvStarredFragment.setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(requireContext())
            binding.rvStarredFragment.layoutManager = linearLayoutManager
            pastDetailsAdapter = StarredAdapter(requireContext(),this)
            pastDetailsAdapter.getStarredResponse(sortedList)
            binding.rvStarredFragment.adapter = pastDetailsAdapter
        }
    }

    override fun onClick(todaySession: StarredResponse,repo : String) {
        val bundle = Bundle()
        bundle.putString(ORGANIZATION,organizationName)
        bundle.putString(REPO,repo)
        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).navigate(R.id.action_repoDetailsFragment_to_readMe,bundle)

    }
}