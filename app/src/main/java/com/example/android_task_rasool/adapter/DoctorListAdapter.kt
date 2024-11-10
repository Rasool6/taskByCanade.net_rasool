package com.example.android_task_rasool.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_task_rasool.model.Story
import com.example.android_task_rasool.databinding.ItemDoctorCalerBinding

class DoctorListAdapter(private val stories: List<Story>) : RecyclerView.Adapter<DoctorListAdapter.StoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDoctorCalerBinding.inflate(inflater, parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(stories[position])
    }

    override fun getItemCount(): Int = stories.size

    inner class StoryViewHolder(private val binding: ItemDoctorCalerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
            if (story.isMyStory) {
              //  binding.storyImg.setImageDrawable(ContextCompat.getDrawable(binding.root.context, story.storyImg))
                // Set other data specific to "My Story" layout here if needed
            } else {
              //  binding.storyImg.setImageDrawable(ContextCompat.getDrawable(binding.root.context, story.storyImg))
                // Set other data specific to "Other Story" layout here if needed
            }
        }
    }
}


