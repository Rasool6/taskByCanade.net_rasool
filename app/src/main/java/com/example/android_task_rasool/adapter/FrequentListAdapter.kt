package com.example.android_task_rasool.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android_task_rasool.databinding.ItemFreqListBinding
import com.example.android_task_rasool.model.FrequentDataModel

class FrequentListAdapter(private val stories: List<FrequentDataModel>,var callBack:()->Unit) : RecyclerView.Adapter<FrequentListAdapter.StoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFreqListBinding.inflate(inflater, parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(stories[position])
    }

    override fun getItemCount(): Int = stories.size

    inner class StoryViewHolder(private val binding: ItemFreqListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: FrequentDataModel) {

            binding.appCompatImageView5.setImageDrawable(ContextCompat.getDrawable(binding.root.context, story.bgCircle))
            binding.appCompatImageView5.background=ContextCompat.getDrawable(binding.root.context, story.icon)
            binding.frequentViewAllBtn.text=story.categoryTitle
                // Set other data specific to "My Story" layout here if needed

            when(story.categoryTitle){
                "Caller\nID"->{
                  binding.root.setOnClickListener {
                      callBack()
                  }
                }
            }

        }
    }
}


