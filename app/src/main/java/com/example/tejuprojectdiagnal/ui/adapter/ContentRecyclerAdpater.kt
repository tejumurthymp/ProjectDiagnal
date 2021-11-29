package com.example.tejuprojectdiagnal.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tejuprojectdiagnal.R
import com.example.tejuprojectdiagnal.databinding.RecyclerAdapterContentBinding
import com.example.tejuprojectdiagnal.mvvm.models.ContentModel

class ContentRecyclerAdapter(private val data: List<ContentModel.Page.ContentItems.Content?>?) : RecyclerView.Adapter<ContentRecyclerAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = RecyclerAdapterContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        data?.get(position)?.apply {
            holder.binding.imageView.load(R.drawable.poster1)
            holder.binding.textView.text = name
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class MyHolder(val binding: RecyclerAdapterContentBinding) : RecyclerView.ViewHolder(binding.root)
}