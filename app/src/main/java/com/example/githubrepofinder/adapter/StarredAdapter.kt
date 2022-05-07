package com.example.githubrepofinder.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.githubrepofinder.R
import com.example.githubrepofinder.databinding.ItemRepoBinding
import com.example.githubrepofinder.response.StarredResponse
import com.example.githubrepofinder.utils.RepoListener
import com.example.githubrepofinder.utils.setImage
import java.util.*
import kotlin.collections.ArrayList

class StarredAdapter(val context: Context, private val todayEventClickListener: RepoListener) :
    RecyclerView.Adapter<StarredAdapter.StarredResponseHolder>() {
    private var detailslist: List<StarredResponse> = mutableListOf()

    inner class StarredResponseHolder(binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val binding: ItemRepoBinding? = DataBindingUtil.bind(itemView)

        fun onBind(info: StarredResponse) {
            binding!!.model = info
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarredResponseHolder {
        val binding: ItemRepoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_repo,
            parent,
            false
        )
        return StarredResponseHolder(binding)
    }

    override fun onBindViewHolder(holder: StarredResponseHolder, position: Int) {
        holder.onBind(detailslist[position])

        holder.binding?.name?.text = detailslist[position].name
        holder.binding?.stars?.text = detailslist[position].stargazersCount.toString()
        holder.binding?.profileImage?.let {
            Glide.with(context)
                .load(detailslist[position].owner?.avatarUrl ?: "")
                .circleCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                .into(it)
        }

        holder.binding?.next?.setOnClickListener {
            todayEventClickListener.onClick(detailslist[holder.adapterPosition],detailslist[position].name!!)
        }


    }

    override fun getItemCount(): Int {
        return detailslist.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getStarredResponse(list: List<StarredResponse>) {
        detailslist = list
        notifyDataSetChanged()
    }
}