package com.example.githubrepofinder.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.tiagohm.markdownview.MarkdownView
import br.tiagohm.markdownview.css.styles.Github
import com.example.githubrepofinder.databinding.FragmentReadMeBinding
import com.example.githubrepofinder.repository.StarredRepository
import com.example.githubrepofinder.utils.Constants.ORGANIZATION
import com.example.githubrepofinder.utils.Constants.REPO
import com.example.githubrepofinder.viewModel.StarredViewModel
import com.example.githubrepofinder.viewModelFactory.StarredViewModelFactory


class ReadMe : BaseFragment() {

    private lateinit var binding: FragmentReadMeBinding
    private lateinit var starredViewModel: StarredViewModel
    private lateinit var starredViewModelFactory: StarredViewModelFactory
    private lateinit var organization: String
    private lateinit var repo: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReadMeBinding.inflate(layoutInflater, container, false)

        organization = arguments?.getString(ORGANIZATION).toString()
        repo = arguments?.getString(REPO).toString()

        val starredRepository = StarredRepository()
        starredViewModelFactory = StarredViewModelFactory(starredRepository)
        starredViewModel =
            ViewModelProvider(this, starredViewModelFactory)[StarredViewModel::class.java]

      binding.markdownView.addStyleSheet(Github())

        getReponse()

        return binding.root

    }

    fun getReponse() {
        starredViewModel.getReadMe(organization, repo).observe(viewLifecycleOwner) { response ->

            binding.markdownView.loadMarkdown("**MarkdownView**")
            binding.markdownView.loadMarkdownFromUrl(response.downloadUrl)
        }
    }

}
