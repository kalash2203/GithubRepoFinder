package com.example.githubrepofinder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubrepofinder.R
import com.example.githubrepofinder.databinding.FragmentReadMeBinding
import com.example.githubrepofinder.databinding.FragmentStarredBinding


class ReadMe : BaseFragment(){

    private lateinit var binding: FragmentReadMeBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentReadMeBinding.inflate(layoutInflater, container, false)


        return  binding.root

    }

    }
