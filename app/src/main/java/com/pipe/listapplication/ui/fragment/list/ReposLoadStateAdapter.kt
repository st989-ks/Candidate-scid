package com.pipe.listapplication.ui.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pipe.listapplication.databinding.ProgressBarListBinding

class ReposLoadStateAdapter() :
    LoadStateAdapter<ReposLoadStateAdapter.LoadViewHolder>() {

    override fun onBindViewHolder(holder: LoadViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) = LoadViewHolder(
        ProgressBarListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    inner class LoadViewHolder(private val binding:  ProgressBarListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) = with(binding) {
            if (loadState is LoadState.Error) {
                errorMsg.text = loadState.error.localizedMessage
            }
            progressBar.isVisible = loadState is LoadState.Loading
            errorMsg.isVisible = loadState is LoadState.Error
        }
    }
}