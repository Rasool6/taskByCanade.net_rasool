package com.example.android_task_rasool.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android_task_rasool.databinding.ItemCategoryListBinding
import com.example.android_task_rasool.model.CategoryModel

class CategoryListAdapter(private val stories: List<CategoryModel>) : RecyclerView.Adapter<CategoryListAdapter.StoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryListBinding.inflate(inflater, parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(stories[position])
    }

    override fun getItemCount(): Int = stories.size

    inner class StoryViewHolder(private val binding: ItemCategoryListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: CategoryModel) {

            binding.view.background=ContextCompat.getDrawable(binding.root.context, story.bgCircle)
            binding.mainLayer.background=ContextCompat.getDrawable(binding.root.context, story.bgColor)
            binding.categoryTxt.text=story.categoryTitle
                // Set other data specific to "My Story" layout here if needed

        }
    }
}


