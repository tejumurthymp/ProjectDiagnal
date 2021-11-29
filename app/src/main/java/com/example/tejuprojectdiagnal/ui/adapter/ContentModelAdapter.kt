package com.example.tejuprojectdiagnal.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tejuprojectdiagnal.R
import com.example.tejuprojectdiagnal.databinding.RecyclerAdapterContentBinding
import com.example.tejuprojectdiagnal.mvvm.models.ContentModel

class ContentModelAdapter: PagingDataAdapter<ContentModel.Page.ContentItems.Content, ContentModelAdapter.MyHolder>(CALLBACK) {

    companion object {
        private const val TAG = "ContentModelAdapter"
    }

    init {
        Log.e(TAG, "init method: " )
    }

    class MyHolder(val binding: RecyclerAdapterContentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        Log.e(TAG, "onBindViewHolder: ccalled" )
        getItem(position)?.apply {
            holder.binding.imageView.load(R.drawable.poster1)
            holder.binding.textView.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        Log.e(TAG, "onCreateViewHolder: called" )
        val binding = RecyclerAdapterContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    object CALLBACK : DiffUtil.ItemCallback<ContentModel.Page.ContentItems.Content>() {
        override fun areItemsTheSame(
            oldItem: ContentModel.Page.ContentItems.Content,
            newItem: ContentModel.Page.ContentItems.Content
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: ContentModel.Page.ContentItems.Content,
            newItem: ContentModel.Page.ContentItems.Content
        ): Boolean {
            return oldItem == newItem
        }
    }
}