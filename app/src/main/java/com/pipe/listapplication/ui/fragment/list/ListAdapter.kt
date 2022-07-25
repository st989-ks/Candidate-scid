package com.pipe.listapplication.ui.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pipe.listapplication.databinding.RecyclerListBinding
import com.pipe.listapplication.domain.entity.PostEntity
import javax.inject.Inject

class ListAdapter @Inject constructor() :
    PagingDataAdapter<PostEntity, ListAdapter.ListViewHolder>(DiffUtils) {

    object DiffUtils : DiffUtil.ItemCallback<PostEntity>() {
        override fun areItemsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean =
            oldItem == newItem
    }

    private var onContainerClickListener: ((PostEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        RecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ListViewHolder(private val binding: RecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostEntity) = with(binding) {
            textViewCard.text = post.name
            recyclerCard.setOnClickListener { onContainerClickListener?.let { it(post) } }
        }
    }

    fun setOnPostClickListener(listener: (PostEntity) -> Unit) {
        onContainerClickListener = listener
    }

}