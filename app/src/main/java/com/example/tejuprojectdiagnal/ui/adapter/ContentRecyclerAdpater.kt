package com.example.tejuprojectdiagnal.ui.adapter

import android.util.Log
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
        Log.e("Recycler", "onBindViewHolder: ${data?.size}" )
        data?.get(position)?.apply {
            val drawableResId = when(`poster-image`){
                "poster1.jpg" -> R.drawable.poster1
                "poster2.jpg" -> R.drawable.poster2
                "poster3.jpg" -> R.drawable.poster3
                "poster4.jpg" -> R.drawable.poster4
                "poster5.jpg" -> R.drawable.poster5
                "poster6.jpg" -> R.drawable.poster6
                "poster7.jpg" -> R.drawable.poster7
                "poster8.jpg" -> R.drawable.poster8
                "poster9.jpg" -> R.drawable.poster9
                else -> 0
            }

            holder.binding.imageView.load(drawableResId) {
                placeholder(R.drawable.placeholder_for_missing_posters)
                error(R.drawable.placeholder_for_missing_posters)
            }

            holder.binding.textView.text = name
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class MyHolder(val binding: RecyclerAdapterContentBinding) : RecyclerView.ViewHolder(binding.root)
}